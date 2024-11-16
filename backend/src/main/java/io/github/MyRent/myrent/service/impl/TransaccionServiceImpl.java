package io.github.MyRent.myrent.service.impl;

import io.github.MyRent.myrent.dto.TransaccionDTO;
import io.github.MyRent.myrent.model.Propiedad;
import io.github.MyRent.myrent.model.TipoTransaccion;
import io.github.MyRent.myrent.model.Transaccion;
import io.github.MyRent.myrent.repository.PropiedadRepository;
import io.github.MyRent.myrent.repository.TransaccionRepository;
import io.github.MyRent.myrent.service.ITransaccionService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TransaccionServiceImpl implements ITransaccionService {
    private final TransaccionRepository transaccionRepository;
    private final PropiedadRepository propiedadRepository;

    public TransaccionServiceImpl(TransaccionRepository transaccionRepository, PropiedadRepository propiedadRepository) {
        this.transaccionRepository = transaccionRepository;
        this.propiedadRepository = propiedadRepository;
    }

    @Override
    public TransaccionDTO findById(Long id) {
        return transaccionRepository.findById(id)
                .map(this::entityToDTO)
                .orElseThrow(() -> new EntityNotFoundException("Transaccion con ID " + id + " no encontrada"));
    }

    @Override
    public TransaccionDTO save(TransaccionDTO transaccion) {
        Transaccion entity = dtoToEntity(transaccion);
        Transaccion savedEntity = transaccionRepository.save(entity);
        transaccionRepository.flush();
        return entityToDTO(savedEntity);
    }

    @Override
    public void deleteById(Long id) {
        transaccionRepository.deleteById(id);
    }

    @Override
    public List<TransaccionDTO> getTransaccionesByUsuarioId(Long idUsuario) {
        return transaccionRepository.findByUsuarioId(idUsuario)
                .stream()
                .map(this::entityToDTO)
                .toList();
    }

    @Override
    public List<TransaccionDTO> getTransaccionesByPerfilId(Long idPerfil) {
        return transaccionRepository.findByPerfilId(idPerfil)
                .stream()
                .map(this::entityToDTO)
                .toList();
    }

    @Override
    public List<TransaccionDTO> getTransaccionesByPropiedadId(Long idPropiedad) {
        return transaccionRepository.findByPropiedadId(idPropiedad)
                .stream()
                .map(this::entityToDTO)
                .toList();
    }

    @Override
    public List<TransaccionDTO> getTransaccionesByUsuarioIdAndTipo(Long idUsuario, String tipo) {
        return transaccionRepository.findByUsuarioIdAndTipoTransaccion(idUsuario, TipoTransaccion.valueOf(tipo.toUpperCase()))
                .stream()
                .map(this::entityToDTO)
                .toList();
    }

    @Override
    public List<TransaccionDTO> getTransaccionesByPerfilIdAndTipo(Long idPerfil, String tipo) {
        return transaccionRepository.findByPerfilIdAndTipoTransaccion(idPerfil, TipoTransaccion.valueOf(tipo.toUpperCase()))
                .stream()
                .map(this::entityToDTO)
                .toList();
    }

    @Override
    public List<TransaccionDTO> getTransaccionesByPropiedadIdAndTipo(Long idPropiedad, String tipo) {
        return transaccionRepository.findByPropiedadIdAndTipoTransaccion(idPropiedad, TipoTransaccion.valueOf(tipo.toUpperCase()))
                .stream()
                .map(this::entityToDTO)
                .toList();
    }

    private TransaccionDTO entityToDTO(Transaccion entity){
        return TransaccionDTO.builder()
                .id(entity.getId())
                .propiedadId(entity.getPropiedad().getId())
                .tipoTransaccion(entity.getTipoTransaccion().toString())
                .fecha(entity.getFecha())
                .descripcion(entity.getDescripcion())
                .monto(entity.getMonto())
                .build();
    }

    private Transaccion dtoToEntity(TransaccionDTO dto){
        Transaccion entity = new Transaccion();
        Propiedad propiedad = propiedadRepository.findById(dto.getPropiedadId())
                .orElseThrow(() -> new EntityNotFoundException("Propiedad con ID " + dto.getPropiedadId() + " no encontrada"));
        entity.setId(dto.getId());
        entity.setPropiedad(propiedad);
        entity.setTipoTransaccion(TipoTransaccion.valueOf(dto.getTipoTransaccion()));
        entity.setFecha(dto.getFecha());
        entity.setDescripcion(dto.getDescripcion());
        entity.setMonto(dto.getMonto());
        return entity;
    }


}
