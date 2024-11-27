package io.github.MyRent.myrent.service.impl;

import io.github.MyRent.myrent.model.Usuario;
import io.github.MyRent.myrent.dto.UsuarioDTO;
import io.github.MyRent.myrent.repository.UsuarioRepository;
import io.github.MyRent.myrent.service.UsuarioService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    UsuarioServiceImpl(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // region Métodos
    @Override
    public UsuarioDTO obtenerUsuarioPorId(Long id) {
        Usuario usuario = usuarioRepository.findUsuarioById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(usuario.getId());
        usuarioDTO.setCorreo(usuario.getCorreo());
        usuarioDTO.setContrasenia(usuario.getContrasenia());
        usuarioDTO.setFecha_registro(usuario.getFechaRegistro());
        usuarioDTO.setFecha_acceso(usuario.getFechaAcceso());

        return usuarioDTO;
    }

    @Override
    public ArrayList<UsuarioDTO> obtenerTodosLosUsuarios() {
        try{
            ArrayList<Usuario> usuarios = usuarioRepository.getAllUsuarios().orElseThrow(() -> new RuntimeException("No hay usuarios"));
            if (!usuarios.isEmpty()) {
                ArrayList<UsuarioDTO> usuariosDTO = new ArrayList<>();
                for (Usuario usuario : usuarios) {
                    UsuarioDTO usuarioDTO = new UsuarioDTO();
                    usuarioDTO.setId(usuario.getId());
                    usuarioDTO.setCorreo(usuario.getCorreo());
                    usuarioDTO.setContrasenia(usuario.getContrasenia());
                    usuarioDTO.setFecha_registro(usuario.getFechaRegistro());
                    usuarioDTO.setFecha_acceso(usuario.getFechaAcceso());
                    usuariosDTO.add(usuarioDTO);
                }
                return usuariosDTO;
            }else {
                return null;
            }
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public UsuarioDTO crearUsuario(UsuarioDTO usuarioDTO) {
        String contrasenia = passwordEncoder.encode(usuarioDTO.getContrasenia());
        usuarioRepository.insertarUsuario(usuarioDTO.getCorreo(), contrasenia, usuarioDTO.getFecha_acceso(), usuarioDTO.getFecha_registro());
        return usuarioDTO;
    }

    @Override
    public UsuarioDTO actualizarUsuario(UsuarioDTO usuarioDTO) {
        String contrasenia = passwordEncoder.encode(usuarioDTO.getContrasenia());
        usuarioRepository.actualizarUsuarioPorId(usuarioDTO.getCorreo(), contrasenia, usuarioDTO.getFecha_acceso(), usuarioDTO.getId());
        return usuarioDTO;
    }

    @Override
    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteUsuarioById(id);
    }

    @Override
    public UsuarioDTO obtenerUsuarioPorCorreo(String correo) {
        Usuario usuario = usuarioRepository.findUsuarioByCorreo(correo).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(usuario.getId());
        usuarioDTO.setCorreo(usuario.getCorreo());
        usuarioDTO.setContrasenia(usuario.getContrasenia());
        usuarioDTO.setFecha_registro(usuario.getFechaRegistro());
        usuarioDTO.setFecha_acceso(usuario.getFechaAcceso());

        return usuarioDTO;
    }
    // endregion
}
