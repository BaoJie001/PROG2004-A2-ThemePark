import exceptions.FileOperationException;

import java.util.*;
import java.io.*;

/**
 * Ride class implementing RideInterface
 * Manages queue, history, and ride operations for a theme park ride
 */
public class Ride implements RideInterface {
    private String rideName;
    private Employee operator;
    private int maxRider;
    private int numOfCycles;
    private final Queue<Visitor> waitingQueue;  // Queue for waiting visitors
    private final LinkedList<Visitor> rideHistory;  // History of visitors who have taken the ride

    // Default constructor
    public Ride() {
        this.rideName = "Unnamed Ride";
        this.operator = null;
        this.maxRider = 2;
        this.numOfCycles = 0;
        this.waitingQueue = new LinkedList<>();
        this.rideHistory = new LinkedList<>();
    }

    // Parameterized constructor (for basic initialization)
    public Ride(String rideName, Employee operator, int maxRider) {
        this();
        this.rideName = rideName;
        this.operator = operator;
        this.maxRider = maxRider;
    }

    // Second constructor with advanced initialization (as required in Part 1)
    public Ride(String rideName, Employee operator, int maxRider,
                Queue<Visitor> initialQueue, List<Visitor> initialHistory) {
        this.rideName = (rideName != null && !rideName.trim().isEmpty()) ? rideName : "Unnamed Ride";
        this.operator = operator;

        // Validate maxRider is at least 1
        if (maxRider >= 1) {
            this.maxRider = maxRider;
        } else {
            System.out.println("⚠️  Invalid maxRider: " + maxRider + ". Setting to default value 2.");
            this.maxRider = 2;
        }

        this.numOfCycles = 0;

        // Initialize waiting queue with provided initial queue or empty queue
        this.waitingQueue = (initialQueue != null) ?
                new LinkedList<>(initialQueue) : new LinkedList<>();

        // Initialize ride history with provided initial history or empty list
        this.rideHistory = (initialHistory != null) ?
                new LinkedList<>(initialHistory) : new LinkedList<>();
    }

    // Getters and setters
    @Override
    public String getRideName() {
        return rideName;
    }

    @Override
    public void setRideName(String rideName) {
        if (rideName != null && !rideName.trim().isEmpty()) {
            this.rideName = rideName;
        } else {
            System.out.println("⚠️  Invalid ride name. Name cannot be null or empty.");
        }
    }

    @Override
    public Employee getOperator() {
        return operator;
    }

    @Override
    public void setOperator(Employee operator) {
        this.operator = operator;
    }

    @Override
    public int getMaxRider() {
        return maxRider;
    }

    @Override
    public void setMaxRider(int maxRider) {
        // Validate maxRider is at least 1 (as required in Part 5)
        if (maxRider >= 1) {
            this.maxRider = maxRider;
            System.out.println("✅ Max riders for " + rideName + " set to: " + maxRider);
        } else {
            System.out.println("❌ Error: Max rider must be at least 1. Current value remains: " + this.maxRider);
        }
    }

    @Override
    public int getNumOfCycles() {
        return numOfCycles;
    }

    // Part 3: Queue management methods
    @Override
    public void addVisitorToQueue(Visitor visitor) {
        if (visitor == null) {
            System.out.println("❌ Error: Cannot add null visitor to queue.");
            return;
        }
        boolean added = waitingQueue.offer(visitor);
        if (added) {
            System.out.println("✅ Visitor '" + visitor.getName() + "' added to queue for " + rideName);
        } else {
            System.out.println("❌ Failed to add visitor '" + visitor.getName() + "' to queue");
        }
    }

    @Override
    public void removeVisitorFromQueue() {
        Visitor visitor = waitingQueue.poll();
        if (visitor != null) {
            System.out.println("✅ Visitor '" + visitor.getName() + "' removed from queue");
        } else {
            System.out.println("ℹ️ Queue is empty. No visitor to remove.");
        }
    }

    @Override
    public void printQueue() {
        if (waitingQueue.isEmpty()) {
            System.out.println("ℹ️ Queue for " + rideName + " is empty.");
        } else {
            System.out.println("📋 Current queue for " + rideName + " (" + waitingQueue.size() + " visitors):");
            int position = 1;
            for (Visitor visitor : waitingQueue) {
                System.out.println("   " + position + ". " + visitor);
                position++;
            }
        }
    }

