package io.github.MyRent.myrent.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class PropiedadObjetoKey implements Serializable {
    @Column(name = "propiedad_id")
    private Long propiedadId;
    @Column(name = "objeto_id")
    private Long objetoId;
}
