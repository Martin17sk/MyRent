package io.github.MyRent.myrent.repository;

import io.github.MyRent.myrent.model.Perfil;
import io.github.MyRent.myrent.dto.PerfilDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long> {

    @Query("SELECT p FROM Perfil p WHERE p.usuario.id = :usuarioId")
    List<Perfil> findPerfilesByUsuarioId(@Param("usuarioId") Long usuarioId);

    @Query("SELECT p FROM Perfil p WHERE p.id = :id")
    PerfilDTO getPerfilById(Long id);
}
