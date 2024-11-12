package io.github.MyRent.myrent.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Data
@Entity
public class Desempenio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date fecha;
    private float ocupacion;
    private float ingresos;
    private float gastos;
    private float costes;
    @Column(name = "beneficio_neto")
    private float beneficios;
    @ManyToOne
    @JoinColumn(name = "propiedad_id")
    private Propiedad propiedad;
}
