package io.github.MyRent.myrent.repository;

import io.github.MyRent.myrent.model.Propiedad;
import io.github.MyRent.myrent.model.TipoTransaccion;
import io.github.MyRent.myrent.model.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface TransaccionRepository extends JpaRepository<Transaccion, Long> {
    @Query("SELECT t FROM Transaccion t " +
            "JOIN Propiedad p ON t.propiedad.id = p.id " +
            "JOIN Perfil pf ON p.perfil.id = pf.id " +
            "WHERE pf.usuario.id = :usuarioId")
    List<Transaccion> findByUsuarioId(@Param("usuarioId") Long usuarioId);
    @Query("SELECT t FROM Transaccion t " +
            "JOIN Propiedad p ON t.propiedad.id = p.id " +
            "WHERE p.perfil.id = :perfilId")
    List<Transaccion> findByPerfilId(@Param("perfilId") Long perfilId);
    List<Transaccion> findByPropiedadId(Long propiedadId);

    List<Transaccion> findByPropiedadIdAndTipoTransaccion(Long propiedadId, TipoTransaccion tipoTransaccion);
    @Query("SELECT t FROM Transaccion t " +
            "JOIN Propiedad p ON t.propiedad.id = p.id " +
            "JOIN Perfil pf ON p.perfil.id = pf.id " +
            "WHERE pf.usuario.id = :usuarioId AND t.tipoTransaccion = :tipoTransaccion")
    List<Transaccion> findByUsuarioIdAndTipoTransaccion(@Param("usuarioId") Long usuarioId, @Param("tipoTransaccion") TipoTransaccion tipoTransaccion);

    @Query("SELECT t FROM Transaccion t " +
            "JOIN Propiedad p ON t.propiedad.id = p.id " +
            "WHERE p.perfil.id = :perfilId AND t.tipoTransaccion = :tipoTransaccion")
    List<Transaccion> findByPerfilIdAndTipoTransaccion(@Param("perfilId") Long perfilId, @Param("tipoTransaccion") TipoTransaccion tipoTransaccion);

    List<Transaccion> findByPropiedadIdAndTipoTransaccionAndFechaBetween(Long propiedad_id, TipoTransaccion tipoTransaccion, java.util.Date fecha, java.util.Date fecha2);
    @Query("SELECT DISTINCT t.propiedad FROM Transaccion t")
    List<Propiedad> findDistinctPropiedades();
}
