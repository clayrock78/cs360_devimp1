import java.util.ArrayList;
import java.util.List;

public class SessionNote {
    private final List<String> notes = new ArrayList<>();

    public void setNote(String note) {
        notes.add(note);
    }

    public String getNotes() {
        StringBuilder noteText = new StringBuilder();
        for (String note : notes) {
            noteText.append(note).append("\n");
        }
        return noteText.toString();
    }

    public String getNote() {
        if (notes.isEmpty()) {
            return null;
        }
        return notes.get(notes.size() - 1);
    }
}
