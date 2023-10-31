package utn.frc.utn.edu.ar.estaciones_api.service.mappers;

import org.springframework.stereotype.Service;
import utn.frc.utn.edu.ar.estaciones_api.controller.response.EstacionResponseDTO;
import utn.frc.utn.edu.ar.estaciones_api.domain.Estacion;

import java.util.function.Function;

@Service
public class GetEstacionResponseMapper implements Function<Estacion, EstacionResponseDTO> {

    @Override
    public EstacionResponseDTO apply(Estacion estacion) {
        return new EstacionResponseDTO(estacion.getId(), estacion.getNombre(),estacion.getFechaHoraCreacion(), estacion.getLatitud(), estacion.getLongitud());
    }
}
