package io.github.MyRent.myrent.repository;

import io.github.MyRent.myrent.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByIdUsuario(int id);
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO usuario(`correo`, `contrasenia`) values (:correo, :contrasenia)", nativeQuery = true)
    void insertarUsuario(@Param("correo") String correo,@Param("contrasenia") String contrasenia);

}