import java.util.*;
import java.io.*;
import exceptions.NoOperatorException;
import exceptions.NoVisitorsInQueueException;
import exceptions.FileOperationException;

/**
 * Ride class implementing RideInterface
 * Manages queue, history, and ride operations for a theme park ride
 */
public class Ride implements RideInterface {
    private String rideName;
    private Employee operator;
    private int maxRider;
    private int numOfCycles;
    private Queue<Visitor> waitingQueue;
    private LinkedList<Visitor> rideHistory;

    // Default constructor
    public Ride() {
        this.waitingQueue = new LinkedList<>();
        this.rideHistory = new LinkedList<>();
        this.numOfCycles = 0;
        this.maxRider = 2; // Default value
    }

    // Parameterized constructor
    public Ride(String rideName, Employee operator, int maxRider) {
        this();
        this.rideName = rideName;
        this.operator = operator;
        this.maxRider = maxRider;
    }

    // Getters and setters
    public String getRideName() { return rideName; }
    public void setRideName(String rideName) { this.rideName = rideName; }
    public Employee getOperator() { return operator; }
    public void setOperator(Employee operator) { this.operator = operator; }
    public int getMaxRider() { return maxRider; }
    public void setMaxRider(int maxRider) { this.maxRider = maxRider; }
    public int getNumOfCycles() { return numOfCycles; }

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
    public boolean checkVisitorFromHistory(Visitor visitor) {
        if (visitor == null) {
            System.out.println("❌ Error: Cannot check null visitor.");
            return false;
        }
        boolean found = rideHistory.contains(visitor);
        System.out.println("🔍 Visitor '" + visitor.getName() + "' in ride history of " + rideName + ": " + found);
        return found;
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
    public void runOneCycle() throws NoOperatorException, NoVisitorsInQueueException {
        // Check if operator is assigned
        if (operator == null) {
            throw new NoOperatorException("Cannot run ride '" + rideName + "': No operator assigned.");
        }

        // Check if there are visitors in queue
        if (waitingQueue.isEmpty()) {
            throw new NoVisitorsInQueueException("Cannot run ride '" + rideName + "': No visitors in queue.");
        }

        System.out.println("🎢 Running ride cycle for " + rideName + " (max riders: " + maxRider + ")...");

        int ridersProcessed = 0;
        List<Visitor> currentRiders = new ArrayList<>();

        // Process visitors from queue
        while (ridersProcessed < maxRider && !waitingQueue.isEmpty()) {
            Visitor visitor = waitingQueue.poll();
            rideHistory.add(visitor);
            currentRiders.add(visitor);
            ridersProcessed++;
            System.out.println("   ✅ " + visitor.getName() + " has taken the ride");
        }

        numOfCycles++;
        System.out.println("🎉 Ride cycle completed successfully!");
        System.out.println("   Riders processed: " + ridersProcessed);
        System.out.println("   Remaining in queue: " + waitingQueue.size());
        System.out.println("   Total cycles run: " + numOfCycles);
    }

    // Part 6: Export ride history to file
    @Override
    public void exportRideHistory(String filename) throws FileOperationException {
        if (filename == null || filename.trim().isEmpty()) {
            throw new FileOperationException("Filename cannot be null or empty");
        }

        File file = new File(filename);
        try {
            // Create parent directories if they don't exist
            File parentDir = file.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs();
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
            throw new FileOperationException("Error exporting ride history to '" + filename + "'", e);
        }
    }

    // Part 7: Import ride history from file
    @Override
    public void importRideHistory(String filename) throws FileOperationException {
        if (filename == null || filename.trim().isEmpty()) {
            throw new FileOperationException("Filename cannot be null or empty");
        }

        File file = new File(filename);
        if (!file.exists()) {
            throw new FileOperationException("File not found: " + filename);
        }
        if (!file.canRead()) {
            throw new FileOperationException("Cannot read file: " + filename);
        }

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
            throw new FileOperationException("Error importing ride history from '" + filename + "'", e);
        }
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

        System.out.println("🔃 Sorting ride history...");
        rideHistory.sort(comparator);
        System.out.println("✅ Ride history sorted successfully using custom comparator");
    }
}
