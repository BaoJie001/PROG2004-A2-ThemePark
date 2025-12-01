/**
 * Visitor class extending Person
 * Represents theme park visitors
 */
public class Visitor extends Person {
    private String membershipLevel;
    private int tickets;

    // Default constructor
    public Visitor() {
        super();
        this.membershipLevel = "Standard";
        this.tickets = 1;
    }

    // Parameterized constructor
    public Visitor(String name, int age, String id, String membershipLevel) {
        super(name, age, id);
        this.membershipLevel = membershipLevel;
        this.tickets = 1;
    }

    public Visitor(String name, int age, String id, String membershipLevel, int tickets) {
        super(name, age, id);
        this.membershipLevel = membershipLevel;
        this.tickets = tickets;
    }

    // Getters and setters
    public String getMembershipLevel() {
        return membershipLevel;
    }

    public void setMembershipLevel(String membershipLevel) {
        this.membershipLevel = membershipLevel;
    }

    public int getTickets() {
        return tickets;
    }

    public void setTickets(int tickets) {
        if (tickets >= 0) {
            this.tickets = tickets;
        }
    }

    @Override
    public String toString() {
        return String.format("Visitor{name='%s', age=%d, id='%s', membership='%s', tickets=%d}",
                getName(), getAge(), getId(), membershipLevel, tickets);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Visitor visitor = (Visitor) obj;
        return getId().equals(visitor.getId()) &&
                getName().equals(visitor.getName());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}