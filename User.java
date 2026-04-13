public class User {
    private String name;
    private String email;
    private static int userCount = 0;
    private int ID;

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public String getEmail() {
        return this.email;
    }

    public int getID(){
        return this.ID;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    User(String name, String email, int ID){
        this.name = name;
        this.email = email;
        this.ID = ID;
        User.userCount += 1;
    }

    User(String name, String email){
        this.name = name;
        this.email = email;
        this.ID = User.userCount;
    }

}
