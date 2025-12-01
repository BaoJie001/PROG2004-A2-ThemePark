package exceptions;

/**
 * Custom exception for when no operator is assigned to a ride
 */
public class NoOperatorException extends Exception {
    public NoOperatorException(String message) {
        super(message);
    }

    public NoOperatorException(String message, Throwable cause) {
        super(message, cause);
    }
}