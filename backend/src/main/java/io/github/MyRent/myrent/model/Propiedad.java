package io.github.MyRent.myrent.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "propiedad")
@Entity
public class Propiedad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String direccion;
    private int capacidad;
    private int dormitorios;
    private int banos;
    private float latitud;
    private float longitud;
    private float largo;
    private float ancho;
    private float alto;
    @Enumerated(EnumType.STRING)
    private Medida medida;
    private float precio;
    private boolean disponibilidad;
    private Date fechaCreacion;

    @OneToMany(mappedBy = "propiedad")
    private Set<Desempenio> desempenios;
    @OneToMany(mappedBy = "propiedad")
    private Set<TransaccionFinanciera> transacciones;
    @OneToMany(mappedBy = "propiedad")
    private Set<PropiedadObjeto> propiedadesObjetos;


    @ManyToOne
    @JoinColumn(name = "perfil_id")
    private Perfil perfil;


    @ManyToMany
    @JoinTable(
            name = "propiedad_servicio",
            joinColumns = @JoinColumn(name = "propiedad_id"),
            inverseJoinColumns = @JoinColumn(name = "servicio_id")
    )
    private Set<Servicio> servicios;
    @ManyToMany
    @JoinTable(
            name = "propiedad_empleado",
            joinColumns = @JoinColumn(name = "propiedad_id"),
            inverseJoinColumns = @JoinColumn(name = "empleado_id")
    )
    private Set<Empleado> empleados;

}
