package io.github.MyRent.myrent.repository;


import io.github.MyRent.myrent.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    Optional<Empleado> getEmpleadoById(Long id);



}