    // Part 4A: Ride history management methods
    @Override
    public void addVisitorToHistory(Visitor visitor) {
        if (visitor == null) {
            System.out.println("❌ Error: Cannot add null visitor to history.");
            return;
        }
        boolean added = rideHistory.add(visitor);
        if (added) {
            System.out.println("✅ Visitor '" + visitor.getName() + "' added to ride history of " + rideName);
        } else {
            System.out.println("❌ Failed to add visitor '" + visitor.getName() + "' to ride history");
        }
    }

    @Override
    public void checkVisitorFromHistory(Visitor visitor) {
        if (visitor == null) {
            System.out.println("❌ Error: Cannot check null visitor.");
            return;
        }
        boolean found = rideHistory.contains(visitor);
        System.out.println("🔍 Visitor '" + visitor.getName() + "' in ride history of " + rideName + ": " + found);
    }

    @Override
    public int numberOfVisitors() {
        int count = rideHistory.size();
        System.out.println("📊 Number of visitors in ride history of " + rideName + ": " + count);
        return count;
    }

    @Override
    public void printRideHistory() {
        if (rideHistory.isEmpty()) {
            System.out.println("ℹ️ Ride history for " + rideName + " is empty.");
        } else {
            System.out.println("📜 Ride history for " + rideName + " (" + rideHistory.size() + " visitors):");
            // Use Iterator as required in Part 4A
            Iterator<Visitor> iterator = rideHistory.iterator();
            int count = 1;
            while (iterator.hasNext()) {
                System.out.println("   " + count + ". " + iterator.next());
                count++;
            }
        }
    }

    // Part 5: Run ride cycle method
    @Override
    public void runOneCycle() throws exceptions.NoOperatorException, exceptions.NoVisitorsInQueueException {
        // Check if operator is assigned
        if (operator == null) {
            // Create root cause exception with additional context
            Throwable rootCause = new IllegalStateException(
                    "Ride operation attempted without operator assignment. " +
                            "Ride name: " + rideName + ", Current status: Inactive"
            );

            // Use constructor with Throwable parameter
            throw new exceptions.NoOperatorException(
                    "Cannot run ride '" + rideName + "': No operator assigned. " +
                            "Please assign an operator before starting the ride.",
                    rootCause
            );
        }

        // Check if there are visitors in queue
        if (waitingQueue.isEmpty()) {
            // Create root cause for empty queue
            Throwable rootCause = new IllegalStateException(
                    "Ride operation attempted with empty queue. " +
                            "Ride name: " + rideName + ", " +
                            "Operator: " + operator.getName() + ", " +
                            "Max riders: " + maxRider
            );

            // Use constructor with Throwable parameter for NoVisitorsInQueueException
            throw new exceptions.NoVisitorsInQueueException(
                    "Cannot run ride '" + rideName + "': No visitors in queue. " +
                            "Please add visitors to the queue before running the ride.",
                    rootCause
            );
        }

        System.out.println("🎢 Running ride cycle for " + rideName + " (max riders: " + maxRider + ")...");
        System.out.println("   Operator: " + operator.getName());

        int ridersProcessed = 0;
        List<Visitor> currentRiders = new ArrayList<>();

        // Process visitors from queue up to maxRider limit
        while (ridersProcessed < maxRider && !waitingQueue.isEmpty()) {
            Visitor visitor = waitingQueue.poll();
            rideHistory.add(visitor);
            currentRiders.add(visitor);
            ridersProcessed++;
            System.out.println("   ✅ " + visitor.getName() + " has taken the ride");
        }

        // Use currentRiders to avoid "unused" warning
        if (!currentRiders.isEmpty()) {
            System.out.println("   Current riders list processed successfully");
        }

        numOfCycles++;
        System.out.println("🎉 Ride cycle completed successfully!");
        System.out.println("   Riders processed: " + ridersProcessed);
        System.out.println("   Remaining in queue: " + waitingQueue.size());
        System.out.println("   Total cycles run: " + numOfCycles);
        System.out.println("   Total visitors in history: " + rideHistory.size());
    }

