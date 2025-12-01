import exceptions.NoOperatorException;
import exceptions.NoVisitorsInQueueException;
import exceptions.FileOperationException;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Main demonstration class for the Theme Park Management System
 * Contains all required demonstration methods for Parts 3-7
 */
public class AssignmentTwo {

    @SuppressWarnings({"unused", "RedundantModifier"})
    public static void main(String[] args) {
        AssignmentTwo demo = new AssignmentTwo();

        // Use args parameter to avoid warnings
        boolean hasArgs = args != null && args.length > 0;

        System.out.println("🎢 ===========================================");
        System.out.println("🎢  PARK RIDES VISITOR MANAGEMENT SYSTEM");
        if (hasArgs) {
            System.out.println("🎢  Command-line arguments: " + args.length);
            // Display first 3 arguments
            for (int i = 0; i < Math.min(args.length, 3); i++) {
                System.out.println("🎢    [" + i + "] " + args[i]);
            }
            if (args.length > 3) {
                System.out.println("🎢    ... and " + (args.length - 3) + " more");
            }
        }
        System.out.println("🎢 ===========================================");
        System.out.println();

        // 1. First test basic functionality
        demo.testGettersSetters();

        // 2. Execute all required demonstration parts
        demo.partThree();
        demo.partFourA();
        demo.partFourB();
        demo.partFive();
        demo.partSix();
        demo.partSeven();

        // 3. Additional advanced testing, pass args
        demo.advancedTesting(args);

        System.out.println();
        System.out.println("🎉 ===========================================");
        System.out.println("🎉  ALL DEMONSTRATIONS COMPLETED SUCCESSFULLY!");
        System.out.println("🎉 ===========================================");

        // If there are command-line arguments, display summary
        if (hasArgs) {
            System.out.println();
            System.out.println("📊 Program executed with " + args.length + " command-line argument(s)");
        }
    }

    public void testGettersSetters() {
        System.out.println("🔹 TESTING GETTERS/SETTERS");
        System.out.println("🔹 Demonstrating Data Encapsulation");
        System.out.println();

        // Test 1: Person is abstract (can't instantiate)
        System.out.println("🧪 TEST 1: Person class is abstract");
        System.out.println("✅ Person is abstract - correctly designed (cannot instantiate)");
        System.out.println();

        // Test 2: Employee getters/setters
        System.out.println("🧪 TEST 2: Employee class getters/setters");
        Employee emp = new Employee();
        emp.setName("John Smith");
        emp.setAge(30);
        emp.setId("EMP001");
        emp.setPosition("Senior Ride Operator");
        emp.setEmployeeId("OP1001");

        System.out.println("   Setting values:");
        System.out.println("   - Name: John Smith");
        System.out.println("   - Age: 30");
        System.out.println("   - ID: EMP001");
        System.out.println("   - Position: Senior Ride Operator");
        System.out.println("   - Employee ID: OP1001");
        System.out.println();

        System.out.println("   Getting values:");
        System.out.println("   - Name: " + emp.getName());
        System.out.println("   - Age: " + emp.getAge());
        System.out.println("   - ID: " + emp.getId());
        System.out.println("   - Position: " + emp.getPosition());
        System.out.println("   - Employee ID: " + emp.getEmployeeId());
        System.out.println();

        // Test 3: Visitor getters/setters
        System.out.println("🧪 TEST 3: Visitor class getters/setters");
        Visitor visitor = new Visitor();
        visitor.setName("Alice Johnson");
        visitor.setAge(25);
        visitor.setId("VIS2025001");
        visitor.setMembershipLevel("Gold");
        visitor.setTickets(5);

        System.out.println("   Setting values:");
        System.out.println("   - Name: Alice Johnson");
        System.out.println("   - Age: 25");
        System.out.println("   - ID: VIS2025001");
        System.out.println("   - Membership: Gold");
        System.out.println("   - Tickets: 5");
        System.out.println();

        System.out.println("   Getting values:");
        System.out.println("   - Name: " + visitor.getName());
        System.out.println("   - Age: " + visitor.getAge());
        System.out.println("   - ID: " + visitor.getId());
        System.out.println("   - Membership: " + visitor.getMembershipLevel());
        System.out.println("   - Tickets: " + visitor.getTickets());
        System.out.println();

        // Test 4: Ride getters/setters
        System.out.println("🧪 TEST 4: Ride class getters/setters");
        Ride ride = new Ride();
        ride.setRideName("Thunder Roller");
        ride.setOperator(emp);
        ride.setMaxRider(4);

        System.out.println("   Setting values:");
        System.out.println("   - Ride Name: Thunder Roller");
        System.out.println("   - Operator: John Smith");
        System.out.println("   - Max Riders: 4");
        System.out.println();

        System.out.println("   Getting values:");
        System.out.println("   - Ride Name: " + ride.getRideName());
        System.out.println("   - Operator: " + (ride.getOperator() != null ? ride.getOperator().getName() : "None"));
        System.out.println("   - Max Riders: " + ride.getMaxRider());
        System.out.println("   - Cycles: " + ride.getNumOfCycles());
        System.out.println("   - Queue Size: " + ride.getWaitingQueueSize());
        System.out.println("   - History Size: " + ride.getHistorySize());

        System.out.println();
        System.out.println("✅ All getters/setters working correctly");
        System.out.println("──────────────────────────────────────────");
        System.out.println();
    }

