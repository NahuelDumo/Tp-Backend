package utn.frc.utn.edu.ar.alquileres_api.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Alquileres")
public class Alquiler {
    @Id
    @GeneratedValue(generator = "ALQUILERES")
    @TableGenerator(name = "ALQUILERES", table = "sqlite_sequence",
            pkColumnName = "name", valueColumnName = "seq",
            pkColumnValue="ALQUILERES",
            initialValue=0, allocationSize=1)
    @Column(name = "ID")
    private Long id;

    @Column(name = "ID_CLIENTE")
    private Long idCliente;

    @Column(name = "ESTADO")
    private Long estado;

    @Column(name = "ESTACION_RETIRO")
    private Long estacionRetiro;

    @Column(name = "ESTACION_DEVOLUCION", nullable = true)
    private Long estacionDevolucion;

    @Column(name = "FECHA_HORA_RETIRO")
    private LocalDateTime fechaHoraRetiro;

    @Column(name = "FECHA_HORA_DEVOLUCION", nullable = true)
    private LocalDateTime fechaHoraDevolucion;

    @Column(name = "Monto")
    private Float monto;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name="ID_TARIFA", referencedColumnName = "ID")
    private Tarifa tarifa;

    public Alquiler(long idCliente, long estado,
                    long estacionRetiro,
                    LocalDateTime fechaHoraRetiro,
                    Tarifa tarifa) {

        this.idCliente = idCliente;
        this.estado = estado;
        this.estacionRetiro = estacionRetiro;
        this.estacionDevolucion = null;
        this.fechaHoraRetiro = fechaHoraRetiro;
        this.fechaHoraDevolucion = null;
        this.monto = 0f;
        this.tarifa = tarifa;
    }

    public Long getIdTarifa() {
        return tarifa.getId();
    }
}
