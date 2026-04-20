import java.util.ArrayList;
import java.util.List;

public class AppointmentService {

    private final List<Appointment> appointments = new ArrayList<>();

    private final TutorScheduler schedulerService;
    private final AppointmentNotifier notificationService;

    public AppointmentService(TutorScheduler schedulerService,
                              AppointmentNotifier notificationService) {

        this.schedulerService = schedulerService;
        this.notificationService = notificationService;
    }

    public Appointment bookAppointment(Student student, Tutor tutor, int date) {
        ensureTutorAvailability(tutor, date);

        Appointment appointment = createAppointment(student, tutor, date);
        appointments.add(appointment);

        schedulerService.reserveSlot(tutor, date);
        notificationService.notifyBooking(tutor, student);

        return appointment;
    }

    public List<Appointment> getAppointmentsForTutor(Tutor tutor) {

        List<Appointment> tutorAppointments = new ArrayList<>();

        for (Appointment appointment : appointments) {
            if (appointment.getTutor().equals(tutor)) {
                tutorAppointments.add(appointment);
            }
        }

        return tutorAppointments;
    }

    private void ensureTutorAvailability(Tutor tutor, int date) {
        if (!schedulerService.isAvailable(tutor, date)) {
            throw new IllegalStateException("Tutor not available at that time.");
        }
    }

    private Appointment createAppointment(Student student, Tutor tutor, int date) {
        Appointment appointment = new Appointment(student, tutor);
        appointment.setDate(date);
        return appointment;
    }
}
