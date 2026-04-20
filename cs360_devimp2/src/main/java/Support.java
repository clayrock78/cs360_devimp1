package src.main.java;

import java.util.ArrayList;
import java.util.List;

public class Support {
    private final List<String> tickets = new ArrayList<>();

    public void sendTicket(String ticket) {
        tickets.add(ticket);
        System.out.println("Sent ticket to support");
    }
}
