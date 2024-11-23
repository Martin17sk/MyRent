package io.github.MyRent.myrent.service.impl;

import io.github.MyRent.myrent.dto.DesempenioDTO;
import io.github.MyRent.myrent.model.Desempenio;
import io.github.MyRent.myrent.model.TipoTransaccion;
import io.github.MyRent.myrent.model.Transaccion;
import io.github.MyRent.myrent.repository.DesempenioRepository;
import io.github.MyRent.myrent.repository.TransaccionRepository;
import io.github.MyRent.myrent.service.IDesempenioService;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.util.List;

@Service
public class DesempenioServiceImpl implements IDesempenioService {
    private final DesempenioRepository desempenioRepository;
    private final TransaccionRepository transaccionRepository;
    public DesempenioServiceImpl(DesempenioRepository desempenioRepository,
                                 TransaccionRepository transaccionRepository) {
        this.desempenioRepository = desempenioRepository;
        this.transaccionRepository = transaccionRepository;
    }

    @Override
    public void generateDesempenios() {
        YearMonth month = YearMonth.now();
        transaccionRepository.findDistinctPropiedades().forEach(propiedad -> {
            List<Transaccion> Ingresos = transaccionRepository.findByPropiedadIdAndTipoTransaccionAndFechaBetween(
                    propiedad.getId(),
                    TipoTransaccion.INGRESO,
                    java.sql.Date.valueOf(month.atDay(1)),
                    java.sql.Date.valueOf(month.atEndOfMonth()));

            List<Transaccion> Gastos = transaccionRepository.findByPropiedadIdAndTipoTransaccionAndFechaBetween(
                    propiedad.getId(),
                    TipoTransaccion.GASTO,
                    java.sql.Date.valueOf(month.atDay(1)),
                    java.sql.Date.valueOf(month.atEndOfMonth()));

            List<Transaccion> Costes = transaccionRepository.findByPropiedadIdAndTipoTransaccionAndFechaBetween(
                    propiedad.getId(),
                    TipoTransaccion.COSTE,
                    java.sql.Date.valueOf(month.atDay(1)),
                    java.sql.Date.valueOf(month.atEndOfMonth()));

            float ingresos = Ingresos.stream().map(Transaccion::getMonto).reduce(0,Integer::sum);
            float gastos = Gastos.stream().map(Transaccion::getMonto).reduce(0,Integer::sum);
            float costes = Costes.stream().map(Transaccion::getMonto).reduce(0,Integer::sum);
            float ocupacion = 0;
            float beneficios = ingresos - gastos - costes;


            Desempenio desempenio = Desempenio.builder()
                    .fecha(java.sql.Date.valueOf(month.atDay(1)))
                    .ocupacion(ocupacion)
                    .ingresos(ingresos)
                    .gastos(gastos)
                    .costes(costes)
                    .beneficios(beneficios)
                    .propiedad(propiedad)
                    .build();
            desempenioRepository.save(desempenio);
        });
    }

    @Override
    public List<DesempenioDTO> findByPropiedadId(Long propiedadId) {
        return desempenioRepository.findByPropiedadId(propiedadId).stream()
                .map(this::toDesempenioDTO)
                .toList();
    }

    @Override
    public List<DesempenioDTO> findByPropiedadIdAndMonth(Long propiedadId, YearMonth month) {
        return desempenioRepository.findByPropiedadIdAndFechaBetween(propiedadId,
                        java.sql.Date.valueOf(month.atDay(1)),
                        java.sql.Date.valueOf(month.atEndOfMonth())).stream()
                .map(this::toDesempenioDTO)
                .toList();
    }

    private DesempenioDTO toDesempenioDTO(Desempenio desempenio) {
        return DesempenioDTO.builder()
                .id(desempenio.getId())
                .propiedadId(desempenio.getPropiedad().getId())
                .fecha(YearMonth.from(desempenio.getFecha().toLocalDate()))
                .ocupacion(desempenio.getOcupacion())
                .ingresos(desempenio.getIngresos())
                .gastos(desempenio.getGastos())
                .costes(desempenio.getCostes())
                .beneficios(desempenio.getBeneficios())
                .build();
    }
}
