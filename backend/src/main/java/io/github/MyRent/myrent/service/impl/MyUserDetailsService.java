package io.github.MyRent.myrent.service.impl;

import io.github.MyRent.myrent.model.Usuario;
import io.github.MyRent.myrent.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {
    private final UsuarioRepository usuarioRepository;
    public MyUserDetailsService( UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        Usuario user = usuarioRepository.findUsuarioByCorreo(correo) // Usa "findByEmail" si trabajas con correos.
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + correo));
        return new org.springframework.security.core.userdetails.User(
                user.getCorreo(), // Cambia esto si usas otro campo como username.
                user.getContrasenia(), // La contraseña debe estar cifrada.
                new ArrayList<>() // Lista de roles o permisos, si aplica.
        );
    }
}
