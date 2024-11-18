package io.github.MyRent.myrent.controller;

import io.github.MyRent.myrent.model.UsuarioDTO;
import io.github.MyRent.myrent.repository.UsuarioRepository;
import io.github.MyRent.myrent.service.impl.UsuarioServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    // region Configuración
    private final UsuarioServiceImpl usuarioService;
    public UsuarioController(UsuarioRepository usuarioRepository, UsuarioServiceImpl usuarioServiceImpl){
        this.usuarioService = usuarioServiceImpl;
    }
    // endregion
    // region Endpoints
    @GetMapping("/{id_usuario}")
    public ResponseEntity<UsuarioDTO> getUsuarioById(@PathVariable long id_usuario) {
        try {
            UsuarioDTO usuarioDTO = usuarioService.obtenerUsuarioPorId(id_usuario);
            return ResponseEntity.ok(usuarioDTO);
        } catch (Exception e) {

            return ResponseEntity.notFound().build();

        }
    }
    @PostMapping
    public ResponseEntity<UsuarioDTO> insertarUsuario(
            @RequestParam("correo") String correo,
            @RequestParam("contrasenia") String contrasenia
    ){
      try{
            UsuarioDTO usuarioDTO = new UsuarioDTO();
            usuarioDTO.setCorreo(correo);
            usuarioDTO.setContrasenia(contrasenia);
            usuarioDTO.setFecha_acceso(LocalDateTime.now());
            usuarioDTO.setFecha_registro(LocalDateTime.now());
            usuarioService.crearUsuario(usuarioDTO);
            return ResponseEntity.ok(usuarioDTO);
      } catch (Exception e) {
            return ResponseEntity.badRequest().build();
      }
    }
    @DeleteMapping("/{id_usuario}")
    public ResponseEntity<Map<String, String>> eliminarUsuario(@PathVariable long id_usuario){
        return null;
    }
    // Actualizar un usuario
    @PutMapping("/{id_usuario}")
    public ResponseEntity<UsuarioDTO> actualizarUsuario(
            @PathVariable long id_usuario,
            @RequestParam("correo") String correo,
            @RequestParam("contrasenia") String contrasenia
    ){
        return null;
    }

    // endregion
}
