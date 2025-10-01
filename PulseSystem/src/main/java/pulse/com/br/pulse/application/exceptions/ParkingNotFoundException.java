package pulse.com.br.pulse.application.exceptions;

public class ParkingNotFoundException extends RuntimeException {
    public ParkingNotFoundException(String message) {
        super(message);
    }
}
