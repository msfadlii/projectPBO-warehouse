package Model;

public class UserJSON {
    private String username;
    private String password;
    private String nama;
    public UserJSON(){
        this.username = "username";
        this.password = "password";
        this.nama = "nama";
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getNama() {
        return nama;
    }
}