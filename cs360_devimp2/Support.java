import java.util.*;

public class Support {
    private List<String> ticketList = new ArrayList<String>();
    public void sendTicket(String ticket) {
        ticketList.add(ticket);
        System.out.println("Sent ticket to support");
    }
}