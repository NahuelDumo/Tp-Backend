package utn.frc.utn.edu.ar.estaciones_api.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException (String message) {
        super(message);
    }

    public ResourceNotFoundException () {
        super();
    }
}
