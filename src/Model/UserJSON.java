package Model;

public class UserJSON {
    private String username;
    private String password;
    public UserJSON(){
        this.username = "username";
        this.password = "password";
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}