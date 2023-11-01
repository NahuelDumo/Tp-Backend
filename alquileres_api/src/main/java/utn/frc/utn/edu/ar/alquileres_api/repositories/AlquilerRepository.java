package utn.frc.utn.edu.ar.alquileres_api.repositories;


import utn.frc.utn.edu.ar.alquileres_api.entidades.Alquiler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlquilerRepository extends JpaRepository<Alquiler, Long> {

}
