package io.github.MyRent.myrent.controller;

import io.github.MyRent.myrent.dto.DesempenioDTO;
import io.github.MyRent.myrent.service.IDesempenioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.YearMonth;
import java.util.List;

@RestController
@RequestMapping("/api/desempenio")
public class DesempenioController {
    private final IDesempenioService desempenioService;

    public DesempenioController(IDesempenioService desempenioService) {
        this.desempenioService = desempenioService;
    }
    @GetMapping("/propiedad/{id}")
    public ResponseEntity<List<DesempenioDTO>> getDesempenioByPropiedadId(@PathVariable Long id) {
        List<DesempenioDTO> desempenios = desempenioService.findByPropiedadId(id);
        return ResponseEntity.ok(desempenios);
    }

    @GetMapping("/propiedad/{id}/month/{month}")
    public ResponseEntity<List<DesempenioDTO>> getDesempenioByPropiedadIdAndMonth(@PathVariable Long id, @PathVariable String month) {
        List<DesempenioDTO> desempenios = desempenioService.findByPropiedadIdAndMonth(id, YearMonth.parse(month));
        return ResponseEntity.ok(desempenios);
    }

}
