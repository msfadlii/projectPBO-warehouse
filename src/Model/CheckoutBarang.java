package Model;

public class CheckoutBarang extends Barang{
    private String tanggalKeluar;

    public CheckoutBarang(String no_resi, String tanggalTiba, String Penerima, int berat, String namaPetugas, String namaKategori, String tanggalKeluar) {
        super(no_resi, tanggalTiba, Penerima, berat, namaPetugas, namaKategori);
        this.tanggalKeluar = tanggalKeluar;
    }

    public String getTanggalKeluar() {
        return tanggalKeluar;
    }
}
