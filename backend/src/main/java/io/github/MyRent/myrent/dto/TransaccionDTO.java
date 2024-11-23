package io.github.MyRent.myrent.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransaccionDTO {
    private Long id;
    @NotNull
    private Long propiedadId;
    @NotBlank
    @Pattern(regexp = "^(COSTE|GASTO|INGRESO)$", message = "El tipo de transacción debe ser 'COSTE' o 'GASTO' o 'INGRESO'")
    private String tipoTransaccion;
    @PastOrPresent(message = "La fecha debe ser pasada o presente")
    private Date fecha;
    @Size(max = 255, message = "La descripción no puede tener más de 255 caracteres")
    private String descripcion;
    @Positive(message = "El monto debe ser mayor a 0")
    private int monto;
}
