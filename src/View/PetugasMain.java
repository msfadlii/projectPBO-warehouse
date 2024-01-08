package View;

import Controller.ControllerBarang;
import Model.ModelBarang;
import View.Barang.ViewBarang;
import View.res.Warna;

import java.util.Scanner;

public class PetugasMain {

    public static void main(String[] args) {
        ModelBarang modelBarang = new ModelBarang();
        ControllerBarang cBarang = new ControllerBarang(modelBarang);
        ViewBarang viewBarang = new ViewBarang(cBarang);
        Scanner input = new Scanner(System.in);
        String namaP = Login.username;
        while (true) {
            System.out.println(Warna.judul+"\n----- PETUGAS -----\n"+Warna.sub_judul +"Halo " + namaP + Warna.menu+"\n1. Data Barang\n2. List Barang\n3. Cari Barang\n4. Checkout Barang\n5. Cetak Laporan\n6. Logout");
            System.out.print(Warna.sub_judul+"Masukan Pilihan Anda : ");
            int pilih = input.nextInt();input.nextLine();
            switch (pilih) {
                case 1: {
                    System.out.println(Warna.menu+"1. Tambah Barang\n2. Update Barang\n3. Hapus Barang\n4. Kembali");
                    System.out.print(Warna.sub_judul+"Masukkan Pilihan : ");
                    int pilih1 = input.nextInt();input.nextLine();
                    switch (pilih1){
                        case 1: {
                            viewBarang.insertBarang();
                            break;
                        }
                        case 2: {
                            viewBarang.updateBarang();
                            break;
                        }
                        case 3:{
                            viewBarang.deleteBarang();
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
                    int pilih_list = input.nextInt(); input.nextLine();
                    System.out.print(Warna.sub_judul+"Ingin mem-filter barang ? : ");
                    String quest = input.nextLine();
                    String filter="";
                    String formatFilter="";
                    if(quest.toLowerCase().equals("ya")){
                        System.out.println(Warna.komen + "Filter berdasarkan Tanggal atau " +
                                "Kota\nContoh : 10-12-2023 atau Surabaya" + "\u001B[0m");
                        System.out.print("Masukkan filter : ");
                        filter = input.nextLine();
                        formatFilter = filter.substring(0, 1).toUpperCase()+filter.substring(1).toLowerCase();
                    }
                    switch (pilih_list){
                        case 1:
                            viewBarang.listMasuk(formatFilter);
                            break;
                        case 2:
                            viewBarang.list(formatFilter);
                            break;
                        case 3:
                            viewBarang.listKeluar(formatFilter);
                            break;
                        default:
                            break;
                    }
                    break;
                }
                case 3: {
                    viewBarang.cariBarang();
                    break;
                }
                case 4: {
                    viewBarang.checkoutBarang();
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
                            cBarang.buatFileLaporan("masuk");
                            break;
                        case 2:
                            cBarang.buatFileLaporan("keluar");
                            break;
                        case 3:
                            cBarang.buatFileLaporan("");
                            break;
                        case 4:
                            break;
                        default:
                            break;
                    }
                    break;
                }
                case 6: {
                    Login.main(null);
                    break;
                }
            }
        }
    }
}