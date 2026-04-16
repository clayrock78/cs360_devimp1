import java.util.BitSet;

public class Calendar {

    public static void printCalendar(BitSet days, int startOffset) {
        if (days.size() != 31) {
            throw new IllegalArgumentException("Expected BitSet of size 31 for days.");
        }

        // Header
        System.out.println(" Su Mo Tu We Th Fr Sa");

        int dayNumber = 1;
        int totalSlots = 42; // 6 weeks * 7 days (covers any month layout)

        for (int slot = 0; slot < totalSlots; slot++) {
            if (slot < startOffset || dayNumber > 31) {
                // Empty cell
                System.out.print("   ");
            } else {
                boolean marked = days.get(dayNumber - 1);
                String label = marked ? String.format("%2d*", dayNumber) 
                                      : String.format("%2d ", dayNumber);
                System.out.print(label);
            }

            // New line after Saturday
            if ((slot + 1) % 7 == 0) {
                System.out.println();
            }

            if (slot >= startOffset && dayNumber <= 31) {
                dayNumber++;
            }
        }
    }
}