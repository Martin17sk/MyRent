package io.github.MyRent.myrent.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuario")
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String correo;
    private String contrasenia;
    @Column(name = "fecha_registro")
    private Timestamp fechaRegistro;
    @Column(name = "fecha_acceso")
    private Timestamp fechaAcceso;
    @OneToMany(mappedBy = "usuario")
    private Set<Perfil> perfiles;
}
