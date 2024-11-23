package io.github.MyRent.myrent.service;

import io.github.MyRent.myrent.dto.DesempenioDTO;

import java.time.YearMonth;
import java.util.List;

public interface IDesempenioService {
    void generateDesempenios();
    List<DesempenioDTO> findByPropiedadId(Long propiedadId);
    List<DesempenioDTO> findByPropiedadIdAndMonth(Long propiedadId, YearMonth month);
}
