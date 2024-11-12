package io.github.MyRent.myrent.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Data
@Entity
public class TransaccionFinanciera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "propiedad_id")
    private Propiedad propiedad;

    @Enumerated(EnumType.STRING)
    private TipoTransaccion tipoTransaccion;
    private Date fecha;
    private String descripcion;
    private int monto;
}