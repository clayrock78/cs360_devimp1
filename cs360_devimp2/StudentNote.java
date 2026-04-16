// student-read only 
public class StudentNote extends SessionNote {
    
    public void setNote(String newNote){
        System.out.println("students cannot edit notes.");
    }
}
