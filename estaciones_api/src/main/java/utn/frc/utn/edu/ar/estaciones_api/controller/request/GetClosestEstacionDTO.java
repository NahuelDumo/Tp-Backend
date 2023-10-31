package utn.frc.utn.edu.ar.estaciones_api.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetClosestEstacionDTO {

    Double latitud;
    Double longitud;

}
