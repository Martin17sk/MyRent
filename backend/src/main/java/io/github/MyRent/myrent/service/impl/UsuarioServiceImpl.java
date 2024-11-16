package io.github.MyRent.myrent.service.impl;

import io.github.MyRent.myrent.model.Usuario;
import io.github.MyRent.myrent.model.UsuarioDTO;
import io.github.MyRent.myrent.repository.UsuarioRepository;
import io.github.MyRent.myrent.service.UsuarioService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Override
    public UsuarioDTO obtenerUsuarioPorId(Long id) {
        return null;
    }

    @Override
    public List<UsuarioDTO> obtenerTodosLosUsuarios() {
        return List.of();
    }

    @Override
    public UsuarioDTO crearUsuario(UsuarioDTO usuarioDTO) {
        return null;
    }

    @Override
    public void eliminarUsuario(Long id) {

    }
}
