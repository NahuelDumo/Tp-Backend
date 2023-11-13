package utn.frc.utn.edu.ar.alquileres_api.Services.Mappers;


import org.springframework.stereotype.Service;
import utn.frc.utn.edu.ar.alquileres_api.Controller.Response.AlquilerResponseDto;
import utn.frc.utn.edu.ar.alquileres_api.entidades.Alquiler;

import java.util.function.Function;

@Service
public class AlquilerResponseMapper implements Function<Alquiler, AlquilerResponseDto> {
    @Override
    public AlquilerResponseDto apply(Alquiler alquiler) {
        return new AlquilerResponseDto(alquiler.getId(),
                alquiler.getIdCliente(),
                alquiler.getEstado(),
                alquiler.getEstacionRetiro(),
                alquiler.getEstacionDevolucion(),
                alquiler.getFechaHoraRetiro(),
                alquiler.getFechaHoraDevolucion(),
                alquiler.getMonto(),
                alquiler.getIdTarifa());
    }
}
