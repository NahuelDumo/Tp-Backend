package entidades;


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

    private long id;

    @Column(name = "TIPO_TARIFA")
    private long tipoTarifa;

    @Column(name = "Definicion")
    private char definicion ;

    @Column(name = "DIA_SEMANA")
    private int diaSemana;

    @Column(name = "DIA_MES")
    private int diaMes;

    @Column(name = "MES")
    private int mes;

    @Column(name = "ANIO")
    private int anio;

    @Column(name = "MONTO_FIJO_ALQUILER")
    private int montoFijoAlquiler;

    @Column(name = "MONTO_MINUTO_FRACCION")
    private float montoMinutoFraccion;

    @Column(name = "MONTO_KM")
    private float montoKM;

    @Column(name = "MONTO_HORA")
    private float montoHora;



}
