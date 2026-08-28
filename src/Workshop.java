import java.util.ArrayList;
import java.util.List;

public class Workshop {
    private String title;
    private int attendeeCount;
    private ArrayList<Supply> supplies;

    public Workshop(String title, int attendeeCount) {
        this.title = title;
        this.attendeeCount = attendeeCount;
        this.supplies = new ArrayList<>();
    }
}