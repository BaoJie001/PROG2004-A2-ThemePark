import exceptions.NoOperatorException;
import exceptions.NoVisitorsInQueueException;
import exceptions.FileOperationException;

/**
 * Main demonstration class for the Theme Park Management System
 * Contains all required demonstration methods for Parts 3-7
 */
public class AssignmentTwo {

    public static void main(String[] args) {
        AssignmentTwo demo = new AssignmentTwo();

        System.out.println("🎢 ===========================================");
        System.out.println("🎢  PARK RIDES VISITOR MANAGEMENT SYSTEM");
        System.out.println("🎢 ===========================================");
        System.out.println();

        // Execute all demonstration parts
        demo.partThree();
        demo.partFourA();
        demo.partFourB();
        demo.partFive();
        demo.partSix();
        demo.partSeven();

        System.out.println();
        System.out.println("🎉 ===========================================");
        System.out.println("🎉  ALL DEMONSTRATIONS COMPLETED SUCCESSFULLY!");
        System.out.println("🎉 ===========================================");
    }

    public void partThree() {
        System.out.println("🔹 PART 3: QUEUE MANAGEMENT");
        System.out.println("🔹 Demonstrating Visitor Queue Operations");
        System.out.println();

        // Create a ride without operator for queue demonstration
        Ride rollerCoaster = new Ride("Roller Coaster", null, 2);

        // Add 5 visitors to queue
        System.out.println("📥 Adding 5 visitors to queue:");
        for (int i = 1; i <= 5; i++) {
            rollerCoaster.addVisitorToQueue(
                    new Visitor("QueueVisitor" + i, 20 + i, "QID" + i, "Standard")
            );
        }
        System.out.println();

        // Print current queue
        rollerCoaster.printQueue();
        System.out.println();

        // Remove one visitor from queue
        System.out.println("📤 Removing one visitor from queue:");
        rollerCoaster.removeVisitorFromQueue();
        System.out.println();

        // Print queue after removal
        System.out.println("📋 Queue after removal:");
        rollerCoaster.printQueue();

        System.out.println();
        System.out.println("✅ Part 3 Demonstration Completed");
        System.out.println("──────────────────────────────────────────");
        System.out.println();
    }

    public void partFourA() {
        System.out.println("🔹 PART 4A: RIDE HISTORY MANAGEMENT");
        System.out.println("🔹 Demonstrating LinkedList for Ride History");
        System.out.println();

        Ride waterRide = new Ride("Water Adventure", null, 4);

        // Add 5 visitors to ride history
        System.out.println("📥 Adding 5 visitors to ride history:");
        for (int i = 1; i <= 5; i++) {
            waterRide.addVisitorToHistory(
                    new Visitor("HistoryVisitor" + i, 25 + i, "HID" + i, "Premium", i)
            );
        }
        System.out.println();

        // Check if a specific visitor exists in history
        Visitor checkVisitor = new Visitor("HistoryVisitor3", 28, "HID3", "Premium", 3);
        waterRide.checkVisitorFromHistory(checkVisitor);
        System.out.println();

        // Print number of visitors
        waterRide.numberOfVisitors();
        System.out.println();

        // Print entire ride history using Iterator
        System.out.println("📜 Printing ride history using Iterator:");
        waterRide.printRideHistory();

        System.out.println();
        System.out.println("✅ Part 4A Demonstration Completed");
        System.out.println("──────────────────────────────────────────");
        System.out.println();
    }

    public void partFourB() {
        System.out.println("🔹 PART 4B: SORTING RIDE HISTORY");
        System.out.println("🔹 Demonstrating Comparator for Sorting");
        System.out.println();

        Ride ferrisWheel = new Ride("Ferris Wheel", null, 6);

        // Add visitors in unsorted order
        String[] names = {"Charlie", "Alice", "Bob", "Eve", "David", "Alice"};
        int[] ages = {25, 30, 22, 28, 35, 30}; // Note: Two Alices with same age
        String[] memberships = {"Gold", "Silver", "Basic", "Gold", "Silver", "Premium"};

        System.out.println("📥 Adding visitors in unsorted order:");
        for (int i = 0; i < names.length; i++) {
            ferrisWheel.addVisitorToHistory(
                    new Visitor(names[i], ages[i], "SID" + i, memberships[i], i + 1)
            );
        }
        System.out.println();

        // Print before sorting
        System.out.println("📜 Ride history BEFORE sorting:");
        ferrisWheel.printRideHistory();
        System.out.println();

        // Sort using custom comparator
        System.out.println("🔃 Sorting ride history by name, age, and membership...");
        ferrisWheel.sortRideHistory(new VisitorComparator());
        System.out.println();

        // Print after sorting
        System.out.println("📜 Ride history AFTER sorting:");
        ferrisWheel.printRideHistory();

        System.out.println();
        System.out.println("✅ Part 4B Demonstration Completed");
        System.out.println("──────────────────────────────────────────");
        System.out.println();
    }

