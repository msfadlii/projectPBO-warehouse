package View.Barang;

import Controller.ControllerBarang;
import Node.NodeBarang;
import Node.NodeCheckoutBarang;
import View.Login;
import View.res.Warna;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class ViewBarang {
    private ControllerBarang controllerBarang;
    private static Scanner input = new Scanner(System.in);
    String namaP = Login.username;

    public ViewBarang(ControllerBarang cBarang){
        this.controllerBarang = cBarang;
    }

    public void insertBarang(){
        System.out.println(Warna.judul + "----- TAMBAH BARANG -----");
        System.out.print("Masukan nomor resi : ");
        String no_resi = input.nextLine();
        LocalDateTime tanggalTiba = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        System.out.print("Masukkan nama penerima : ");
        String penerima = input.nextLine();
        System.out.print("Masukkan alamat penerima : ");
        String alamat = input.nextLine();
        System.out.print("Masukkan kota penerima : ");
        String kota = input.nextLine();
        System.out.print("Masukkan berat barang : ");
        int berat = input.nextInt(); input.nextLine();
        controllerBarang.listKategori();
        System.out.print("Pilih Kategori : ");
        int pilih_kategori = input.nextInt();
        String kat = controllerBarang.pilihKategori(pilih_kategori);
        controllerBarang.insert(no_resi, tanggalTiba.format(format), penerima, alamat, kota, berat, namaP, kat);
        input.nextLine();
    }

    public void updateBarang(){
        System.out.println(Warna.judul + "----- UPDATE BARANG -----");
        System.out.print("Masukan nomor resi : ");
        String no_resi = input.nextLine();
        System.out.print("Masukkan Berat : ");
        int berat = input.nextInt(); input.nextLine();
        System.out.print("Masukkan nama penerima : ");
        String penerima = input.nextLine();
        System.out.print("Masukkan alamat penerima : ");
        String alamat = input.nextLine();
        System.out.print("Masukkan kota penerima : ");
        String kota = input.nextLine();
        controllerBarang.listKategori();
        System.out.print("Pilih Kategori : ");
        int pilih_kategori = input.nextInt();
        String kat = controllerBarang.pilihKategori(pilih_kategori);
        controllerBarang.update(no_resi, penerima, alamat, kota, berat, kat);
        input.nextLine();
    }

    public void deleteBarang(){
        System.out.println(Warna.judul+"----- DELETE BARANG -----");
        System.out.print("Masukan nomor resi : ");
        String no_resi = input.nextLine();
        controllerBarang.delete(no_resi);
    }

    public void listMasuk(String filter){
//        ArrayList<NodeBarang> arrBarang = controllerBarang.viewBarangMasuk();
        ArrayList<NodeBarang> arrBarang = controllerBarang.viewBarangMasuk(filter);

        if(arrBarang == null|| arrBarang.size() == 0){
            System.out.println("Tidak ada Barang ! ");
        }else{
            for (int i=0; i< arrBarang.size(); i++){
                System.out.println(Warna.output_list+"-----------------");
                System.out.println("Nomor Resi      : "+ arrBarang.get(i).getNo_resi());
                System.out.println("Kategori        : " + arrBarang.get(i).getKategori().getNama());
                System.out.println("Berat           : "+ arrBarang.get(i).getBerat());
                System.out.println("Nama Penerima   : "+ arrBarang.get(i).getNodePenerima().getNamaPenerima());
                System.out.println("Alamat Penerima : "+ arrBarang.get(i).getNodePenerima().getAlamat());
                System.out.println("Kota            : "+ arrBarang.get(i).getNodePenerima().getKota());
                System.out.println("Tanggal         : "+ arrBarang.get(i).getTanggaltiba());
                System.out.println("Petugas         : "+ arrBarang.get(i).getNamaPetugas());
                System.out.println("-----------------"+Warna.reset_warna);
            }
        }
    }

    public void list(String filter){
        ArrayList<NodeBarang> arrBarang = controllerBarang.viewBarang(filter);
        if(arrBarang == null || arrBarang.size() == 0){
            System.out.println("Tidak ada Barang ! ");
        }else{
            for (int i=0; i< arrBarang.size(); i++){
                System.out.println(Warna.output_list+"-----------------");
                System.out.println("Nomor Resi      : "+ arrBarang.get(i).getNo_resi());
                System.out.println("Kategori        : " + arrBarang.get(i).getKategori().getNama());
                System.out.println("Berat           : "+ arrBarang.get(i).getBerat());
                System.out.println("Nama Penerima   : "+ arrBarang.get(i).getNodePenerima().getNamaPenerima());
                System.out.println("Alamat Penerima : "+ arrBarang.get(i).getNodePenerima().getAlamat());
                System.out.println("Kota            : "+ arrBarang.get(i).getNodePenerima().getKota());
                System.out.println("Tanggal         : "+ arrBarang.get(i).getTanggaltiba());
                System.out.println("Petugas         : "+ arrBarang.get(i).getNamaPetugas());
                System.out.println("-----------------"+Warna.reset_warna);
            }
        }
    }

    public void listKeluar(String filter){
        ArrayList<NodeCheckoutBarang> arrBarang = controllerBarang.viewBarangKeluar(filter);
        if(arrBarang == null|| arrBarang.size() == 0){
            System.out.println("Tidak ada Barang ! ");
        }else{
            for (int i=0; i< arrBarang.size(); i++){
                System.out.println(Warna.output_list+"-----------------");
                System.out.println("Nomor Resi      : "+ arrBarang.get(i).getNo_resi());
                System.out.println("Kategori        : "+ arrBarang.get(i).getKategori().getNama());
                System.out.println("Berat           : "+ arrBarang.get(i).getBerat());
                System.out.println("Nama Penerima   : "+ arrBarang.get(i).getNodePenerima().getNamaPenerima());
                System.out.println("Alamat Penerima : "+ arrBarang.get(i).getNodePenerima().getAlamat());
                System.out.println("Tanggal Masuk   : "+ arrBarang.get(i).getTanggaltiba());
                System.out.println("Tanggal Keluar  : "+ arrBarang.get(i).getTanggalKeluar());
                System.out.println("Petugas         : "+ arrBarang.get(i).getNamaPetugas());
                System.out.println("-----------------"+Warna.reset_warna);
            }
        }
    }

    public void cariBarang(){
        System.out.println(Warna.judul + "----- CARI BARANG -----" + Warna.reset_warna);
        System.out.println(Warna.komen + "Cari berdasarkan : " +
                "\nNomor Resi | Kategori | Petugas | Kota | Tanggal" + "\u001B[0m");
        System.out.print(Warna.sub_judul + "Masukkan Pencarian : ");
        String cari = input.nextLine();
        NodeBarang dataNodeBarang = controllerBarang.searchBarang(cari);
        System.out.println();
        if(dataNodeBarang != null){
            System.out.println("Nomor Resi      : "+ dataNodeBarang.getNo_resi());
            System.out.println("Kategori        : " + dataNodeBarang.getKategori().getNama());
            System.out.println("Berat           : "+ dataNodeBarang.getBerat());
            System.out.println("Nama Penerima   : "+ dataNodeBarang.getNodePenerima().getNamaPenerima());
            System.out.println("Alamat Penerima : "+ dataNodeBarang.getNodePenerima().getAlamat());
            System.out.println("Kota            : "+ dataNodeBarang.getNodePenerima().getKota());
            System.out.println("Tanggal         : "+ dataNodeBarang.getTanggaltiba());
            System.out.println("Petugas         : "+ dataNodeBarang.getNamaPetugas());
        }else{
            System.out.println("Barang tidak ada!");
        }
    }

    public void checkoutBarang(){
        // Checkout barang bisa milih antara filter kota atau tanggal

        System.out.println(Warna.judul + "----- CHECKOUT BARANG -----" + Warna.reset_warna);
        System.out.print(Warna.sub_judul+"Masukkan Nomor Resi : ");
        String noResi = input.nextLine();
        LocalDateTime tanggalKeluar = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        controllerBarang.checkout(noResi, tanggalKeluar.format(format));
    }
}
