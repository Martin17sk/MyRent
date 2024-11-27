package io.github.MyRent.myrent.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "empleado")
@Entity
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @ManyToOne
    @JoinColumn(name = "perfil_id")
    private Perfil perfil;
    @ManyToMany
    private Set<Propiedad> propiedades;


    public void addPropiedad(Propiedad propiedad){
        propiedades.add(propiedad);
    }
    public void removePropiedad(Propiedad propiedad){
        propiedades.remove(propiedad);
    }
}
