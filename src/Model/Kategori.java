package Model;

public class Kategori {
    private int nomor;
    private String nama;

    public Kategori(int nomor, String nama) {
        this.nomor = nomor;
        this.nama = nama;
    }
    public Kategori(String nama) {
        this.nama = nama;
    }

    public int getNomor() {
        return nomor;
    }

    public String getNama() {
        return nama;
    }
}
