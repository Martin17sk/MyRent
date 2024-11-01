package io.github.MyRent.myrent.repository;

import io.github.MyRent.myrent.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByIdUsuario(Long id);
}