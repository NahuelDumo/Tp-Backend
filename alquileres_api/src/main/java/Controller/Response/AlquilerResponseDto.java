package Controller.Response;


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
    private Long estado;
    private Long estacionRetiro;
    private LocalDateTime fechaHoraRetiro;
    private LocalDateTime fechaHoraDevolucion;
    private Float monto;
    private Long idTarifa;

}
