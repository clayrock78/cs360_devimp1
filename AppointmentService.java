import java.util.ArrayList;
import java.util.List;

public class AppointmentService {

    private List<Appointment> appointments = new ArrayList<>();

    private SchedulerService schedulerService;
    private NotificationService notificationService;

    public AppointmentService(SchedulerService schedulerService,
                              NotificationService notificationService) {

        this.schedulerService = schedulerService;
        this.notificationService = notificationService;
    }

    public Appointment bookAppointment(Student student, Tutor tutor, int date) {

        boolean available = schedulerService.isAvailable(tutor, date);

        if (!available) {
            throw new RuntimeException("Tutor not available at that time.");
        }

        Appointment appointment = new Appointment(student, tutor);
        appointment.setDate(date);

        appointments.add(appointment);

        schedulerService.reserveSlot(tutor, date);

        notificationService.notifyBooking(tutor, student);

        return appointment;
    }

    public List<Appointment> getAppointmentsForTutor(Tutor tutor) {

        List<Appointment> result = new ArrayList<>();

        for (Appointment a : appointments) {
            if (a.getTutor().equals(tutor)) {
                result.add(a);
            }
        }

        return result;
    }

}