import java.util.Comparator;
import exceptions.NoOperatorException;
import exceptions.NoVisitorsInQueueException;
import exceptions.FileOperationException;

/**
 * Interface defining ride operations
 * Must be implemented by Ride class
 */
public interface RideInterface {
    // Queue management methods
    void addVisitorToQueue(Visitor visitor);
    void removeVisitorFromQueue();
    void printQueue();

    // Ride history management methods
    void addVisitorToHistory(Visitor visitor);
    boolean checkVisitorFromHistory(Visitor visitor);
    int numberOfVisitors();
    void printRideHistory();

    // Ride operation methods
    void runOneCycle() throws NoOperatorException, NoVisitorsInQueueException;

    // File I/O methods
    void exportRideHistory(String filename) throws FileOperationException;
    void importRideHistory(String filename) throws FileOperationException;

    // Sorting method
    void sortRideHistory(Comparator<Visitor> comparator);
}