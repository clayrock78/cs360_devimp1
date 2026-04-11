public class Appointment {

    private Student student;
    private Tutor tutor;
    private int date;
    private Boolean confirmed = false;

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
        if (0 < date || date > 31) {
            throw new IllegalArgumentException();
        }
        this.date = date;
    }

public void markAsConfirmed() {
    // TODO Auto-generated method stub
    this.confirmed = true;
}
}
