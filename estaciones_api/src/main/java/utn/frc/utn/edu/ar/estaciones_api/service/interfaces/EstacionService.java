package utn.frc.utn.edu.ar.estaciones_api.service.interfaces;

import utn.frc.utn.edu.ar.estaciones_api.controller.request.CreateEstacionRequestDTO;
import utn.frc.utn.edu.ar.estaciones_api.controller.response.EstacionResponseDTO;

import java.util.List;

public interface EstacionService {

    public List<EstacionResponseDTO> getAll();

    public EstacionResponseDTO add(CreateEstacionRequestDTO dto);

    public Boolean existsById(Long id);

    public Double getDistanceBetweenStations(Long idInicio, Long idFin);

    public EstacionResponseDTO getClosestStation(Double latitud, Double longitud);

}


// ID CLIENTE. De donde lo sacamos?

