public interface AppointmentNotifier {
    void notifyAppointmentRequest(Appointment appointment);
    void notifyBooking(Tutor tutor, Student student);
    void notifyReview(Tutor tutor, Student student, String review);
}