    public void partFive() {
        System.out.println("🔹 PART 5: RUN RIDE CYCLE");
        System.out.println("🔹 Demonstrating Ride Operation with Error Handling");
        System.out.println();

        // Create ride with operator
        Employee operator = new Employee("John RideOperator", 32, "EMP001", "Senior Operator", "OP001");
        Ride thunderstorm = new Ride("Thunderstorm", operator, 3);

        // Test 1: Try to run ride without visitors (should fail)
        System.out.println("🧪 TEST 1: Attempting to run ride with no visitors...");
        try {
            thunderstorm.runOneCycle();
        } catch (NoOperatorException | NoVisitorsInQueueException e) {
            System.out.println("❌ Expected error: " + e.getMessage());
        }
        System.out.println();

        // Add 10 visitors to queue
        System.out.println("📥 Adding 10 visitors to queue:");
        for (int i = 1; i <= 10; i++) {
            thunderstorm.addVisitorToQueue(
                    new Visitor("CycleVisitor" + i, 18 + i, "CID" + i, "Standard", 2)
            );
        }
        System.out.println();

        // Print queue before running cycle
        System.out.println("📋 Queue BEFORE running cycle:");
        thunderstorm.printQueue();
        System.out.println();

        // Test 2: Run ride with operator and visitors (should succeed)
        System.out.println("🧪 TEST 2: Running ride with operator and visitors...");
        try {
            thunderstorm.runOneCycle();
        } catch (NoOperatorException | NoVisitorsInQueueException e) {
            System.out.println("❌ Unexpected error: " + e.getMessage());
        }
        System.out.println();

        // Print queue after running cycle
        System.out.println("📋 Queue AFTER running cycle:");
        thunderstorm.printQueue();
        System.out.println();

        // Print ride history
        System.out.println("📜 Ride history after cycle:");
        thunderstorm.printRideHistory();
        System.out.println();

        // Test 3: Create ride without operator and try to run (should fail)
        System.out.println("🧪 TEST 3: Testing ride without operator...");
        Ride noOperatorRide = new Ride("Test Ride", null, 2);
        // Add some visitors
        noOperatorRide.addVisitorToQueue(new Visitor("TestVisitor", 25, "TID001", "Basic"));
        try {
            noOperatorRide.runOneCycle();
        } catch (NoOperatorException | NoVisitorsInQueueException e) {
            System.out.println("❌ Expected error: " + e.getMessage());
        }

        System.out.println();
        System.out.println("✅ Part 5 Demonstration Completed");
        System.out.println("──────────────────────────────────────────");
        System.out.println();
    }

    public void partSix() {
        System.out.println("🔹 PART 6: EXPORT RIDE HISTORY TO FILE");
        System.out.println("🔹 Demonstrating File Output Operations");
        System.out.println();

        Ride exportRide = new Ride("Export Demo Ride", null, 4);
        String filename = "data/ride_history_export.csv";

        // Add 5 visitors to ride history
        System.out.println("📥 Adding visitors to ride history for export:");
        String[] exportNames = {"ExportAlice", "ExportBob", "ExportCharlie", "ExportDiana", "ExportEdward"};
        for (int i = 0; i < exportNames.length; i++) {
            exportRide.addVisitorToHistory(
                    new Visitor(exportNames[i], 20 + (i * 3), "EXID" + i, i % 2 == 0 ? "Gold" : "Silver", i + 2)
            );
        }
        System.out.println();

        // Print ride history before export
        System.out.println("📜 Ride history to be exported:");
        exportRide.printRideHistory();
        System.out.println();

        // Export to file
        System.out.println("💾 Exporting ride history to file: " + filename);
        try {
            exportRide.exportRideHistory(filename);
            System.out.println("✅ Export completed successfully!");
        } catch (FileOperationException e) {
            System.out.println("❌ Export failed: " + e.getMessage());
        }

        System.out.println();
        System.out.println("✅ Part 6 Demonstration Completed");
        System.out.println("──────────────────────────────────────────");
        System.out.println();
    }

    public void partSeven() {
        System.out.println("🔹 PART 7: IMPORT RIDE HISTORY FROM FILE");
        System.out.println("🔹 Demonstrating File Input Operations");
        System.out.println();

        Ride importRide = new Ride("Import Demo Ride", null, 4);
        String filename = "data/ride_history_export.csv";

        // Import from file
        System.out.println("📥 Importing ride history from file: " + filename);
        try {
            importRide.importRideHistory(filename);
            System.out.println("✅ Import completed successfully!");
        } catch (FileOperationException e) {
            System.out.println("❌ Import failed: " + e.getMessage());
            System.out.println("💡 Note: Run Part 6 first to create the export file.");
            return;
        }
        System.out.println();

        // Verify import by checking number of visitors
        System.out.println("🔍 Verifying import results:");
        importRide.numberOfVisitors();
        System.out.println();

        // Print imported ride history
        System.out.println("📜 Imported ride history:");
        importRide.printRideHistory();
        System.out.println();

        // Test error handling with non-existent file
        System.out.println("🧪 Testing error handling with non-existent file...");
        try {
            importRide.importRideHistory("non_existent_file.csv");
        } catch (FileOperationException e) {
            System.out.println("❌ Expected error: " + e.getMessage());
        }

        System.out.println();
        System.out.println("✅ Part 7 Demonstration Completed");
        System.out.println("──────────────────────────────────────────");
        System.out.println();
    }
}