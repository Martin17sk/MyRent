package io.github.MyRent.myrent.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
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
    private List<Desempenio> desempenios;
    @OneToMany(mappedBy = "propiedad")
    private List<TransaccionFinanciera> transacciones;
    @ManyToOne
    @JoinColumn(name = "perfil_id")
    private Perfil perfil;
    @ManyToMany
    private List<Servicio> servicios;
    @ManyToMany
    private List<Empleado> empleados;
    @ManyToMany
    private List<Objeto> objetos;
}
