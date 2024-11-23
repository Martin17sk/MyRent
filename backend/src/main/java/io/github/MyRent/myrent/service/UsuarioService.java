package io.github.MyRent.myrent.service;

import io.github.MyRent.myrent.model.UsuarioDTO;

import java.util.List;

public interface UsuarioService {
    UsuarioDTO obtenerUsuarioPorId(Long id);
    List<UsuarioDTO> obtenerTodosLosUsuarios();
    UsuarioDTO crearUsuario(UsuarioDTO usuarioDTO);
    UsuarioDTO actualizarUsuario(UsuarioDTO usuarioDTO);
    void eliminarUsuario(Long id);
    UsuarioDTO obtenerUsuarioPorCorreo(String correo);
}
