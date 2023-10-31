package utn.frc.utn.edu.ar.estaciones_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import utn.frc.utn.edu.ar.estaciones_api.domain.Estacion;

import java.util.List;

@Repository
public interface EstacionRepository extends JpaRepository<Estacion, Long> {


    @Query("SELECT e FROM Estacion e " +
            "ORDER BY SQRT((e.latitud - :latitud) * (e.latitud - :latitud) + (e.longitud - :longitud) * (e.longitud - :longitud)) ")
    public List<Estacion> findEstacionesOrdenadas(Double latitud, Double longitud);

}
