public class Appointment {
    private boolean confirmedByTutor;
    private Student student;
    private Tutor tutor;
    private int date; // from 0 to 31

    private AppointmentNote appointmentNote;
    private StudentNote studentNote;

    public Appointment(Student student, Tutor tutor) {
        setTutor(tutor);
        setStudent(student);
    }   

    public void setDate(int date) {
        if (0 < date || date > 31) {
            throw new IllegalArgumentException();
        }
        this.date = date;
    }

    public int getDate() {
        return this.date;
    }

    private void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    public Tutor getTutor(){
        return this.tutor;
    }

    private void setStudent(Student student) {
        this.student = student;
    }

    public Student getStudent() {
        return this.student;
    }

    public void markAsConfirmed() {
        System.out.println("Appointment confirmed by tutor");
        this.confirmedByTutor = true;
    }

    public boolean getConfirmationStatus() {
        return this.confirmedByTutor;
    }
}
