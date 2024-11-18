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
@Table(name = "arriendo")
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
