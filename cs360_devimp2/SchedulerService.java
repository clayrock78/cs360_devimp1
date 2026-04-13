import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SchedulerService {

    private Map<Integer, Set<Integer>> tutorSchedule = new HashMap<>();

    public boolean isAvailable(Tutor tutor, int date) {

        Set<Integer> reserved = tutorSchedule.get(tutor.getID());

        if (reserved == null) return true;

        return !reserved.contains(date);
    }

    public void reserveSlot(Tutor tutor, int date) {

        tutorSchedule.putIfAbsent(tutor.getID(), new HashSet<>());

        tutorSchedule.get(tutor.getID()).add(date);
    }

}