/**
 * Abstract class representing a person
 * Cannot be instantiated directly - used as base class for Employee and Visitor
 */
public abstract class Person {
    private String name;
    private int age;
    private String id;

    // Default constructor
    public Person() {
        this.name = "";
        this.age = 0;
        this.id = "";
    }

    // Parameterized constructor
    public Person(String name, int age, String id) {
        this.name = name;
        this.age = age;
        this.id = id;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        // Validate age is within reasonable range
        if (age >= 0 && age <= 120) {
            this.age = age;
        } else {
            System.out.println("⚠️  Invalid age: " + age + ". Age must be between 0 and 120.");
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("Person{name='%s', age=%d, id='%s'}", name, age, id);
    }
}