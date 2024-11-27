package io.github.MyRent.myrent.service;

import io.github.MyRent.myrent.dto.PerfilDTO;

import java.util.List;

public interface PerfilService {

    void crearPerfil(String nombre, long id_usuario);

    void addEmpleado(Long idPerfil, Long idEmpleado);

    void actualizarPerfil(PerfilDTO perfil);



    List<PerfilDTO> conseguirTodosLosPerfilesPorIdUsuario(Long id);
    PerfilDTO conseguirPerfilPorId(Long id);
}
