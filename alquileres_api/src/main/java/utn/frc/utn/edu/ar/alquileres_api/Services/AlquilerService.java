package utn.frc.utn.edu.ar.alquileres_api.Services;


import utn.frc.utn.edu.ar.alquileres_api.Controller.Response.AlquilerResponseDto;

import java.util.List;


public interface AlquilerService {
    public AlquilerResponseDto add(Long estacionRetiro, Long idCliente);


    AlquilerResponseDto finalizarAlquiler(Long idAlquiler, Long idEstacion, String tipoMoneda);

    List<AlquilerResponseDto> alquilerFiltroCosto(Float costo);
}
