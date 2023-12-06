package View.petugas;

import Controller.ControllerBarang;
import Model.Barang;
import View.auth.Login;
import View.res.Warna;

import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class PetugasMain {

    public static void main(String[] args) {
        ControllerBarang cBarang = new ControllerBarang();
        Scanner input = new Scanner(System.in);
        String namaP = Login.namaP;
        loop:while (true) {
            System.out.println(Warna.judul+"\n----- PETUGAS -----\n"+Warna.sub_judul +"Halo " + namaP + Warna.menu+"\n1. Data Barang\n2. List Barang\n3. Cari Barang\n4. Checkout Barang\n5. Cetak Laporan\n6. Logout");
            System.out.print(Warna.sub_judul+"Masukan Pilihan Anda : ");
            int pilih = input.nextInt();
            input.nextLine();
            switch (pilih) {
                case 1: {
                    System.out.println(Warna.menu+"1. Tambah Barang\n2. Update Barang\n3. Hapus Barang\n4. Kembali");
                    System.out.print(Warna.sub_judul+"Masukkan Pilihan : ");
                    int pilih1 = input.nextInt(); input.nextLine();
                    switch (pilih1){
                        case 1: {
                            System.out.println(Warna.judul + "----- TAMBAH BARANG -----");
                            System.out.print("Masukan nomor resi : ");
                            String no_resi = input.nextLine();
                            LocalDateTime tanggalTiba = LocalDateTime.now();
                            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                            System.out.print("Masukkan Penerima barang : ");
                            String Penerima = input.nextLine();
                            System.out.print("Masukkan berat barang : ");
                            int berat = input.nextInt(); input.nextLine();
                            cBarang.listKategori();
                            System.out.print("Pilih Kategori : ");
                            int pilih_kategori = input.nextInt();
                            String kat = cBarang.pilihKategori(pilih_kategori);
                            cBarang.tambahBarang(no_resi, tanggalTiba.format(format), Penerima, berat, namaP, kat);
                            break;
                        }
                        case 2: {
                            System.out.println(Warna.judul + "----- UPDATE BARANG -----");
                            System.out.print("Masukan nomor resi : ");
                            String no_resi = input.nextLine();
                            System.out.print("Masukkan Berat : ");
                            int berat = input.nextInt(); input.nextLine();
                            System.out.print("Masukkan Penerima : ");
                            String penerima = input.nextLine();
                            cBarang.listKategori();
                            System.out.print("Pilih Kategori : ");
                            int pilih_kategori = input.nextInt();
                            String kat = cBarang.pilihKategori(pilih_kategori);
                            boolean cekUpd = cBarang.updateBarang(no_resi, penerima, berat, kat);
                            if(cekUpd){
                                System.out.println("Data berhasil diUpdate!");
                            }else{
                                System.out.println("Data gagal diUpdate!");
                            }
                            break;
                        }
                        case 3:{
                            System.out.println(Warna.judul+"----- DELETE BARANG -----");
                            System.out.print("Masukan nomor resi : ");
                            String no_resi = input.nextLine();
                            boolean cekDelete = cBarang.deleteBarang(no_resi);
                            if(cekDelete){
                                System.out.println("Data berhasil diHapus!");
                            }else{
                                System.out.println("Data gagal diHapus!");
                            }
                            break;
                        }
                        case 4:
                            break;
                        default:

                            break;
                    }
                    break;
                }
                case 2: {
                    System.out.println(Warna.judul + "----- LIST BARANG -----");
                    System.out.println(Warna.menu+"1. Barang Masuk\n2. Barang di Gudang\n3. Barang Keluar");
                    System.out.print(Warna.sub_judul+"Pilihan : ");
                    int pilih_list = input.nextInt();
                    switch (pilih_list){
                        case 1:
                            cBarang.listBarangMasuk();
                            break;
                        case 2:
                            cBarang.listBarang();
                            break;
                        case 3:
                            cBarang.listBarangKeluar();
                            break;
                        default:

                            break;
                    }
                    break;
                }
                case 3: {
                    System.out.println(Warna.judul + "----- CARI BARANG -----" + Warna.reset_warna);
                    System.out.println(Warna.komen + "Cari berdasarkan Nomor Resi, Kategori dan Petugas" + "\u001B[0m");
                    System.out.print(Warna.sub_judul + "Masukkan Pencarian : ");
                    String cari = input.nextLine();
                    Barang dataBarang = cBarang.searchBarang(cari);
                    if(dataBarang != null){
                        System.out.println("Nomor Resi      : "+dataBarang.getNo_resi());
                        System.out.println("Kategori        : " + dataBarang.getKategori().getNama());
                        System.out.println("Berat           : "+dataBarang.getBerat());
                        System.out.println("Penerima        : "+dataBarang.getPenerima());
                        System.out.println("Tanggal         : "+ dataBarang.getTanggaltiba());
                        System.out.println("Petugas         : "+dataBarang.getNamaPetugas());
                    }
                    break;
                }
                case 4: {
                    //checkout barang
                    System.out.println(Warna.judul + "----- CHECKOUT BARANG -----" + Warna.reset_warna);
                    System.out.print(Warna.sub_judul+"Masukkan Nomor Resi : ");
                    String noResi = input.nextLine();
                    LocalDateTime tanggalKeluar = LocalDateTime.now();
                    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                    cBarang.checkoutBarang(noResi, tanggalKeluar.format(format));

                    break;
                }
                case 5: {
                    //cetak laporan
                    System.out.println(Warna.judul+"----- CETAK LAPORAN -----"+Warna.reset_warna);
                    System.out.println(Warna.menu+"1. Laporan Barang Masuk\n2. Laporan Barang Keluar\n3. Laporan Barang di Gudang\n4. Kembali");
                    System.out.print(Warna.sub_judul+"Pilihan Cetak : ");
                    int pilih_cetak = input.nextInt();

                    switch (pilih_cetak){
                        case 1:
                            cBarang.cetakLaporan("masuk");
                            break;
                        case 2:

                            break;
                        case 3:

                            break;
                        case 4:

                            break;
                        default:

                            break;
                    }
                    break;
                }
            }
        }
    }
}
