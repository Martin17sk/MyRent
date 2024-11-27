package io.github.MyRent.myrent.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PerfilDTO {
    private long id;
    private String nombre;
    private LocalDateTime fechaCreacion;
    private Long usuario_id;
    private Set<EmpleadoDTO> empleados;
}
