package io.github.MyRent.myrent.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {
    private long id;
    private String correo;
    private String Contrasenia;
    private LocalDateTime fecha_registro;
    private LocalDateTime fecha_acceso;
}
