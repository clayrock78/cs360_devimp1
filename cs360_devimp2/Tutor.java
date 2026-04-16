import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Random;

public class Tutor extends User {

    private static final int DAYS_IN_MONTH = 31;

    private List<String> tutoringSubjects = new ArrayList<>();
    private final BitSet availability = new BitSet(DAYS_IN_MONTH);

    public Tutor(String name, String email, int id) {
        super(name, email, id);
        randomizeAvailability();
    }

    public Tutor(String name, String email) {
        super(name, email);
        randomizeAvailability();
    }

    @Override
    public void setName(String name) {
        if (name.length() < 5 || name.length() > 32) {
            throw new IllegalArgumentException();
        }
        super.setName(name);
    }

    public List<String> getTutoringSubjects() {
        return tutoringSubjects;
    }

    public void addTutoringSubject(String subject) {
        tutoringSubjects.add(subject);
    }

    public boolean isAvailable(int day) {
        return availability.get(day);
    }

    public void printAvailability() {
        System.out.printf("Availability for tutor %s\n", getName());
        Calendar.printCalendar(availability, 2);
    }

    public void randomizeAvailability() {
        Random random = new Random();
        for (int day = 0; day < DAYS_IN_MONTH; day++) {
            if (random.nextBoolean()) {
                availability.set(day);
            }
        }
    }

    public void setTutoringSubjects(List<String> subjects) {
        tutoringSubjects = new ArrayList<>(subjects);
    }

    public void addSubject(String subject) {
        addTutoringSubject(subject);
    }
}
