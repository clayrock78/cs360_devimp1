import java.util.List;
import java.util.Random;

public class Tutor extends User {

    private List<String> tutoringSubjects;
    //private
    private boolean[] avaliability = new boolean[31];

    public Tutor(String name, String email, int ID) {
        super(name, email, ID);
        this.randomizeAvailabity();
    }

    public Tutor(String name, String email) {
        super(name, email);
        this.randomizeAvailabity();
    }

    public void setName(String name) {
        if (name.length() < 5 || name.length() > 32) {
            throw new IllegalArgumentException();
        }
        this.setName(name);
    }


    public List<String> getTutoringSubjects() {
        return this.tutoringSubjects;
    }

    public void addTutoringSubject(String subject) {
        this.tutoringSubjects.add(subject);
    }

    public boolean isAvaliable(int number) {
        return avaliability[number];
    }

    public void printAvailability() {
        System.out.printf("Availabity for tutor %s\n", this.getName());
        BooleanCalendar.printCalendar(this.avaliability, 2);
    }

    public void randomizeAvailabity() {
        Random random = new Random();
        for (int i = 0; i < 31; i++) {
            this.avaliability[i] = random.nextBoolean();
            // Is this valid?
        }
    }

    public void setTutoringSubjects(List<String> subjects) {
        this.tutoringSubjects = subjects;
    }

    public void addSubject(String subject) {
        tutoringSubjects.add(subject);
    }

}