package io.github.MyRent.myrent.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.YearMonth;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DesempenioDTO {
    private Long id;
    @NotNull
    @Positive(message = "El id de la propiedad debe ser mayor a 0")
    private Long propiedadId;
    private YearMonth fecha;
    private float ocupacion;
    private float ingresos;
    private float gastos;
    private float costes;
    private float beneficios;
}
