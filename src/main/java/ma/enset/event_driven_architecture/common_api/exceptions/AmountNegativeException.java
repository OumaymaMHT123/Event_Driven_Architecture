package ma.enset.event_driven_architecture.common_api.exceptions;

public class AmountNegativeException extends RuntimeException {
    public AmountNegativeException(String message) {
        super(message);

    }
}
