package Model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Barang {
    private String no_resi;
    private String Tanggaltiba;
    private String Penerima;
    private int berat;
    private String namaPetugas;
    private Kategori kategori;


    public Barang(String no_resi, String tanggaltiba, String Penerima, int berat, String namaPetugas,Kategori kategori) {
        this.no_resi = no_resi;
        this.Tanggaltiba = tanggaltiba;
        this.Penerima = Penerima;
        this.berat = berat;
        this.namaPetugas = namaPetugas;
        this.kategori = kategori;
    }

    public Barang(){

    }

    public String getNo_resi() {
        return no_resi;
    }

    public String getPenerima() {
        return Penerima;
    }

    public String getTanggaltiba() {
        return Tanggaltiba;
    }



    public int getBerat() {
        return berat;
    }

    public String getNamaPetugas() {
        return namaPetugas;
    }

    public Kategori getKategori() {
        return kategori;
    }
}