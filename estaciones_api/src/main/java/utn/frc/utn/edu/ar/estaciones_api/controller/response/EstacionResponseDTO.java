package utn.frc.utn.edu.ar.estaciones_api.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class EstacionResponseDTO {


    private Long id;
    private String nombre;
    private LocalDateTime fechaHoraCreacion;
    private Double latitud;
    private Double longitud;

}
