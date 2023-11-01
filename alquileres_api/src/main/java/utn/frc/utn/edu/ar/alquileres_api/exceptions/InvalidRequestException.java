package utn.frc.utn.edu.ar.alquileres_api.exceptions;

public class InvalidRequestException extends RuntimeException {

    public InvalidRequestException (String message) {
        super(message);
    }

    public InvalidRequestException () {
        super();
    }

}
