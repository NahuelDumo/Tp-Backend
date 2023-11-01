package utn.frc.utn.edu.ar.alquileres_api.Services;


import utn.frc.utn.edu.ar.alquileres_api.Controller.Response.AlquilerResponseDto;


public interface AlquilerService {
    public AlquilerResponseDto add(Long estacionRetiro, Long idCliente);


}
