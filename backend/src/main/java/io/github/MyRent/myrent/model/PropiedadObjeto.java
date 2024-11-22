package io.github.MyRent.myrent.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "propiedad_objeto")
public class PropiedadObjeto {
    @EmbeddedId
    private PropiedadObjetoKey id;
    @ManyToOne
    @MapsId("propiedadId")
    @JoinColumn(name = "propiedad_id")
    private Propiedad propiedad;
    @ManyToOne
    @MapsId("objetoId")
    @JoinColumn(name = "objeto_id")
    private Objeto objeto;
    private int cantidad;
}