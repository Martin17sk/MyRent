package io.github.MyRent.myrent.service.impl;

import io.github.MyRent.myrent.dto.PerfilDTO;
import io.github.MyRent.myrent.model.*;
import io.github.MyRent.myrent.repository.EmpleadoRepository;
import io.github.MyRent.myrent.repository.PerfilRepository;
import io.github.MyRent.myrent.repository.UsuarioRepository;
import io.github.MyRent.myrent.service.PerfilService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PerfilServiceImpl implements PerfilService {
    private final PerfilRepository perfilRepository;
    private final UsuarioRepository usuarioRepository;
    private final EmpleadoRepository empleadoRepository;

    public PerfilServiceImpl(PerfilRepository perfilRepo, UsuarioRepository usuarioRepo, EmpleadoRepository empleadoRepository) {
        this.usuarioRepository = usuarioRepo;
        this.perfilRepository = perfilRepo;
        this.empleadoRepository = empleadoRepository;
    }

    @Override
    @Transactional
    public void crearPerfil(String nombre, long id_usuario) {
        Usuario usuario = usuarioRepository.findUsuarioById(id_usuario).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Perfil perfil = new Perfil();
        perfil.setNombre(nombre);
        perfil.setUsuario(usuario);

    }

    @Override
    @Transactional
    public void addEmpleado(Long idPerfil, Long idEmpleado) {
        Perfil perfil = perfilRepository.findById(idPerfil).orElseThrow(() -> new RuntimeException("Perfil no encontrado"));
        Empleado empleado = empleadoRepository.getEmpleadoById(idEmpleado).orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
        perfil.addEmpleado(empleado);
    }

    @Override
    public void actualizarPerfil(PerfilDTO perfil) {
        new PerfilDTO();

    }

    @Override
    public List<PerfilDTO> conseguirTodosLosPerfilesPorIdUsuario(Long id) {

        List<Perfil> perfiles = perfilRepository.findPerfilesByUsuarioId(id);

        return perfiles.stream()
                .map(perfil -> {
                    PerfilDTO perfilDTO = new PerfilDTO();
                    perfilDTO.setId(perfil.getId());
                    perfilDTO.setNombre(perfil.getNombre());
                    perfilDTO.setFechaCreacion(perfil.getFechaCreacion());
                    perfilDTO.setUsuario_id(perfil.getUsuario().getId());
                    return perfilDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public PerfilDTO conseguirPerfilPorId(Long id) {
        return null;
    }
}
