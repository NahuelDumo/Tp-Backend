package Services.Mappers;


import Controller.Response.AlquilerResponseDto;
import entidades.Alquiler;
import java.util.function.Function;

public class AlquilerResponseMappers implements Function<Alquiler, AlquilerResponseDto> {
    @Override
    public AlquilerResponseDto apply(Alquiler alquiler) {
        return new AlquilerResponseDto(alquiler.getId(),
                alquiler.getIdCliente(),
                alquiler.getEstado(),
                alquiler.getEstacionRetiro(),
                alquiler.getFechaHoraRetiro(),
                alquiler.getFechaHoraDevolucion(),
                alquiler.getMonto(),
                alquiler.getIdTarifa());
    }
}
