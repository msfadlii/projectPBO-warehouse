package Node;

public class NodeCheckoutBarang extends NodeBarang {
    private String tanggalKeluar;
    //Kurir?
    public NodeCheckoutBarang(String no_resi, String tanggalTiba, String penerima, String alamat, int berat, String namaPetugas, String namaKategori, String tanggalKeluar) {
        super(no_resi, tanggalTiba, penerima, alamat, berat, namaPetugas, namaKategori);
        this.tanggalKeluar = tanggalKeluar;
    }

    public String getTanggalKeluar() {
        return tanggalKeluar;
    }
}
