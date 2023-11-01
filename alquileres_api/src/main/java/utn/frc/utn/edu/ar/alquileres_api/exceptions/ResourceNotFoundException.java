package utn.frc.utn.edu.ar.alquileres_api.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException (String message) {
        super(message);
    }

    public ResourceNotFoundException () {
        super();
    }
}
