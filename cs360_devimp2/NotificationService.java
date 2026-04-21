public class NotificationService implements AppointmentNotifier {

    @Override
    public void notifyAppointmentRequest(Appointment appointment) {
        System.out.println(buildTutorConfirmationMessage(appointment));
        receiveAppointmentConfirmation(appointment);
    }

    public void receiveAppointmentConfirmation(Appointment appointment) {
        appointment.markAsConfirmed();
        System.out.println(buildStudentConfirmationMessage(appointment));
    }

    @Override
    public void notifyBooking(Tutor tutor, Student student) {
        System.out.println("Email sent to tutor: " + tutor.getName());
        System.out.println("Email sent to student: " + student.getName());
    }

    @Override
    public void notifyReview(Tutor tutor, Student student, String review) {
        System.out.println("Tutor notified of review from " + student.getName());
    }

    private String buildTutorConfirmationMessage(Appointment appointment) {
        return String.format(
                "Notifying tutor (at %s) to confirm appointment request",
                appointment.getTutor().getEmail()
        );
    }

    private String buildStudentConfirmationMessage(Appointment appointment) {
        return String.format(
                "Notifying student (at %s) that their appointment has been confirmed",
                appointment.getStudent().getEmail()
        );
    }
}
