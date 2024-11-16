package io.github.MyRent.myrent.model;

import jakarta.persistence.*;
import lombok.*;

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

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String direccion;

    private int capacidad;
    private int dormitorios;
    private int banos;
    private float precio;
    private boolean disponibilidad;
    private float latitud;
    private float longitud;

    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;

    private Float largo;
    private Float ancho;
    private Float alto;

    @Enumerated(EnumType.STRING)
    private Medida medida;

    @OneToMany(mappedBy = "propiedad")
    private Set<Desempenio> desempenios;

    @OneToMany(mappedBy = "propiedad")
    private Set<Transaccion> transacciones;

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
