package Controller;

import java.lang.reflect.Type;

public interface ControllerTemplate {
    abstract void insert(String no_resi, String tanggalTiba, String penerima, String alamat, String kota, int berat, String namaPetugas, String namaKategori);

    abstract void update(String no_resi, String penerima, String alamat, String kota, int berat, String namaKategori);

    abstract void delete(String no_resi);
}
