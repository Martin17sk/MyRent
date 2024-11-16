package io.github.MyRent.myrent.controller;

import io.github.MyRent.myrent.model.Usuario;
import io.github.MyRent.myrent.repository.UsuarioRepository;
import io.github.MyRent.myrent.service.impl.UsuarioServiceImpl;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    private final UsuarioServiceImpl usuarioServiceImpl;
    public UsuarioController(UsuarioRepository usuarioRepository, UsuarioServiceImpl usuarioServiceImpl){
        this.usuarioServiceImpl = usuarioServiceImpl;
    }

    @GetMapping("/{id_usuario}")
    public ResponseEntity<Usuario> getUsuarioByUsuario_id(@PathVariable int id_usuario) {
        Optional<Usuario> usuario = usuarioServiceImpl.encontrarUsuarioPorId(id_usuario);
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
            Usuario usuarioGuardado = usuarioServiceImpl.guardarUsuario(usuario);

            // Devolver el usuario guardado con el estado 201 Created
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioGuardado);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @DeleteMapping("/{id_usuario}")
    public ResponseEntity<Map<String, String>> eliminarUsuario(@PathVariable long id_usuario){
        try{
            // Buscar el usuario por su id
            Optional<Usuario> usuario = usuarioServiceImpl.encontrarUsuarioPorId(id_usuario);

            // Si el usuario no existe, devolver un error 404 Not Found
            if(usuario.isEmpty()){
                return ResponseEntity.notFound().build();
            }

            // Eliminar el usuario de la base de datos
            usuarioServiceImpl.eliminarUsuarioPorId(id_usuario);

            // Crear un mapa para el mensaje de respuesta
            Map<String, String> response = new HashMap<>();
            response.put("message", "Usuario eliminado con éxito");

            // Devolver el mensaje con el estado 200 OK
            return ResponseEntity.ok(response);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    // Actualizar un usuario
    @PutMapping("/{id_usuario}")
    public ResponseEntity<Usuario> actualizarUsuario(
            @PathVariable long id_usuario,
            @RequestParam("correo") String correo,
            @RequestParam("contrasenia") String contrasenia
    ){
        try{
            // Buscar el usuario por su id
            Optional<Usuario> usuario = usuarioServiceImpl.encontrarUsuarioPorId(id_usuario);

            // Si el usuario no existe, devolver un error 404 Not Found
            if(usuario.isEmpty()){
                return ResponseEntity.notFound().build();
            }

            // Actualizar el usuario con los nuevos datos
            Usuario usuarioActualizado = usuario.get();
            usuarioActualizado.setCorreo(correo);
            usuarioActualizado.setContrasenia(contrasenia);

            // Guardar el usuario actualizado en la base de datos
            Usuario usuarioGuardado = usuarioServiceImpl.guardarUsuario(usuarioActualizado);

            // Devolver el usuario guardado con el estado 200 OK
            return ResponseEntity.ok(usuarioGuardado);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
