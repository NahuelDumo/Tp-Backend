package utn.frc.utn.edu.ar.alquileres_api.Controller;

import org.springframework.web.bind.annotation.*;
import utn.frc.utn.edu.ar.alquileres_api.Controller.Request.AlquilerPatchDto;
import utn.frc.utn.edu.ar.alquileres_api.Controller.Request.AlquilerRequestDto;
import utn.frc.utn.edu.ar.alquileres_api.Controller.Response.AlquilerResponseDto;
import utn.frc.utn.edu.ar.alquileres_api.Services.AlquilerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import utn.frc.utn.edu.ar.alquileres_api.Services.AlquilerServiceImpl;

import java.util.List;


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

    @PatchMapping()
    public ResponseEntity<AlquilerResponseDto> finalizarAlquiler( @RequestBody AlquilerPatchDto dto){
        AlquilerResponseDto response = alquilerService.finalizarAlquiler(dto.getIdAlquiler(), dto.getIdEstacion(), dto.getTipoMoneda());
        return new ResponseEntity<AlquilerResponseDto>(response, HttpStatusCode.valueOf(201));
    }

    @GetMapping()
    public ResponseEntity<List<AlquilerResponseDto>> AlquilerFiltrado(@RequestParam Float costo){
        List<AlquilerResponseDto> response = alquilerService.alquilerFiltroCosto(costo);
        if (response.isEmpty())return new ResponseEntity<List<AlquilerResponseDto>>(response, HttpStatusCode.valueOf(200));
        return new ResponseEntity<List<AlquilerResponseDto>>(response, HttpStatusCode.valueOf(201));

    }



}


