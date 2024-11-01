package io.github.MyRent.myrent.model;

import jakarta.persistence.*;
import lombok.Data;
import java.sql.Timestamp;

@Entity
@Table(name = "usuario")
@Data
public class Usuario {
    @Id
    @Column (name = "id_usuario")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUsuario;
    private String correo;
    private String contrasenia;
    private Timestamp fecha_registro;
    private Timestamp fecha_acceso;
}
