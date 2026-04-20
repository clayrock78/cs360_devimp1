import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    private static final TutorCatalog tutorCatalog = new TutorCatalog();
    private static final SchedulerService schedulerService = new SchedulerService();
    private static final NotificationService notificationService = new NotificationService();
    private static final AppointmentService appointmentService = new AppointmentService(schedulerService, notificationService);

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

        String role = prompt("Login as (student/tutor): ");
        String email = prompt("Enter email: ");
        String name = prompt("Enter name: ");

        if (role.equalsIgnoreCase("student")) {
            return new Student(name, email);
        }

        return new Tutor(name, email);
    }

    private static void studentMenu(Student student) {

        while (true) {
            printStudentMenu();
            int choice = readMenuChoice();

            switch (choice) {
                case 1:
                    searchTutorByName();
                    break;
                case 2:
                    searchTutorBySubject();
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
                default:
                    System.out.println("Invalid menu option.");
                    break;
            }
        }
    }

    private static void searchTutorByName() {

        String name = prompt("Enter tutor name: ");

        try {
            List<Tutor> tutors = tutorCatalog.findTutorsByName(name);
            printTutorNames("Tutor found:", tutors);
        } catch (TutorNotFoundException e) {
            System.out.println("Tutor not found.");
        }
    }

    private static void searchTutorBySubject() {

        String subject = prompt("Enter subject: ");

        List<String> subjects = new ArrayList<>();
        subjects.add(subject);

        List<Tutor> matches = tutorCatalog.findBestTutorsBySubjects(subjects);

        if (matches.isEmpty()) {
            System.out.println("No tutors found for subject: " + subject);
            return;
        }

        printTutorNames("Matching Tutors:", matches);
    }

    private static void bookAppointment(Student student) {

        String tutorName = prompt("Tutor name: ");

        try {
            Tutor tutor = tutorCatalog.findTutorByName(tutorName);
            int date = Integer.parseInt(prompt("Enter date (1-31): "));

            Appointment appointment = appointmentService.bookAppointment(student, tutor, date);

            System.out.println("Appointment booked with "
                    + tutor.getName() + " on day " + appointment.getDate());
        } catch (TutorNotFoundException e) {
            System.out.println("Tutor not found.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void printTutorAvailability() {
        String name = prompt("Enter tutor name: ");

        try {
            List<Tutor> tutors = tutorCatalog.findTutorsByName(name);

            for (Tutor tutor : tutors) {
                tutor.printAvailability();
            }
        } catch (TutorNotFoundException e) {
            System.out.println("Tutor not found.");
        }
    }

    private static void leaveReview(Student student) {

        String tutorName = prompt("Tutor name: ");

        try {
            Tutor tutor = tutorCatalog.findTutorByName(tutorName);
            String review = prompt("Write review: ");

            System.out.println("Review saved.");
            notificationService.notifyReview(tutor, student, review);
        } catch (Exception e) {
            System.out.println("Failed to submit review.");
        }
    }

    private static void tutorMenu(Tutor tutor) {

        while (true) {
            printTutorMenu();
            int choice = readMenuChoice();

            switch (choice) {
                case 1:
                    viewAppointments(tutor);
                    break;
                case 2:
                    confirmAppointment();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid menu option.");
                    break;
            }
        }
    }

    private static void viewAppointments(Tutor tutor) {

        List<Appointment> appointments = appointmentService.getAppointmentsForTutor(tutor);

        if (appointments.isEmpty()) {
            System.out.println("No appointments scheduled.");
            return;
        }

        for (Appointment appointment : appointments) {
            System.out.println(
                    "Student: " + appointment.getStudent().getName()
                            + " | Day: " + appointment.getDate()
            );
        }
    }

    private static void confirmAppointment() {
        System.out.println("Appointment confirmed.");
    }

    private static String prompt(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    private static int readMenuChoice() {
        return Integer.parseInt(scanner.nextLine());
    }

    private static void printStudentMenu() {
        System.out.println("\nStudent Menu");
        System.out.println("1 Search tutor by name");
        System.out.println("2 Search tutors by subject");
        System.out.println("3 Book appointment");
        System.out.println("4 Leave review");
        System.out.println("5 View tutor availability");
        System.out.println("0 Exit");
    }

    private static void printTutorMenu() {
        System.out.println("\nTutor Menu");
        System.out.println("1 View appointments");
        System.out.println("2 Confirm appointment");
        System.out.println("0 Exit");
    }

    private static void printTutorNames(String heading, List<Tutor> tutors) {
        System.out.println(heading);
        for (Tutor tutor : tutors) {
            System.out.println("- " + tutor.getName());
        }
    }
}
