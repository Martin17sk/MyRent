package io.github.MyRent.myrent.service;

import io.github.MyRent.myrent.model.Usuario;
import io.github.MyRent.myrent.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }
    public Usuario insertarUsuario(String correo, String contrasenia){
        usuarioRepository.insertarUsuario(correo, contrasenia);
        Usuario usuario = new Usuario();
        usuario.setCorreo(correo);
        usuario.setContrasenia(contrasenia);
        return usuario;
    }
}