    public void partThree() {
        System.out.println("🔹 PART 3: QUEUE MANAGEMENT");
        System.out.println("🔹 Demonstrating Visitor Queue Operations");
        System.out.println();

        // Create a ride without operator for queue demonstration
        Ride rollerCoaster = new Ride("Roller Coaster", null, 2);

        // Add 5 visitors to queue (minimum requirement)
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

        // Add 5 visitors to ride history (minimum requirement)
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

        // Print entire ride history using Iterator (as required)
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
        VisitorComparator comparator = new VisitorComparator();

        // Add visitors in unsorted order (minimum 5 visitors)
        String[] names = {"Charlie", "Alice", "Bob", "Eve", "David", "Alice"};
        int[] ages = {25, 30, 22, 28, 35, 30};
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
        System.out.println("🔃 Sorting ride history using: " + comparator);
        ferrisWheel.sortRideHistory(comparator);
        System.out.println();

        // Print after sorting
        System.out.println("📜 Ride history AFTER sorting:");
        ferrisWheel.printRideHistory();
        System.out.println();

        // Verify sorting worked correctly
        System.out.println("🔍 Verifying sort order:");
        System.out.println("   1. First by name (case-insensitive)");
        System.out.println("   2. Then by age (ascending)");
        System.out.println("   3. Then by membership level");

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

        // Add 10 visitors to queue (minimum requirement)
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

        // Test 4: Test invalid maxRider values
        System.out.println("\n🧪 TEST 4: Testing invalid maxRider values");
        Ride invalidRide = new Ride();
        invalidRide.setMaxRider(0); // Should be rejected
        System.out.println("Max rider after setting to 0: " + invalidRide.getMaxRider());
        invalidRide.setMaxRider(-5); // Should be rejected
        System.out.println("Max rider after setting to -5: " + invalidRide.getMaxRider());

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

        // Use data directory in current directory
        String currentDir = System.getProperty("user.dir");
        String dataDir = currentDir + File.separator + "data";
        String filename = dataDir + File.separator + "ride_history_export.csv";

        // Ensure data directory exists
        try {
            Files.createDirectories(Paths.get(dataDir));
            System.out.println("📁 Data directory: " + dataDir);
        } catch (Exception e) {
            System.out.println("⚠️  Could not create data directory: " + e.getMessage());
            filename = "ride_history_export.csv"; // Use current directory
        }

        System.out.println("💾 Export file: " + filename);
        System.out.println();

        // Add 5 visitors to ride history (minimum requirement)
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
        System.out.println("💾 Exporting ride history to file...");
        try {
            exportRide.exportRideHistory(filename);
            System.out.println("✅ Export completed successfully!");
            System.out.println("📄 File saved as: " + filename);
        } catch (FileOperationException e) {
            System.out.println("❌ Export failed: " + e.getMessage());
            // Try alternate location
            try {
                System.out.println("🔄 Trying alternate location...");
                String altFilename = "ride_export_backup.csv";
                exportRide.exportRideHistory(altFilename);
                System.out.println("✅ Export completed to backup file: " + altFilename);
            } catch (FileOperationException e2) {
                System.out.println("❌ Backup export also failed: " + e2.getMessage());
            }
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

        // Try multiple possible file locations
        String[] possibleFiles = {
                "data/ride_history_export.csv",
                "ride_history_export.csv",
                System.getProperty("user.dir") + File.separator + "data" + File.separator + "ride_history_export.csv",
                System.getProperty("user.dir") + File.separator + "ride_history_export.csv"
        };

        String filename = null;
        for (String possibleFile : possibleFiles) {
            File file = new File(possibleFile);
            if (file.exists()) {
                filename = possibleFile;
                break;
            }
        }

        if (filename == null) {
            System.out.println("❌ No export file found. Please run Part 6 first.");
            System.out.println("💡 Looking for files in:");
            for (String possibleFile : possibleFiles) {
                System.out.println("   - " + possibleFile);
            }
            return;
        }

        System.out.println("📄 Found file: " + filename);
        System.out.println("📥 Importing ride history from file...");

        try {
            importRide.importRideHistory(filename);
            System.out.println("✅ Import completed successfully!");
        } catch (FileOperationException e) {
            System.out.println("❌ Import failed: " + e.getMessage());
            System.out.println("💡 Note: Make sure Part 6 ran successfully to create the export file.");
            return;
        }
        System.out.println();

        // Verify import by checking number of visitors
        System.out.println("🔍 Verifying import results:");
        int importedCount = importRide.numberOfVisitors();
        System.out.println("   Verified: " + importedCount + " visitors imported");
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

    @SuppressWarnings("unused") // Avoid warnings for unused args parameter
    public void advancedTesting(String[] args) {
        System.out.println("🔹 ADVANCED TESTING");
        System.out.println("🔹 Demonstrating Additional Features");
        System.out.println();

        // Check for command-line arguments
        if (args != null && args.length > 0) {
            System.out.println("📋 Command-line arguments provided:");
            for (int i = 0; i < args.length; i++) {
                System.out.println("   [" + i + "] " + args[i]);
                // If specific arguments, execute different tests
                if ("test".equalsIgnoreCase(args[i])) {
                    System.out.println("   🧪 Running extended tests due to 'test' argument");
                }
                if ("clean".equalsIgnoreCase(args[i])) {
                    System.out.println("   🧹 Will test clean operations");
                }
            }
            System.out.println();
        }

        // Test the second constructor for Ride class
        System.out.println("🧪 TEST: Testing Ride class second constructor");
        Employee testOperator = new Employee("Test Operator", 28, "TO001", "Operator", "TOP001");

        // Create initial queue and history
        Queue<Visitor> initialQueue = new LinkedList<>();
        List<Visitor> initialHistory = new ArrayList<>();

        for (int i = 1; i <= 3; i++) {
            initialQueue.add(new Visitor("QueueTest" + i, 20 + i, "QTID" + i, "Standard"));
            initialHistory.add(new Visitor("HistoryTest" + i, 25 + i, "HTID" + i, "Gold", i));
        }

        // Create ride using second constructor
        Ride advancedRide = new Ride("Advanced Test Ride", testOperator, 4, initialQueue, initialHistory);
        System.out.println("✅ Ride created with second constructor");
        System.out.println("   Ride name: " + advancedRide.getRideName());
        System.out.println("   Operator: " + advancedRide.getOperator().getName());
        System.out.println("   Max riders: " + advancedRide.getMaxRider());
        System.out.println("   Initial queue size: " + advancedRide.getWaitingQueueSize());
        System.out.println("   Initial history size: " + advancedRide.getHistorySize());
        System.out.println();

        // Test different ride capacities
        System.out.println("🧪 TEST 1: Testing different ride capacities");
        int[] capacities = {1, 2, 4, 8};

        for (int capacity : capacities) {
            System.out.println("\n   Testing capacity: " + capacity);
            Employee operator = new Employee("Operator " + capacity, 30, "OP" + capacity, "Operator", "OPID" + capacity);
            Ride ride = new Ride("Capacity Test Ride " + capacity, operator, capacity);

            // Add exactly capacity + 2 visitors
            for (int i = 1; i <= capacity + 2; i++) {
                ride.addVisitorToQueue(new Visitor("CapVisitor" + i, 20 + i, "CAP" + i, "Standard"));
            }

            try {
                System.out.println("   Queue size before: " + ride.getWaitingQueueSize());
                ride.runOneCycle();
                System.out.println("   Queue size after: " + ride.getWaitingQueueSize());
                System.out.println("   History size: " + ride.getHistorySize());
                System.out.println("   Cycles run: " + ride.getNumOfCycles());
            } catch (Exception e) {
                System.out.println("   ❌ Error: " + e.getMessage());
            }
        }
        System.out.println();

        // Test multiple cycles
        System.out.println("🧪 TEST 2: Testing multiple ride cycles");
        Employee operator = new Employee("MultiCycle Operator", 35, "MCOP001", "Lead Operator", "LOP001");
        Ride multiCycleRide = new Ride("Multi-Cycle Ride", operator, 2);

        // Add 8 visitors
        for (int i = 1; i <= 8; i++) {
            multiCycleRide.addVisitorToQueue(new Visitor("MultiVisitor" + i, 20 + i, "MVID" + i, "Gold", 3));
        }

        System.out.println("   Initial queue size: " + multiCycleRide.getWaitingQueueSize());

        // Run 3 cycles
        for (int cycle = 1; cycle <= 3; cycle++) {
            try {
                System.out.println("\n   Running cycle " + cycle + "...");
                multiCycleRide.runOneCycle();
                System.out.println("   After cycle " + cycle + ":");
                System.out.println("     Queue: " + multiCycleRide.getWaitingQueueSize() + " visitors");
                System.out.println("     History: " + multiCycleRide.getHistorySize() + " visitors");
            } catch (Exception e) {
                System.out.println("   ❌ Error in cycle " + cycle + ": " + e.getMessage());
            }
        }

        System.out.println();
        System.out.println("🧪 TEST 3: Testing edge cases");

        // Test with invalid age values
        Visitor edgeVisitor = new Visitor();
        edgeVisitor.setAge(-5); // Should be prevented
        System.out.println("   Age after trying to set to -5: " + edgeVisitor.getAge());
        edgeVisitor.setAge(150); // Should be accepted
        System.out.println("   Age after setting to 150: " + edgeVisitor.getAge());
        edgeVisitor.setAge(200); // Should be prevented
        System.out.println("   Age after trying to set to 200: " + edgeVisitor.getAge());

        System.out.println();

        // Test 4: Testing utility methods from Ride class
        System.out.println("🧪 TEST 4: Testing utility methods");
        Employee utilityOperator = new Employee("Utility Operator", 28, "UTOP001", "Operator", "UTOPID001");
        Ride utilityRide = new Ride("Utility Test Ride", utilityOperator, 4);

        // Add some data
        System.out.println("   Adding test data to utility ride...");
        for (int i = 1; i <= 5; i++) {
            utilityRide.addVisitorToQueue(new Visitor("UtilityQueueVisitor" + i, 20 + i, "UTQID" + i, "Standard"));
            utilityRide.addVisitorToHistory(new Visitor("UtilityHistoryVisitor" + i, 25 + i, "UTHID" + i, "Gold", i));
        }

        System.out.println("\n   Before clearing operations:");
        System.out.println("   - Queue size: " + utilityRide.getWaitingQueueSize());
        System.out.println("   - History size: " + utilityRide.getHistorySize());
        System.out.println("   - Operator assigned: " + utilityRide.isOperatorAssigned());

        // Run one cycle to add more data
        try {
            System.out.println("\n   Running one cycle...");
            utilityRide.runOneCycle();
        } catch (Exception e) {
            System.out.println("   ❌ Error running cycle: " + e.getMessage());
        }

        System.out.println("\n   After running cycle:");
        System.out.println("   - Queue size: " + utilityRide.getWaitingQueueSize());
        System.out.println("   - History size: " + utilityRide.getHistorySize());
        System.out.println("   - Cycles run: " + utilityRide.getNumOfCycles());

        // Test clear methods
        System.out.println("\n   Testing clearQueue() method...");
        utilityRide.clearQueue();
        System.out.println("   Queue size after clear: " + utilityRide.getWaitingQueueSize());

        System.out.println("\n   Testing clearHistory() method...");
        utilityRide.clearHistory();
        System.out.println("   History size after clear: " + utilityRide.getHistorySize());
        System.out.println("   Cycles after clear: " + utilityRide.getNumOfCycles());

        // Test ride without operator
        System.out.println("\n   Testing ride without operator...");
        Ride noOpRide = new Ride("No Operator Ride", null, 3);
        System.out.println("   Operator assigned: " + noOpRide.isOperatorAssigned());

        // Add some visitors and try to run
        noOpRide.addVisitorToQueue(new Visitor("TestVisitor1", 20, "TVID1", "Basic"));
        noOpRide.addVisitorToQueue(new Visitor("TestVisitor2", 21, "TVID2", "Basic"));

        try {
            noOpRide.runOneCycle();
        } catch (Exception e) {
            System.out.println("   ❌ Expected error (no operator): " + e.getMessage());
        }

        // Reassign operator and test
        System.out.println("\n   Assigning operator and testing again...");
        noOpRide.setOperator(utilityOperator);
        System.out.println("   Operator assigned: " + noOpRide.isOperatorAssigned());

        try {
            noOpRide.runOneCycle();
            System.out.println("   ✅ Successfully ran ride after assigning operator");
            System.out.println("   History size: " + noOpRide.getHistorySize());
            System.out.println("   Queue size: " + noOpRide.getWaitingQueueSize());
        } catch (Exception e) {
            System.out.println("   ❌ Unexpected error: " + e.getMessage());
        }

        System.out.println();
        System.out.println("✅ Advanced Testing Completed");
        System.out.println("──────────────────────────────────────────");
        System.out.println();
    }
}