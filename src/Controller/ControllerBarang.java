package Controller;

import Controller.JSON.CBarangJSON;
import Model.Barang;
import Model.Kategori;

import java.util.ArrayList;

public class ControllerBarang extends CBarangJSON {

    public void tambahBarang(String no_resi, String tanggalTiba, String penerima, int berat, String namaPetugas) {
        if (cekFile()){
            ArrayList<Barang> barangArrayList = readFromFile();
            if(barangArrayList != null){
                barangArrayList.add(new Barang(no_resi, tanggalTiba, penerima, berat, namaPetugas));
                System.out.println("Barang berhasil ditambahkan!");
                writeFileJSON(barangArrayList);
            }else{
                barangArrayList = new ArrayList<Barang>();
                barangArrayList.add(new Barang(no_resi, tanggalTiba, penerima, berat, namaPetugas));
                writeFileJSON(barangArrayList);
            }
        } else {
            System.out.println("Database Barang tidak ada !");
        }
    }
    public void listBarang(){
        ArrayList<Barang> listBarang = readFromFile();
        for (Barang barang:listBarang){
            System.out.println("-----------------");
            System.out.println("Nomor Resi : "+barang.getNo_resi());
            System.out.println("Tanggal : "+ barang.getTanggaltiba());
            System.out.println("Penerima : "+barang.getPenerima());
            System.out.println("Berat : "+barang.getBerat());
            System.out.println("Nama Petugas : "+barang.getNamaPetugas());
            System.out.println("Kategori : " + barang.getKategori());
        }


    }
}
