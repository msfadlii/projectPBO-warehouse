package View.petugas;

import Controller.ControllerBarang;
import View.auth.Login;

import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class PetugasMain {

    public static void main(String[] args) {
        ControllerBarang cBarang = new ControllerBarang();
        Scanner input = new Scanner(System.in);
        String namaP = Login.namaP;
        while (true) {
            System.out.println("\n----- PETUGAS -----\nHalo " + namaP + "\n1. Tambah Barang\n2. List Barang\n3. Carii Barang\n5. Logout");
            System.out.print("Masukan Pilihan Anda : ");
            int pilih = input.nextInt();
            switch (pilih) {
                case 1:
                    System.out.println("----- TAMBAH BARANG -----");
                    System.out.print("Masukan nomor resi : ");
                    String no_resi = input.next();
                    LocalDateTime tanggalTiba = LocalDateTime.now();
                    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                    System.out.print("Masukkan Penerima barang : ");
                    String Penerima = input.next();
                    System.out.print("Masukkan berat barang : ");
                    int berat = input.nextInt();

                    cBarang.tambahBarang(no_resi, tanggalTiba.format(format), Penerima, berat, namaP);

                    continue;
                case 2:
                    System.out.println("----- LIST BARANG -----");
                    cBarang.listBarang();
            }
        }
    }
}
