import java.util.BitSet;

public class Calendar {

    private static final int DAYS_IN_MONTH = 31;
    private static final int DAYS_IN_WEEK = 7;
    private static final int TOTAL_SLOTS = 42;

    public static void printCalendar(BitSet days, int startOffset) {
        validateCalendar(days);
        printHeader();

        int dayNumber = 1;

        for (int slot = 0; slot < TOTAL_SLOTS; slot++) {
            printSlot(days, startOffset, dayNumber, slot);

            if (shouldStartNewLine(slot)) {
                System.out.println();
            }

            if (slot >= startOffset && dayNumber <= DAYS_IN_MONTH) {
                dayNumber++;
            }
        }
    }

    private static void validateCalendar(BitSet days) {
        if (days.size() != DAYS_IN_MONTH) {
            throw new IllegalArgumentException("Expected BitSet of size 31 for days.");
        }
    }

    private static void printHeader() {
        System.out.println(" Su Mo Tu We Th Fr Sa");
    }

    private static void printSlot(BitSet days, int startOffset, int dayNumber, int slot) {
        if (slot < startOffset || dayNumber > DAYS_IN_MONTH) {
            System.out.print("   ");
            return;
        }

        boolean marked = days.get(dayNumber - 1);
        String label = marked ? String.format("%2d*", dayNumber)
                : String.format("%2d ", dayNumber);
        System.out.print(label);
    }

    private static boolean shouldStartNewLine(int slot) {
        return (slot + 1) % DAYS_IN_WEEK == 0;
    }
}
