package Node;

public class NodeCheckoutBarang extends NodeBarang {
    private String tanggalKeluar;

    public NodeCheckoutBarang(String no_resi, String tanggalTiba, String Penerima, int berat, String namaPetugas, String namaKategori, String tanggalKeluar) {
        super(no_resi, tanggalTiba, Penerima, berat, namaPetugas, namaKategori);
        this.tanggalKeluar = tanggalKeluar;
    }

    public String getTanggalKeluar() {
        return tanggalKeluar;
    }
}
