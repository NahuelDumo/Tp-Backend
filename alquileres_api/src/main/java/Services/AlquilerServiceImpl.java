package Services;

import Controller.Response.AlquilerResponseDto;
import entidades.Alquiler;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


@Service
public class AlquilerServiceImpl implements AlquilerService{
    @Override
    public AlquilerResponseDto add(Long estacionRetiro, Long idCliente) {
        try {
            // Creación de la instancia de RequestTemplate
            RestTemplate template = new RestTemplate();
            // Se realiza una petición a http://localhost:8082/api/personas/{id}, indicando que id vale 1 y que la
            // respuesta de la petición tendrá en su cuerpo a un objeto del tipo Persona.
            ResponseEntity<Boolean> res = template.getForEntity(
                    "http://localhost:8082/api/estaciones/{id}", Boolean.class, estacionRetiro
            );

            // Se comprueba si el código de repuesta es de la familia 200
            if (res.getBody()) {
                System.out.println("Si papilo");
            } else {
                System.out.println("F papilo");

            }

        } catch (HttpClientErrorException ex) {
            // La repuesta no es exitosa.

        }

        return null;
    }
}
