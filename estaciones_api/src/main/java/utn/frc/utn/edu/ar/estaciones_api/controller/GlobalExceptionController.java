package utn.frc.utn.edu.ar.estaciones_api.controller;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import utn.frc.utn.edu.ar.estaciones_api.exception.InvalidRequestException;
import utn.frc.utn.edu.ar.estaciones_api.exception.ResourceNotFoundException;

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


}
