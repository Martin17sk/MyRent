package io.github.MyRent.myrent.repository;

import io.github.MyRent.myrent.model.Desempenio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface DesempenioRepository extends JpaRepository<Desempenio, Long> {
    List<Desempenio> findByPropiedadId(Long propiedadId);
    List<Desempenio> findByPropiedadIdAndFechaBetween(Long propiedadId, Date fechaInicio, Date fechaFin);
}
