package io.github.MyRent.myrent.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
@Data
@Entity
public class Arriendo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "propiedad_id")
    private Propiedad propiedad;
    @Column(name = "fecha_inicio")
    private Date fechaInicio;
    @Column(name = "fecha_termino")
    private Date fechaTermino;
}
