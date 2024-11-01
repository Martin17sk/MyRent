package io.github.MyRent.myrent.controller;

import io.github.MyRent.myrent.model.Usuario;
import io.github.MyRent.myrent.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/{id_usuario}")
    public ResponseEntity<Usuario> getUsuarioByUsuario_id(@PathVariable Long id_usuario) {
        Optional<Usuario> usuario = usuarioRepository.findById(id_usuario);
        return usuario.map(ResponseEntity::ok).
                orElseGet(()->ResponseEntity.notFound().build());
    }

}
