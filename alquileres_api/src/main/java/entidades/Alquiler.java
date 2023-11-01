package entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Alquileres")
public class Alquiler {
    @Id
    @Column(name = "Id")

    private long id;

    @Column(name = "Estado")
    private long idCliente;

    @Column(name = "Estacion_Retiro")
    private long estado;

    @Column(name = "Estacion_Devolucion")
    private long estacionRetiro;

    @Column(name = "Fecha_Hora_Retiro")
    private LocalDateTime fechaHoraRetiro;

    @Column(name = "Fecha_Hora_Devolucion")
    private LocalDateTime fechaHoraDevolucion;

    @Column(name = "Monto")
    private long monto;

    @Column(name = "Id_Tarifa")
    private long idTarifa;

}
