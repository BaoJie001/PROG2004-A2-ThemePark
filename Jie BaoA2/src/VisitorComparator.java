import java.util.Comparator;

/**
 * Comparator for sorting visitors by name and age
 * Implements Comparator interface as required
 */
public class VisitorComparator implements Comparator<Visitor> {
    @Override
    public int compare(Visitor v1, Visitor v2) {
        if (v1 == null && v2 == null) return 0;
        if (v1 == null) return -1;
        if (v2 == null) return 1;

        // First compare by name (case insensitive)
        int nameCompare = v1.getName().compareToIgnoreCase(v2.getName());
        if (nameCompare != 0) {
            return nameCompare;
        }

        // If names are equal, compare by age
        int ageCompare = Integer.compare(v1.getAge(), v2.getAge());
        if (ageCompare != 0) {
            return ageCompare;
        }

        // If age is also equal, compare by membership level
        return v1.getMembershipLevel().compareTo(v2.getMembershipLevel());
    }

    @Override
    public String toString() {
        return "VisitorComparator [sorts by: name (case-insensitive), age, membership]";
    }
}