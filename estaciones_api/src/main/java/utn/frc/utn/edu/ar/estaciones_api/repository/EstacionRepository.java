package utn.frc.utn.edu.ar.estaciones_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utn.frc.utn.edu.ar.estaciones_api.domain.Estacion;

@Repository
public interface EstacionRepository extends JpaRepository<Estacion, Long> {
}
