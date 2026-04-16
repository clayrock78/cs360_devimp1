public class User {
    private String name;
    private String email;
    private static int nextUserId = 0;
    private final int id;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    public int getID() {
        return getId();
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User(String name, String email, int id) {
        this.name = name;
        this.email = email;
        this.id = id;
        nextUserId = Math.max(nextUserId, id + 1);
    }

    public User(String name, String email) {
        this(name, email, nextUserId);
    }
}
