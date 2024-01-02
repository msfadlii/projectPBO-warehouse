package Node;

public class NodePetugas {
    private String username;
    private String password;
    private String nama;

    public NodePetugas(String username, String password, String nama) {
        this.username = username;
        this.password = password;
        this.nama = nama;
    }

    public NodePetugas(){

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
