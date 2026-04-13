
import java.util.*;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    private static TutorCatalog tutorCatalog = new TutorCatalog();
    private static SchedulerService schedulerService = new SchedulerService();
    private static NotificationService notificationService = new NotificationService();
    private static AppointmentService appointmentService = new AppointmentService(schedulerService, notificationService);

    public static void main(String[] args) {

        System.out.println("=== Tutoring Service MVP ===");

        User currentUser = login();

        if (currentUser instanceof Student) {
            studentMenu((Student) currentUser);
        } else if (currentUser instanceof Tutor) {
            tutorMenu((Tutor) currentUser);
        }

        System.out.println("Goodbye!");
    }

    private static User login() {

        System.out.print("Login as (student/tutor): ");
        String role = scanner.nextLine();

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        // AuthService stub
        if (role.equalsIgnoreCase("student")) {
            return new Student(name, email);
        }

        return new Tutor(name, email);
    }

    /* ------------------------------
       STUDENT FLOW
    ------------------------------ */
    private static void studentMenu(Student student) {

        while (true) {

            System.out.println("\nStudent Menu");
            System.out.println("1 Search tutor by name");
            System.out.println("2 Search tutors by subject");
            System.out.println("3 Book appointment");
            System.out.println("4 Leave review");
            System.out.println("5 View tutor availability");
            System.out.println("0 Exit");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {

                case 1:
                    try {
                        searchTutorByName();
                    } catch (Exception e) {
                        System.out.println("No tutors found.");
                    }
                    ;
                    break;

                case 2:
                    try {
                        searchTutorBySubject();
                    } catch (Exception e) {
                        System.out.println("No tutors found");
                    }
                    break;

                case 3:
                    bookAppointment(student);
                    break;

                case 4:
                    leaveReview(student);
                    break;

                case 5:
                    printTutorAvailability();
                    break;

                case 0:
                    return;
            }
        }
    }

    private static void searchTutorByName() {

        System.out.print("Enter tutor name: ");
        String name = scanner.nextLine();

        try {

            List<Tutor> tutors = tutorCatalog.findTutorsByName(name);

            for (Tutor tutor: tutors)
                System.out.println("Tutor found: " + tutor.getName());

        } catch (TutorNotFoundException e) {

            System.out.println("Tutor not found.");
        }
    }

    private static void searchTutorBySubject() {

        System.out.print("Enter subject: ");
        String subject = scanner.nextLine();

        List<String> subjects = new ArrayList<>();
        subjects.add(subject);

        List<Tutor> matches = tutorCatalog.findBestTutorsBySubjects(subjects);

        if (matches.isEmpty()) {
            System.out.println("No tutors found for subject: " + subject);
            return;
        }

        System.out.println("Matching Tutors:");

        for (Tutor tutor : matches) {
            System.out.println("- " + tutor.getName());
        }
    }

    private static void bookAppointment(Student student) {

        System.out.print("Tutor name: ");
        String tutorName = scanner.nextLine();

        try {

            Tutor tutor = tutorCatalog.findTutorByName(tutorName);

            System.out.print("Enter date (1-31): ");
            int date = Integer.parseInt(scanner.nextLine());

            Appointment appt
                    = appointmentService.bookAppointment(student, tutor, date);

            System.out.println("Appointment booked with "
                    + tutor.getName() + " on day " + appt.getDate());

        } catch (TutorNotFoundException e) {
            System.out.println("Tutor not found.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void printTutorAvailability() {
        System.out.print("Enter tutor name: ");
        String name = scanner.nextLine();

        try {

            List<Tutor> tutors = tutorCatalog.findTutorsByName(name);

            for (Tutor tutor: tutors) {
                tutor.printAvailability();
            }

        } catch (TutorNotFoundException e) {

            System.out.println("Tutor not found.");
        }
    }

    private static void leaveReview(Student student) {

        System.out.print("Tutor name: ");
        String tutorName = scanner.nextLine();

        try {

            Tutor tutor = tutorCatalog.findTutorByName(tutorName);

            System.out.print("Write review: ");
            String review = scanner.nextLine();

            System.out.println("Review saved.");

            notificationService.notifyReview(tutor, student, review);

        } catch (Exception e) {

            System.out.println("Failed to submit review.");
        }
    }


    /* ------------------------------
       TUTOR FLOW
    ------------------------------ */
    private static void tutorMenu(Tutor tutor) {

        while (true) {

            System.out.println("\nTutor Menu");
            System.out.println("1 View appointments");
            System.out.println("2 Confirm appointment");
            System.out.println("0 Exit");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {

                case 1:
                    viewAppointments(tutor);
                    break;

                case 2:
                    confirmAppointment();
                    break;

                case 0:
                    return;
            }
        }
    }

    private static void viewAppointments(Tutor tutor) {

        List<Appointment> appts
                = appointmentService.getAppointmentsForTutor(tutor);

        if (appts.isEmpty()) {
            System.out.println("No appointments scheduled.");
            return;
        }

        for (Appointment a : appts) {

            System.out.println(
                    "Student: " + a.getStudent().getName()
                    + " | Day: " + a.getDate()
            );
        }
    }

    private static void confirmAppointment() {

        System.out.println("Appointment confirmed.");
    }
}
