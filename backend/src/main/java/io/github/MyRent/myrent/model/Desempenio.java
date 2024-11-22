package io.github.MyRent.myrent.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table( name = "desempenio")
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
