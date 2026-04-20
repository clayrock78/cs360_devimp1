import src.main.java.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Tests {
    AppointmentService appointmentService;

    // tests
    /* 
    1. make sure student can login
    2. make sure student can't schedule appnts on busy tutor days
    3. make sure student can schedule appnts 

    Tutor
    1. tutor can login
    2. tutor can set availability
    
    */

    @Test
    public static void tutorCanLogin() {
        String role = "tutor";
        String email = "asmartman@tutor.net";
        String name = "Alfred Smartman";
        User user = Main.login(role, email, name);
        assertTrue(user instanceof Tutor);
        assertEquals(user.getEmail(), email);
        assertEquals(user.getName(), name);

    }

    @Test
    public static void studentCanLogin() {
        String role = "student";
        String email = "cbrunw02@pfw.edu";
        String name = "Chad Brunswick";
        User user = Main.login(role, email, name);
        assertTrue(user instanceof Student);
        assertEquals(user.getEmail(), email);
        assertEquals(user.getName(), name);
    }

    @Test
    public void studentCanMakeAppointment() {
        String studentRole = "student";
        String studentEmail = "cbrunw02@pfw.edu";
        String studentName = "Chad Brunswick";
        User user1 = Main.login(studentRole, studentName, studentName);
        Student student = null;
        if (user1 instanceof Student) {
            student = (Student) user1;
        }

        String tutorRole = "tutor";
        String tutorEmail = "asmartman@tutor.net";
        String tutorName = "Alfred Smartman";
        User user2 = Main.login(tutorRole, tutorEmail, tutorName);
        Tutor tutor = null;
        if (user2 instanceof Tutor) {
            tutor = (Tutor) user2;
        }
        

        appointmentService.bookAppointment(student, tutor, 1);

    }

}
