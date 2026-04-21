public class StudentNote extends SessionNote {

    @Override
    public void setNote(String note) {
        System.out.println("students cannot edit notes.");
    }
}
