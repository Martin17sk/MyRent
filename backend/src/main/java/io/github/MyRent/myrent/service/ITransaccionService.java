package io.github.MyRent.myrent.service;

import io.github.MyRent.myrent.dto.TransaccionDTO;

import java.util.List;

public interface ITransaccionService {
    TransaccionDTO findById(Long id);
    TransaccionDTO save(TransaccionDTO transaccion);
    void deleteById(Long id);
    List<TransaccionDTO> getTransaccionesByUsuarioId(Long idUsuario);
    List<TransaccionDTO> getTransaccionesByPerfilId(Long idPerfil);
    List<TransaccionDTO> getTransaccionesByPropiedadId(Long idPropiedad);
    List<TransaccionDTO> getTransaccionesByUsuarioIdAndTipo(Long idUsuario, String tipo);
    List<TransaccionDTO> getTransaccionesByPerfilIdAndTipo(Long idPerfil, String tipo);
    List<TransaccionDTO> getTransaccionesByPropiedadIdAndTipo(Long idPropiedad, String tipo);

}
