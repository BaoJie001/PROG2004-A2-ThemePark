/**
 * Abstract class representing a person
 * Cannot be instantiated directly - used as base class for Employee and Visitor
 */
public abstract class Person {
    private String name;
    private int age;
    private String id;

    // Default constructor
    public Person() {}

    // Parameterized constructor
    public Person(String name, int age, String id) {
        this.name = name;
        this.age = age;
        this.id = id;
    }

    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    @Override
    public String toString() {
        return String.format("Person{name='%s', age=%d, id='%s'}", name, age, id);
    }
}