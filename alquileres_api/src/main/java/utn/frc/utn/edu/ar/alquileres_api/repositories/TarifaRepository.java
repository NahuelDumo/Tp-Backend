package utn.frc.utn.edu.ar.alquileres_api.repositories;

import utn.frc.utn.edu.ar.alquileres_api.entidades.Tarifa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TarifaRepository extends JpaRepository<Tarifa, Long> {

    @Query("SELECT t FROM Tarifa t WHERE t.anio = :anio AND t.mes = :mes AND t.diaMes = :dia ")
    public Optional<Tarifa> findByFechaEspecifica(Integer anio, Integer mes, Integer dia);

    @Query("SELECT t FROM Tarifa t WHERE t.diaSemana = :diaSemana")
    public Optional<Tarifa> findByDiaSemana(Integer diaSemana);

}
