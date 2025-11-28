package exceptions;

/**
 * Custom exception for when no visitors are in the queue
 */
public class NoVisitorsInQueueException extends Exception {
    public NoVisitorsInQueueException(String message) {
        super(message);
    }

    public NoVisitorsInQueueException(String message, Throwable cause) {
        super(message, cause);
    }
}