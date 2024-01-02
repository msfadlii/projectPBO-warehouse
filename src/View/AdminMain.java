package View;
import Controller.ControllerPetugas;
import Node.NodePetugas;

import java.util.*;

public class AdminMain {
    static Scanner input = new Scanner(System.in);
    static ArrayList<NodePetugas> nodePetugasArrList = new ArrayList<>();
//    static ModelUser cUser = new ModelUser();
    static ControllerPetugas cUser = new ControllerPetugas();
    void menuAdmin(){
        System.out.println("----- MENU ADMIN -----");
        System.out.println("1. Tambah Petugas\n2. Hapus Petugas\n3. List Petugas");
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

                cUser.addUser(unamePetugas, passPetugas, namaPetugas);
                break;
            }
            case 2: {
                System.out.println("----- Hapus Petugas -----");
                System.out.print("Masukkan Username Petugas : ");
                String unamePetugas = input.nextLine();
                cUser.deleteUser(unamePetugas);
                break;
            }
            case 3: {
                System.out.println("----- LIST PETUGAS -----");
                cUser.listUser();
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
