package utn.frc.utn.edu.ar.alquileres_api.Controller;

import utn.frc.utn.edu.ar.alquileres_api.Controller.Request.AlquilerRequestDto;
import utn.frc.utn.edu.ar.alquileres_api.Controller.Response.AlquilerResponseDto;
import utn.frc.utn.edu.ar.alquileres_api.Services.AlquilerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/alquileres")
@AllArgsConstructor
public class AlquilerControler {
    private AlquilerService alquilerService;

    @PostMapping()
    public ResponseEntity<AlquilerResponseDto> add(@RequestBody AlquilerRequestDto dto) {
        AlquilerResponseDto response = alquilerService.add(dto.getIdEstacion(), dto.getIdCliente());
        return new ResponseEntity<AlquilerResponseDto>(response, HttpStatusCode.valueOf(201));

    }
}

