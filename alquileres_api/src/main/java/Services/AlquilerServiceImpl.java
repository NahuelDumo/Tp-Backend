package Services;

import Controller.Response.AlquilerResponseDto;
import Services.Mappers.AlquilerResponseMapper;
import entidades.Alquiler;
import entidades.Tarifa;
import exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import repositories.AlquilerRepository;
import repositories.TarifaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;


@Service
@AllArgsConstructor
public class AlquilerServiceImpl implements AlquilerService{

    private AlquilerRepository alquilerRepository;
    private AlquilerResponseMapper alquilerResponseMapper;
    private TarifaRepository tarifaRepository;


    @Override
    public AlquilerResponseDto add(Long estacionRetiro, Long idCliente) {

        if (!existeEstacion(estacionRetiro)) {
            throw new ResourceNotFoundException("No fue encontrada la estacion de ID: " + estacionRetiro);
        }

        LocalDate fechaActual = LocalDate.now();
        LocalTime horaActual = LocalTime.now();
        Tarifa tarifaCorrespondiente = obtenerTarifaCorrespondiente(fechaActual);
        LocalDateTime fechaHoraActual = LocalDateTime.of(fechaActual, horaActual);

        Alquiler alquiler = crearAlquiler(idCliente, estacionRetiro, tarifaCorrespondiente, fechaHoraActual);
        Alquiler creado = alquilerRepository.save(alquiler);
        return alquilerResponseMapper.apply(creado);

    }

    private Boolean existeEstacion(Long estacionRetiro) {

            // Creación de la instancia de RequestTemplate
            RestTemplate template = new RestTemplate();
            ResponseEntity<Boolean> res = template.getForEntity(
                    "http://localhost:8082/api/estaciones/{id}", Boolean.class, estacionRetiro
            );
            //El body devuelve un booleano de si existe o no. Por lo tanto:

            return res.getBody();
        }

    private Tarifa obtenerTarifaCorrespondiente(LocalDate fechaActual) {

        Integer anio = fechaActual.getYear();
        Integer mes = fechaActual.getMonthValue();
        Integer dia = fechaActual.getDayOfMonth();

        Integer diaSemana = fechaActual.getDayOfWeek().getValue();

        Tarifa tarifa = tarifaRepository.findByFechaEspecifica(anio, mes, dia)
                .orElse(
                        tarifaRepository.findByDiaSemana(diaSemana).
                                orElseThrow(() -> new RuntimeException("Ha ocurrido un error en buscar la tarifa del dia de semana.")));
        return tarifa;

    }
    private Alquiler crearAlquiler(Long idCliente, Long idEstacionRetiro, Tarifa tarifa, LocalDateTime fechaHoraActual) {

        Alquiler alquiler = new Alquiler(idCliente, 1, idEstacionRetiro, fechaHoraActual, tarifa);

        return alquiler;

    }

}

