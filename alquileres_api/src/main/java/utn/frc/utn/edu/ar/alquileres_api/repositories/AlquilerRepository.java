package utn.frc.utn.edu.ar.alquileres_api.repositories;


import org.springframework.data.jpa.repository.Query;
import utn.frc.utn.edu.ar.alquileres_api.entidades.Alquiler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlquilerRepository extends JpaRepository<Alquiler, Long> {
    @Query("SELECT a FROM Alquiler a WHERE a.monto >= :costo")
    List<Alquiler> findAllByCostoGreaterThan(Float costo);
}
