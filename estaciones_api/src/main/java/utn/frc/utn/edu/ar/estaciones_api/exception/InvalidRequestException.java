package utn.frc.utn.edu.ar.estaciones_api.exception;

public class InvalidRequestException extends RuntimeException {

    public InvalidRequestException (String message) {
        super(message);
    }

    public InvalidRequestException () {
        super();
    }

}
