import java.util.ArrayList;
import java.util.List;

public class Workshop {
    private final String title;
    private int attendees;
    private final List<Supply> supplies;

    public Workshop(String title, int attendees) {
        this.title = title;
        this.attendees = attendees;
        this.supplies = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public int getAttendees() {
        return attendees;
    }

    public List<Supply> getSupplies() {
        return new ArrayList<>(supplies);
    }

    public void addSupply(String supplyName, double amount) {
        supplies.add(new Supply(supplyName, amount));
    }
}