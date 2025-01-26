import java.util.ArrayList;
import java.util.List;

public class User {
    
    private String username;
    private List<Entry> entries;

    public User(String username ) {
        this.username = username;
        this.entries = new ArrayList<Entry>();
    }

    public String getUsername() {
        return username;
    }

    public List<Entry> getEntries() {
        return entries;
    }

    public void addEntry(Entry entry) {
        entries.add(entry);
    }

    public static void main(String[] args) {
        User user = new User("johndoe");
        System.out.println(user.getUsername());
    }
}
