package io.github.MyRent.myrent.repository;

import io.github.MyRent.myrent.model.Propiedad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropiedadRepository extends JpaRepository<Propiedad, Long> {
}
