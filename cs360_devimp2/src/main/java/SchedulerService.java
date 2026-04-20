package src.main.java;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SchedulerService implements TutorScheduler {

    private final Map<Integer, Set<Integer>> reservedDatesByTutorId = new HashMap<>();

    @Override
    public boolean isAvailable(Tutor tutor, int date) {
        return !getReservedDates(tutor).contains(date);
    }

    @Override
    public void reserveSlot(Tutor tutor, int date) {
        getReservedDates(tutor).add(date);
    }

    private Set<Integer> getReservedDates(Tutor tutor) {
        return reservedDatesByTutorId.computeIfAbsent(tutor.getId(), key -> new HashSet<>());
    }
}
