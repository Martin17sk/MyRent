package io.github.MyRent.myrent.controller;

import io.github.MyRent.myrent.model.Usuario;
import io.github.MyRent.myrent.repository.UsuarioRepository;
import io.github.MyRent.myrent.service.UsuarioService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    private final UsuarioRepository usuarioRepository;
    private final UsuarioService usuarioService;
    public UsuarioController(UsuarioRepository usuarioRepository, UsuarioService usuarioService){
        this.usuarioRepository = usuarioRepository;
        this.usuarioService = usuarioService;
    }

    @GetMapping("/{id_usuario}")
    public ResponseEntity<Usuario> getUsuarioByUsuario_id(@PathVariable Long id_usuario) {
        Optional<Usuario> usuario = usuarioRepository.findById(id_usuario);
        return usuario.map(ResponseEntity::ok).
                orElseGet(()->ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<Usuario> insertarUsuario(
            @RequestParam("correo") String correo,
            @RequestParam("contrasenia") String contrasenia
    ){
        try{
            // Crear un nuevo usuario con los datos proporcionados
            Usuario usuario = new Usuario();
            usuario.setCorreo(correo);
            usuario.setContrasenia(contrasenia);

            // Guardar el usuario en la base de datos
            Usuario usuarioGuardado = usuarioRepository.save(usuario);

            // Devolver el usuario guardado con el estado 201 Created
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioGuardado);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}
