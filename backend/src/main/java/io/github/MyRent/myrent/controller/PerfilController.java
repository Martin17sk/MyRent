package io.github.MyRent.myrent.controller;

import io.github.MyRent.myrent.dto.PerfilDTO;
import io.github.MyRent.myrent.service.PerfilService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/perfiles")
public class PerfilController {
    // region Configuración
    PerfilService perfilService;
    public PerfilController(PerfilService perfilService) {
        this.perfilService = perfilService;
    }
    // endregion
    // region Endpoints
    @GetMapping("/{id_usuario}")
    public ResponseEntity<List<PerfilDTO>> conseguirTodosLosPerfiles(@PathVariable long id_usuario) {
        try {
            List<PerfilDTO> perfiles = perfilService.conseguirTodosLosPerfilesPorIdUsuario(id_usuario);
            return ResponseEntity.ok(perfiles);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    // endregion
}
