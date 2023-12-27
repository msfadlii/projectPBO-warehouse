package Node;

public class NodeKategori {
    private int nomor;
    private String nama;

    public NodeKategori(int nomor, String nama) {
        this.nomor = nomor;
        this.nama = nama;
    }
    public NodeKategori(String nama) {
        this.nama = nama;
    }

    public int getNomor() {
        return nomor;
    }

    public String getNama() {
        return nama;
    }
}
