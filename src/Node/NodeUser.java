package Node;

public class NodeUser {
    private String username;
    private String password;
    private String nama;

    public NodeUser(String username, String password, String nama) {
        this.username = username;
        this.password = password;
        this.nama = nama;
    }

    public NodeUser(){

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

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
