package utn.frc.utn.edu.ar.estaciones_api.controller.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateEstacionRequestDTO {

    private String nombre;
    private Double latitud;
    private Double longitud;



}
