package io.github.MyRent.myrent.repository;

import io.github.MyRent.myrent.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findUsuarioById(Long id);

    Optional<Usuario> findUsuarioByCorreo(String correo);
    @Query(value = "SELECT * FROM usuario", nativeQuery = true)
    Optional<ArrayList<Usuario>> getAllUsuarios();

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO usuario(`correo`, `contrasenia`, `fecha_acceso`, `fecha_registro`) values (:correo, :contrasenia, :fecha_acceso, :fecha_registro)", nativeQuery = true)
    void insertarUsuario(@Param("correo") String correo,@Param("contrasenia") String contrasenia, @Param("fecha_acceso") LocalDateTime fecha_acceso, @Param("fecha_registro") LocalDateTime fecha_registro);

    @Modifying
    @Transactional
    @Query(value = "UPDATE usuario SET correo = :correo, contrasenia = :contrasenia, fecha_acceso= :fecha_acceso WHERE id = :id", nativeQuery = true)
    void actualizarUsuarioPorId(@Param("correo") String correo, @Param("contrasenia") String contrasenia, @Param("fecha_acceso")LocalDateTime fecha_acceso, @Param("id") long id);

    void deleteUsuarioById(Long id);

}