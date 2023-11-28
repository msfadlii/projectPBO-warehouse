package View.admin;
import Controller.ControllerUser;
import Model.User;
import Model.UserJSON;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.*;
import java.util.*;

public class AdminMain {
    static Scanner input = new Scanner(System.in);
    static ArrayList<User> userArrList = new ArrayList<>();
    static ControllerUser cUser = new ControllerUser();
    void menuAdmin(){
        System.out.println("----- MENU ADMIN -----");
        System.out.println("1. Tambah Petugas\n2. Hapus Petugas\n3. Login\n4. List Petugas");
        System.out.print("Masukkan pilihan anda : ");
        int pilihan = input.nextInt();
        input.nextLine();
        switch (pilihan){
            case 1: {
                System.out.println("----- Tambah Petugas -----");
                System.out.print("Masukkan Username Petugas : ");
                String unamePetugas = input.nextLine();
                System.out.print("Masukkan Password Petugas : ");
                String passPetugas = input.nextLine();
                System.out.print("Masukkan Nama Petugas : ");
                String namaPetugas = input.nextLine();

                cUser.addPetugas(unamePetugas, passPetugas, namaPetugas);
                break;
            }
            case 2: {
                System.out.println("----- Hapus Petugas -----");
                System.out.print("Masukkan Username Petugas : ");
                String unamePetugas = input.nextLine();
                cUser.deletePetugas(unamePetugas);
                break;
            }
            case 4: {
                System.out.println("----- LIST PETUGAS -----");
                cUser.showPetugas();
                break;
            }
            default:

                break;
        }
    }
    public static void main(String[] args) {
        AdminMain objAdmin = new AdminMain();

        while(true){
            objAdmin.menuAdmin();
        }
    }
}
