package utn.frc.utn.edu.ar.alquileres_api.Services;

import jakarta.transaction.Transactional;
import org.springframework.http.HttpEntity;
import utn.frc.utn.edu.ar.alquileres_api.Controller.Response.AlquilerResponseDto;
import utn.frc.utn.edu.ar.alquileres_api.Services.Mappers.AlquilerResponseMapper;
import utn.frc.utn.edu.ar.alquileres_api.entidades.Alquiler;
import utn.frc.utn.edu.ar.alquileres_api.entidades.Tarifa;
import utn.frc.utn.edu.ar.alquileres_api.exceptions.InvalidRequestException;
import utn.frc.utn.edu.ar.alquileres_api.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import utn.frc.utn.edu.ar.alquileres_api.repositories.AlquilerRepository;
import utn.frc.utn.edu.ar.alquileres_api.repositories.TarifaRepository;

import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    @Override
    @Transactional
    public AlquilerResponseDto finalizarAlquiler(Long idAlquiler, Long idEstacion, String tipoMoneda) {
        Alquiler alquiler = alquilerRepository.findById(idAlquiler).orElseThrow(() -> new ResourceNotFoundException("No fue encontrada el alquiler de ID: " + idAlquiler));
        if (alquiler.getEstado() != 1) {
            throw new ResourceNotFoundException("El alquiler de ID: " + idAlquiler + " ya esta finalizado");
        }

        if (!existeEstacion(idEstacion)) {
            throw new ResourceNotFoundException("No fue encontrada la estación de ID: " + idEstacion);
        }

        Double distanciaMetros = DistanceBetweenStations(idEstacion, alquiler.getEstacionRetiro());
        LocalDateTime fechaHoraActual = LocalDateTime.now();
        Float costoTotal = alquiler.calcularCosto(distanciaMetros, fechaHoraActual);
        alquiler.finalizar(idEstacion, costoTotal, fechaHoraActual);

        alquilerRepository.save(alquiler);

        alquiler.setMonto(obtenerCostoTipoMoneda(tipoMoneda, alquiler.getMonto()));
        return alquilerResponseMapper.apply(alquiler);

    }

    private Float obtenerCostoTipoMoneda(String tipoMoneda, Float monto) {
        RestTemplate template = new RestTemplate();
        String url = "http://34.82.105.125:8080/convertir";

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("moneda_destino", tipoMoneda);
        requestBody.put("importe", monto);

        ResponseEntity<Map> responseEntity = template.postForEntity(url, requestBody, Map.class);

        Map responseBody = responseEntity.getBody();
        if (responseBody != null && responseBody.containsKey("importe")) {
            return ((Number) responseBody.get("importe")).floatValue();

        }
        throw new InvalidRequestException("El tipo de moneda es invalido");

    }

    private Double DistanceBetweenStations(Long idEstacionOrigen, Long idEstacionDestino) {
        // Creación de la instancia de RequestTemplate
        RestTemplate template = new RestTemplate();
        String url = "http://localhost:8082/api/estaciones/?idEstacionOrigen={idEstacionOrigen}&idEstacionDestino={idEstacionDestino}";
        ResponseEntity<Double> res = template.getForEntity(url, Double.class, idEstacionOrigen, idEstacionDestino);

        return res.getBody();
    }

    public List<AlquilerResponseDto> alquilerFiltroCosto(Float costo){
        List<Alquiler> alquilerList = alquilerRepository.findAllByCostoGreaterThan(costo);
        return alquilerList.stream().map(alquiler -> alquilerResponseMapper.apply(alquiler)).toList();
    }


}

