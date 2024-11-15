package io.github.MyRent.myrent.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import java.time.LocalDateTime;
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
    @Column(name = "fecha_registro", nullable = false)
    @CreationTimestamp
    private LocalDateTime fechaRegistro;
    @Column(name = "fecha_acceso", nullable = false)
    @UpdateTimestamp
    private LocalDateTime fechaAcceso;
    @OneToMany(mappedBy = "usuario")
    private Set<Perfil> perfiles;
}
