package Controller;

import Controller.JSON.CBarangJSON;
import Controller.JSON.CKategoriJSON;
import Model.Barang;
import Model.CheckoutBarang;
import Model.Kategori;
import View.auth.Login;
import View.res.Warna;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


public class ControllerBarang extends CBarangJSON {
    private ArrayList<Barang> listBarangMasuk = readFromFileIn();
    private ArrayList<Barang> listBarang = readFromFile();
    private ArrayList<CheckoutBarang> listBarangKeluar = readFromFileOut();

    public void tambahBarang(String no_resi, String tanggalTiba, String penerima, int berat, String namaPetugas, String namaKategori) {
        if (cekFile()){
            ArrayList<Barang> barangArrayList = readFromFile();
            if(barangArrayList != null){
                barangArrayList.add(new Barang(no_resi, tanggalTiba, penerima, berat, namaPetugas, namaKategori));
                System.out.println("Barang berhasil ditambahkan!");
                writeFileJSON(barangArrayList);
                writeFJSONIn(barangArrayList);
            }else{
                barangArrayList = new ArrayList<Barang>();
                barangArrayList.add(new Barang(no_resi, tanggalTiba, penerima, berat, namaPetugas, namaKategori));
                writeFileJSON(barangArrayList);
                writeFJSONIn(barangArrayList);
            }
        } else {
            System.out.println("Database Barang tidak ada !");
        }
    }

    public boolean updateBarang(String no_resi, String penerima, int berat, String namaKategori){
        Barang dataBarang = searchBarang(no_resi);
        if(dataBarang != null){
            dataBarang.setPenerima(penerima);
            dataBarang.setBerat(berat);
            dataBarang.setKategori(namaKategori);
            writeFileJSON(listBarang);
            return true;
        }
        return false;
    }

    public boolean deleteBarang(String no_resi){
        Barang dataBarang = searchBarang(no_resi);
        if(dataBarang != null){
            listBarang.remove(dataBarang);
            writeFileJSON(listBarang);
            return true;
        }
        return false;
    }

    public Barang searchBarang(String cari){
        Barang dataBarang = null;
        for (Barang barang:listBarang){
            if(cari.equals(barang.getNamaPetugas()) || cari.equals(barang.getKategori().getNama()) || cari.equals(barang.getNo_resi())){
                return barang;
            }
        }
        return dataBarang;
    }

    public void listBarangMasuk(){
        listBarangMasuk = readFromFileIn();
        if(listBarangMasuk != null){
            for (Barang barang:listBarangMasuk){
                System.out.println(Warna.output_list+"-----------------");
                System.out.println("Nomor Resi      : "+barang.getNo_resi());
                System.out.println("Kategori        : " + barang.getKategori().getNama());
                System.out.println("Berat           : "+barang.getBerat());
                System.out.println("Penerima        : "+barang.getPenerima());
                System.out.println("Tanggal         : "+ barang.getTanggaltiba());
                System.out.println("Petugas         : "+barang.getNamaPetugas());
                System.out.println("-----------------"+Warna.reset_warna);
            }
        }else{
            System.out.println("Tidak ada barang!");
        }
    }
    public void listBarang(){
        listBarang = readFromFile();
        if(listBarang != null){
            for (Barang barang:listBarang){
                System.out.println(Warna.output_list+"-----------------");
                System.out.println("Nomor Resi      : "+barang.getNo_resi());
                System.out.println("Kategori        : " + barang.getKategori().getNama());
                System.out.println("Berat           : "+barang.getBerat());
                System.out.println("Penerima        : "+barang.getPenerima());
                System.out.println("Tanggal         : "+ barang.getTanggaltiba());
                System.out.println("Petugas         : "+barang.getNamaPetugas());
                System.out.println("-----------------"+Warna.reset_warna);
            }
        }else{
            System.out.println("Tidak ada barang!");
        }
    }

    public void listBarangKeluar(){
        listBarangKeluar = readFromFileOut();
        if(listBarangKeluar != null){
            for (CheckoutBarang barang:listBarangKeluar){
                System.out.println(Warna.output_list+"-----------------");
                System.out.println("Nomor Resi      : "+barang.getNo_resi());
                System.out.println("Kategori        : " + barang.getKategori().getNama());
                System.out.println("Berat           : "+barang.getBerat());
                System.out.println("Penerima        : "+barang.getPenerima());
                System.out.println("Tanggal Masuk   : "+ barang.getTanggaltiba());
                System.out.println("Petugas         : "+barang.getNamaPetugas());
                System.out.println("Tanggal Keluar  : "+barang.getTanggalKeluar());
                System.out.println("-----------------"+Warna.reset_warna);
            }
        }else{
            System.out.println("Tidak ada barang!");
        }
    }