    // Part 6: Export ride history to file
    @Override
    public void exportRideHistory(String filename) throws exceptions.FileOperationException {
        if (filename == null || filename.trim().isEmpty()) {
            Throwable cause = new IllegalArgumentException("Invalid filename: " + filename);
            throw new exceptions.FileOperationException("Filename cannot be null or empty", cause);
        }

        File file = new File(filename);
        try {
            // Create parent directories if they don't exist
            File parentDir = file.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                boolean dirsCreated = parentDir.mkdirs();
                if (dirsCreated) {
                    System.out.println("📁 Created directory: " + parentDir.getAbsolutePath());
                } else if (!parentDir.exists()) {
                    throw new IOException("Failed to create directory: " + parentDir.getAbsolutePath());
                }
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (Visitor visitor : rideHistory) {
                    String line = String.join(",",
                            visitor.getName(),
                            String.valueOf(visitor.getAge()),
                            visitor.getId(),
                            visitor.getMembershipLevel(),
                            String.valueOf(visitor.getTickets())
                    );
                    writer.write(line);
                    writer.newLine();
                }
                System.out.println("💾 Successfully exported " + rideHistory.size() +
                        " visitors to file: " + filename);
            }
        } catch (IOException e) {
            // Use constructor with Throwable parameter for FileOperationException
            Throwable wrappedCause = new IOException("Export failed for file: " + filename, e);
            throw new exceptions.FileOperationException(
                    "Error exporting ride history to '" + filename + "'",
                    wrappedCause
            );
        }
    }

    // Part 7: Import ride history from file
    @Override
    public void importRideHistory(String filename) throws exceptions.FileOperationException {
        if (filename == null || filename.trim().isEmpty()) {
            Throwable cause = new IllegalArgumentException("Invalid filename: " + filename);
            throw new exceptions.FileOperationException("Filename cannot be null or empty", cause);
        }

        File file = getFile(filename);

        int importedCount = 0;
        int errorCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            int lineNumber = 0;

            while ((line = reader.readLine()) != null) {
                lineNumber++;
                line = line.trim();

                // Skip empty lines
                if (line.isEmpty()) continue;

                try {
                    String[] parts = line.split(",");
                    if (parts.length >= 4) {
                        String name = parts[0];
                        int age = Integer.parseInt(parts[1]);
                        String id = parts[2];
                        String membership = parts[3];
                        int tickets = parts.length >= 5 ? Integer.parseInt(parts[4]) : 1;

                        Visitor visitor = new Visitor(name, age, id, membership, tickets);
                        rideHistory.add(visitor);
                        importedCount++;
                    } else {
                        System.out.println("⚠️  Warning: Invalid format at line " + lineNumber + ": " + line);
                        errorCount++;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("⚠️  Warning: Invalid number format at line " + lineNumber + ": " + line);
                    errorCount++;
                } catch (Exception e) {
                    System.out.println("⚠️  Warning: Error processing line " + lineNumber + ": " + line);
                    errorCount++;
                }
            }

            System.out.println("📥 Successfully imported " + importedCount +
                    " visitors from file: " + filename);
            if (errorCount > 0) {
                System.out.println("⚠️  " + errorCount + " lines had errors and were skipped");
            }

        } catch (IOException e) {
            // Use constructor with Throwable parameter for FileOperationException
            Throwable wrappedCause = new IOException("Import failed for file: " + filename, e);
            throw new exceptions.FileOperationException(
                    "Error importing ride history from '" + filename + "'",
                    wrappedCause
            );
        }
    }

    private static File getFile(String filename) throws FileOperationException {
        File file = new File(filename);
        if (!file.exists()) {
            Throwable cause = new FileNotFoundException("File not found: " + file.getAbsolutePath());
            throw new FileOperationException("File not found: " + filename, cause);
        }
        if (!file.canRead()) {
            Throwable cause = new SecurityException("No read permission for file: " + file.getAbsolutePath());
            throw new FileOperationException("Cannot read file: " + filename, cause);
        }
        return file;
    }

    // Part 4B: Sort ride history
    @Override
    public void sortRideHistory(Comparator<Visitor> comparator) {
        if (comparator == null) {
            System.out.println("❌ Error: Comparator cannot be null.");
            return;
        }
        if (rideHistory.isEmpty()) {
            System.out.println("ℹ️ No visitors in ride history to sort.");
            return;
        }

        System.out.println("🔃 Sorting ride history of " + rideName + "...");
        rideHistory.sort(comparator);
        System.out.println("✅ Ride history sorted successfully using custom comparator");
    }

    // Utility methods for testing
    @Override
    public int getWaitingQueueSize() {
        return waitingQueue.size();
    }

    @Override
    public int getHistorySize() {
        return rideHistory.size();
    }

    // Additional utility methods - used in AssignmentTwo
    public void clearQueue() {
        waitingQueue.clear();
        System.out.println("🧹 Queue cleared for " + rideName);
    }

    public void clearHistory() {
        rideHistory.clear();
        numOfCycles = 0;
        System.out.println("🧹 History cleared for " + rideName);
    }

    public boolean isOperatorAssigned() {
        boolean assigned = operator != null;
        System.out.println("🔍 Operator assigned for " + rideName + ": " + (assigned ? "Yes" : "No"));
        return assigned;
    }
}