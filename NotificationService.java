public class NotificationService {
    public void notifyAppointmentRequest(Appointment appointment) {
        System.out.printf("Notifying tutor (at %s) to confirm appointment request\n", 
            appointment.getTutor().getEmail()
        );
        // pretend tutor has been sent a request to accept and has confirmed it
        receiveAppointmentConfirmation(appointment);
    }

    public void receiveAppointmentConfirmation(Appointment appointment) {
        appointment.markAsConfirmed();
        System.out.printf("Notifying student (at %s that their appointment has been confirmed\n",
            appointment.getStudent().getEmail()
        );
    }

    public void notifyBooking(Tutor tutor, Student student) {

        System.out.println("Email sent to tutor: " + tutor.getName());
        System.out.println("Email sent to student: " + student.getName());
    }

    public void notifyReview(Tutor tutor, Student student, String review) {

        System.out.println("Tutor notified of review from " + student.getName());
    }
}
