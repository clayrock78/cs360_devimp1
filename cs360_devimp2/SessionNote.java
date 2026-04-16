import java.util.*;
public class SessionNote {
    List<String> notes = new ArrayList<String>();

    public void setNote(String note) {
        notes.add(note);
    }

    public String getNotes() {
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < notes.size(); i++) {
            string.append(notes.get(i) + "\n");
        }
        return string.toString();
    }
    private String note;

    public String getNote() {
        return this.note;
    }
}
