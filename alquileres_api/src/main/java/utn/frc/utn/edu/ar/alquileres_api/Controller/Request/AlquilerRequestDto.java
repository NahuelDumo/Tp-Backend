package utn.frc.utn.edu.ar.alquileres_api.Controller.Request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlquilerRequestDto {
    private Long IdEstacion;
    private Long idCliente;
}