package io.github.MyRent.myrent.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Set;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "perfil")
@Entity
public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private LocalDateTime fechaCreacion;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @OneToMany(mappedBy = "perfil")
    private Set<Empleado> empleados;
    @OneToMany(mappedBy = "perfil")
    private Set<Propiedad> propiedades;

    public void addEmpleado(Empleado empleado){
        empleados.add(empleado);
        empleado.setPerfil(this);
    }

    public void removeEmpleado(Empleado empleado){
        empleados.remove(empleado);
        empleado.setPerfil(null);
    }
}
