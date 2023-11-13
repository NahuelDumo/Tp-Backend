package utn.frc.utn.edu.ar.alquileres_api.Controller.Response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlquilerResponseDto {
    private Long Id;
    private Long idCliente;
    private int estado;
    private Long estacionRetiro;
    private Long estacionDevolucion;
    private LocalDateTime fechaHoraRetiro;
    private LocalDateTime fechaHoraDevolucion;
    private Float monto;
    private Long idTarifa;

}
