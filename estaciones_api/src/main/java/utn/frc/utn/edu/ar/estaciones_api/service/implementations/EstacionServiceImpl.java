package utn.frc.utn.edu.ar.estaciones_api.service.implementations;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import utn.frc.utn.edu.ar.estaciones_api.controller.request.CreateEstacionRequestDTO;
import utn.frc.utn.edu.ar.estaciones_api.controller.response.EstacionResponseDTO;
import utn.frc.utn.edu.ar.estaciones_api.domain.Estacion;
import utn.frc.utn.edu.ar.estaciones_api.exception.InvalidRequestException;
import utn.frc.utn.edu.ar.estaciones_api.repository.EstacionRepository;
import utn.frc.utn.edu.ar.estaciones_api.service.interfaces.EstacionService;
import utn.frc.utn.edu.ar.estaciones_api.service.mappers.GetEstacionResponseMapper;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class EstacionServiceImpl implements EstacionService {

    private EstacionRepository estacionRep;
    private GetEstacionResponseMapper estacionResponseMapper;
    @Override
    public List<EstacionResponseDTO> getAll() {

        List<Estacion> estaciones = estacionRep.findAll();
        return estaciones.stream().map(estacionResponseMapper).toList();
    }

    @Override
    public EstacionResponseDTO add(CreateEstacionRequestDTO dto) {


        if (!validarLatitudLongitud(dto.getLatitud(), dto.getLongitud())) {
            throw new InvalidRequestException("La longitud y/o longitud son invalidos.");

        }
        LocalDateTime dateTime = LocalDateTime.now();
        Estacion nuevaEstacion = new Estacion(dto.getNombre(), dateTime, dto.getLatitud(), dto.getLongitud());
        Estacion guardada = estacionRep.save(nuevaEstacion);
        return estacionResponseMapper.apply(guardada);

    }

    @Override
    public Boolean existsById(Long id) {
        return estacionRep.existsById(id);
    }

    @Override
    public Double getDistanceBetweenStations(Long idInicio, Long idFin) {

        Estacion estacionOrigen = estacionRep.findById(idInicio).orElseThrow(()-> new InvalidRequestException("No se ha encontrado la estacion con ID: " + idInicio));
        Estacion estacionDestino = estacionRep.findById(idFin).orElseThrow(()-> new InvalidRequestException("No se ha encontrado la estacion con ID: " + idInicio));
        Double distanciaMetros = calcularDistanciaMetros(estacionOrigen.getLatitud(), estacionOrigen.getLongitud(), estacionDestino.getLatitud(), estacionDestino.getLongitud());
        return distanciaMetros;

    }

    @Override
    public EstacionResponseDTO getClosestStation(Double latitud, Double longitud) {
        List<Estacion> estacion = estacionRep.findEstacionesOrdenadas(latitud, longitud);
        return estacionResponseMapper.apply(estacion.get(0));

    }


    private Boolean validarLatitudLongitud(Double latitud, Double longitud) {

        return (Math.abs(latitud) < 90.0f & Math.abs(longitud) < 180.0f);

    }

    private Double calcularDistanciaMetros(Double latitudInicio, Double longitudInicio, Double latitudFin, Double longitudFin) {

        Double longDistancia = longitudFin - longitudInicio;
        Double latDistancia = latitudFin - latitudInicio;
        Double gradosDistancia =  (Math.sqrt(Math.pow(longDistancia,2) + Math.pow(latDistancia, 2)));

        return gradosDistancia * 110000;



    }

}
