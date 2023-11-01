package utn.frc.utn.edu.ar.alquileres_api.Controller;

import utn.frc.utn.edu.ar.alquileres_api.exceptions.InvalidRequestException;
import utn.frc.utn.edu.ar.alquileres_api.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<String> handleInvalidRequestException(InvalidRequestException ex) {
        return new ResponseEntity<String>(ex.getMessage(), HttpStatusCode.valueOf(400));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleInvalidRequestException(ResourceNotFoundException ex) {
        return new ResponseEntity<String>(ex.getMessage(), HttpStatusCode.valueOf(404));
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<String> handleInvalidHTTPRequest(HttpClientErrorException ex) {
        return new ResponseEntity<String>(ex.getMessage(), HttpStatusCode.valueOf(500));
    }



}