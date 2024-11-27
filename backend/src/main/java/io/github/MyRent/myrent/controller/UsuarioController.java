package io.github.MyRent.myrent.controller;

import io.github.MyRent.myrent.dto.UsuarioDTO;
import io.github.MyRent.myrent.dto.UsuarioUpdateDTO;
import io.github.MyRent.myrent.repository.UsuarioRepository;
import io.github.MyRent.myrent.service.impl.UsuarioServiceImpl;
import org.springframework.http.ResponseEntity;
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
        try{
            usuarioService.eliminarUsuario(id_usuario);
            return ResponseEntity.ok(Map.of("mensaje", "Usuario eliminado"));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    // Actualizar un usuario
    @PutMapping("/{id_usuario}")
    public ResponseEntity<UsuarioDTO> actualizarUsuario(
            @PathVariable long id_usuario,
            @RequestBody UsuarioUpdateDTO usuarioUpdateDto
    ){
        try{
            UsuarioDTO usuarioExistente  = usuarioService.obtenerUsuarioPorId(id_usuario);
            if (usuarioUpdateDto.getCorreo() != null) {
                usuarioExistente.setCorreo(usuarioUpdateDto.getCorreo());
            }
            if (usuarioUpdateDto.getContrasenia() != null) {
                usuarioExistente.setContrasenia(usuarioUpdateDto.getContrasenia());
            }
            // Actualizar la fecha de acceso
            usuarioExistente.setFecha_acceso(LocalDateTime.now());

            // Guardar los cambios
            usuarioService.actualizarUsuario(usuarioExistente);

            return ResponseEntity.ok(usuarioExistente);

        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // endregion
}
