package ma.enset.event_driven_architecture.common_api.exceptions;

public class BalanceNotSufficientException extends RuntimeException {
    public BalanceNotSufficientException(String message) {
        super(message);

    }
}
