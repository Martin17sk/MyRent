package io.github.MyRent.myrent.repository;

import io.github.MyRent.myrent.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    @Query(value = "SELECT * FROM usuario WHERE id = :id", nativeQuery = true)
    Optional<Usuario> findByIdUsuario(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO usuario(`correo`, `contrasenia`) values (:correo, :contrasenia)", nativeQuery = true)
    void insertarUsuario(@Param("correo") String correo,@Param("contrasenia") String contrasenia);

    @Modifying
    @Transactional
    @Query(value = "UPDATE usuario SET correo = :correo, contrasenia = :contrasenia, fecha_acceso= :fecha_acceso WHERE id = :id", nativeQuery = true)
    void actualizarUsuarioPorId(@Param("correo") String correo, @Param("contrasenia") String contrasenia, @Param("fecha_acceso")LocalDateTime fecha_acceso, @Param("id") long id);

}