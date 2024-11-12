package io.github.MyRent.myrent.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Timestamp fechaCreacion;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @OneToMany(mappedBy = "perfil")
    private List<Empleado> empleados;
    @OneToMany(mappedBy = "perfil")
    private List<Propiedad> propiedades;
}
