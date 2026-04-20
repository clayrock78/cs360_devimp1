package src.main.java;

public class Appointment {

    private static final int MIN_DATE = 1;
    private static final int MAX_DATE = 31;

    private final Student student;
    private final Tutor tutor;
    private int date;
    private boolean confirmed;

    public Appointment(Student student, Tutor tutor) {
        this.student = student;
        this.tutor = tutor;
    }

    public Student getStudent() {
        return student;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        validateDate(date);
        this.date = date;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void markAsConfirmed() {
        this.confirmed = true;
    }

    private void validateDate(int date) {
        if (date < MIN_DATE || date > MAX_DATE) {
            throw new IllegalArgumentException("Date must be between 1 and 31.");
        }
    }
}