    public void listKategori(){
        CKategoriJSON cKategoriJSON = new CKategoriJSON();
        ArrayList<Kategori> listKategori = cKategoriJSON.readFromFile();
        System.out.println("Kategori : ");
        for (int i=0; i<listKategori.size(); i++){
            System.out.println((i+1)+". "+listKategori.get(i).getNama());
        }
    }

    public String pilihKategori(int pilihan){
        CKategoriJSON cKategoriJSON = new CKategoriJSON();
        String namaKat="";
        ArrayList<Kategori> listKategori = cKategoriJSON.readFromFile();
        for (Kategori kategori:listKategori){
            if(pilihan == kategori.getNomor()){
                namaKat = kategori.getNama();
                break;
            }
        }
        return namaKat;
    }

    public void checkoutBarang(String cari, String tanggalKeluar){
        int checkout = 0;
        if (cekFile()){
            ArrayList<Barang> barangArrayList = readFromFile();
            if(barangArrayList != null){
                Iterator<Barang> iterator = barangArrayList.iterator();
                while(iterator.hasNext()){
                    Barang barang = iterator.next();
                    if(cari.equals(barang.getNo_resi())){
                        if (cekFileOut()){
                            ArrayList<CheckoutBarang> newArrList = readFromFileOut();
                            if(newArrList != null){
                                newArrList.add(new CheckoutBarang(barang.getNo_resi(), barang.getTanggaltiba(), barang.getPenerima(), barang.getBerat(), Login.namaP, barang.getKategori().getNama(), tanggalKeluar));

                            }else{
                                newArrList = new ArrayList<CheckoutBarang>();
                                newArrList.add(new CheckoutBarang(barang.getNo_resi(), barang.getTanggaltiba(), barang.getPenerima(), barang.getBerat(), Login.namaP, barang.getKategori().getNama(), tanggalKeluar));
                            }
                            writeFJSON(newArrList);
                            iterator.remove();
                            checkout = 1;
                        } else {
                            System.out.println("Database Barang tidak ada !");
                        }
                    }
                }
                writeFileJSON(barangArrayList);
                if(checkout == 1){
                    System.out.println("Checkout Barang Berhasil !");
                }else{
                    System.out.println("Barang tidak ditemukan!");
                }
            } else {
                System.out.println("Tidak ada Data !");
            }
        } else {
            System.out.println("Database tidak ada");
        }
    }

    public void cetakLaporan(String jenis_laporan){
        String path;
        if(jenis_laporan.equals("masuk")){
            path = "src/Laporan/fileLaporanMasuk.txt";
        }else if(jenis_laporan.equals("keluar")){
            path = "src/Laporan/fileLaporanKeluar.txt";
        }else{
            path = "src/Laporan/fileLaporan.txt";
        }
        try {
            File myObj = new File(path);
            if (myObj.createNewFile()) {
                System.out.println("File Laporan berhasil dibuat: " + myObj.getName());
            } else {
                System.out.println("File Laporan sudah ada.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        DayOfWeek dow = now.getDayOfWeek();
        //Write File
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))) {
            if (jenis_laporan.equals("masuk")) {
                writer.write("---------------------------------------------------------------------------------------------------------\n\t\t\t\t\t\t\t\t\t\t\tLAPORAN BARANG MASUK\n---------------------------------------------------------------------------------------------------------\n");
                writer.write("Tanggal : "+dow+", "+dtf.format(now)+
                                 "\nPetugas : "+Login.namaP+
                                "\n---------------------------------------------------------------------------------------------------------");
                writer.write("\nNOMOR RESI \t\tKATEGORI \t\tBERAT \t\tPENERIMA \t\tTANGGAL \t\t\t\t\tPETUGAS\n");
                if (listBarangMasuk != null) {
                    for (Barang barang:listBarangMasuk){
                        writer.write(
                                barang.getNo_resi()+" \t\t"+
                                        barang.getKategori().getNama()+" \t\t\t"+
                                        barang.getBerat()+" \t\t"+
                                        barang.getPenerima()+" \t\t\t"+
                                        barang.getTanggaltiba()+" \t\t"+
                                        barang.getNamaPetugas()+"\n"
                        );
                    }
                } else {
                    System.out.println("Tidak ada barang!");
                }
            } else if (jenis_laporan.equals("keluar")) {
                path = "src/Laporan/fileLaporanKeluar.txt";
            } else {
                path = "src/Laporan/fileLaporan.txt";
            }


            System.out.println("Berhasil Menulis File.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}