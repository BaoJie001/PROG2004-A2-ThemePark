import java.util.Comparator;

/**
 * Interface defining ride operations
 * Must be implemented by Ride class
 */
public interface RideInterface {
    // Basic ride information
    String getRideName();
    void setRideName(String rideName);
    Employee getOperator();
    void setOperator(Employee operator);
    int getMaxRider();
    void setMaxRider(int maxRider);

    /**
     * Get the number of cycles this ride has completed
     * @return the number of cycles
     */
    int getNumOfCycles();

    // Queue management methods
    void addVisitorToQueue(Visitor visitor);
    void removeVisitorFromQueue();
    void printQueue();

    // Ride history management methods
    void addVisitorToHistory(Visitor visitor);
    void checkVisitorFromHistory(Visitor visitor);
    int numberOfVisitors();
    void printRideHistory();

    // Ride operation methods
    void runOneCycle() throws exceptions.NoOperatorException, exceptions.NoVisitorsInQueueException;

    // File I/O methods
    void exportRideHistory(String filename) throws exceptions.FileOperationException;
    void importRideHistory(String filename) throws exceptions.FileOperationException;

    // Sorting method
    void sortRideHistory(Comparator<Visitor> comparator);

    // Utility methods for testing
    int getWaitingQueueSize();
    int getHistorySize();
}