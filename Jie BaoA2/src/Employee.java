/**
 * Employee class extending Person
 * Represents theme park staff who operate rides
 */
public class Employee extends Person {
    private String position;
    private String employeeId;

    // Default constructor
    public Employee() {
        super();
        this.position = "";
        this.employeeId = "";
    }

    // Parameterized constructor
    public Employee(String name, int age, String id, String position, String employeeId) {
        super(name, age, id);
        this.position = position;
        this.employeeId = employeeId;
    }

    // Getters and setters
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public String toString() {
        return String.format("Employee{name='%s', position='%s', employeeId='%s'}",
                getName(), position, employeeId);
    }
}