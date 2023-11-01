package utn.frc.utn.edu.ar.alquileres_api.entidades;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Tarifas")
public class Tarifa {
    @Id
    @Column(name = "Id")
    @GeneratedValue(generator = "Tarifas")
    @TableGenerator(name = "Tarifas", table = "sqlite_sequence",
            pkColumnName = "name", valueColumnName = "seq",
            pkColumnValue="Tarifas", initialValue = 1, allocationSize = 1)

    private Long id;

    @Column(name = "TIPO_TARIFA")
    private Long tipoTarifa;

    @Column(name = "Definicion")
    private char definicion ;

    @Column(name = "DIA_SEMANA", nullable = true)
    private Integer diaSemana;

    @Column(name = "DIA_MES", nullable = true)
    private Integer diaMes;

    @Column(name = "MES", nullable = true)
    private Integer mes;

    @Column(name = "ANIO", nullable = true)
    private Integer anio;

    @Column(name = "MONTO_FIJO_ALQUILER")
    private Integer montoFijoAlquiler;

    @Column(name = "MONTO_MINUTO_FRACCION")
    private Float montoMinutoFraccion;

    @Column(name = "MONTO_KM")
    private Float montoKM;

    @Column(name = "MONTO_HORA")
    private Float montoHora;



}
