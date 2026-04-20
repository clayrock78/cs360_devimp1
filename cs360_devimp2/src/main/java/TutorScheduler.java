package src.main.java;

public interface TutorScheduler {
    boolean isAvailable(Tutor tutor, int date);
    void reserveSlot(Tutor tutor, int date);
}
