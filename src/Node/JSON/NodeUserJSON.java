package Node.JSON;

public class NodeUserJSON {
    private String username;
    private String password;
    private String nama;
    public NodeUserJSON(){
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