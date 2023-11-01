package utn.frc.utn.edu.ar.estaciones_api.controller;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn.frc.utn.edu.ar.estaciones_api.controller.request.CreateEstacionRequestDTO;
import utn.frc.utn.edu.ar.estaciones_api.controller.response.EstacionResponseDTO;
import utn.frc.utn.edu.ar.estaciones_api.service.interfaces.EstacionService;

import java.util.List;

@RestController
@RequestMapping("/api/estaciones")
@AllArgsConstructor
public class EstacionController {

    private EstacionService estacionService;

    @GetMapping()

    public ResponseEntity<List<EstacionResponseDTO>> getAll() {

        List<EstacionResponseDTO> response = estacionService.getAll();
        return new ResponseEntity<List<EstacionResponseDTO>>(response, HttpStatusCode.valueOf(200));

    }

    @PostMapping()
    public ResponseEntity<EstacionResponseDTO> add(@RequestBody CreateEstacionRequestDTO dto) {

        EstacionResponseDTO response = estacionService.add(dto);
        return new ResponseEntity<EstacionResponseDTO>(response, HttpStatusCode.valueOf(201));

    }

    @GetMapping("/{id}")
    public ResponseEntity<Boolean> existsById(@PathVariable Long id) {

        Boolean response = estacionService.existsById(id);
        return new ResponseEntity<Boolean>(response, HttpStatusCode.valueOf(200));
    }

    @GetMapping("/")
    public ResponseEntity<Double> getDistanceBetweenStations(@RequestParam("idEstacionOrigen") Long idEstacionOrigen, @RequestParam("idEstacionDestino") Long idEstacionDestino) {

        Double response = estacionService.getDistanceBetweenStations(idEstacionOrigen, idEstacionDestino);
        return new ResponseEntity<Double>(response, HttpStatusCode.valueOf(200));

    }

    @GetMapping("/closest")
    public ResponseEntity<EstacionResponseDTO> getClosestStation(@RequestParam("latitud") Double latitud, @RequestParam("longitud") Double longitud) {

        EstacionResponseDTO response = estacionService.getClosestStation(latitud, longitud);
        return new ResponseEntity<EstacionResponseDTO>(response, HttpStatusCode.valueOf(200));

    }


}
