package utn.frc.utn.edu.ar.estaciones_api.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor


@Table(name="ESTACIONES")
public class Estacion {

    @Id
    @Column(name="ID")
    @GeneratedValue(generator = "ESTACIONES")
    @TableGenerator(name = "ESTACIONES", table = "sqlite_sequence",
            pkColumnName = "name", valueColumnName = "seq",
            pkColumnValue="ESTACIONES",
            initialValue=1, allocationSize=1)
    private Long id;

    @Column(name="NOMBRE")
    private String nombre;

    @Column(name="FECHA_HORA_CREACION")
    private LocalDateTime fechaHoraCreacion;

    @Column(name="LATITUD")
    private Double latitud;

    @Column(name="LONGITUD")
    private Double longitud;

    public Estacion(String nombre, LocalDateTime fechaHoraCreacion, Double latitud, Double longitud) {
        this.nombre = nombre;
        this.fechaHoraCreacion = fechaHoraCreacion;
        this.latitud = latitud;
        this.longitud = longitud;
    }
}
