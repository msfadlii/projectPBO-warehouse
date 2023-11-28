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

                if (cUser.cekFile()){
                    ArrayList<User> userArrayList = cUser.readFromFile();
                    if(userArrayList != null){
                        userArrayList.add(new User(unamePetugas, passPetugas));
                        cUser.writeFileJSON(userArrayList);
                    }else{
                        userArrayList = new ArrayList<User>();
                        userArrayList.add(new User(unamePetugas, passPetugas));
                        cUser.writeFileJSON(userArrayList);
                    }
                } else {
                    System.out.println("Database User tidak ada !");
                }
                break;
            }
            case 2: {
                System.out.println("----- Hapus Petugas -----");
                System.out.print("Masukkan Username Petugas : ");
                String unamePetugas = input.nextLine();
                if (cUser.cekFile()){
                    ArrayList<User> userArrayList = cUser.readFromFile();
                    if(userArrayList != null){
                        for (User user:userArrayList){
                            if(unamePetugas.equals(user.getUsername())){
                                userArrayList.remove(user);
                                System.out.println("Petugas berhasil dihapus!");
                                break;
                            }else{
                                System.out.println("Petugas tidak ditemukan!");
                            }
                        }
                        cUser.writeFileJSON(userArrayList);
                    } else {
                        System.out.println("Tidak ada Data !");
                    }
                } else {
                    System.out.println("Database tidak ada");
                }
                break;
            }
            case 3:{
                System.out.println("----- MENU LOGIN -----");
                System.out.print("Masukkan Username : ");
                String username = input.nextLine();
                System.out.print("Masukkan Password : ");
                String password = input.nextLine();

                break;
            }
            case 4: {
                System.out.println("----- LIST PETUGAS -----");
                ArrayList<User> listUser = cUser.readFromFile();
                for (User user:listUser){
                    System.out.println("-----------------");
                    System.out.println("Username : "+user.getUsername());
                    System.out.println("Password : "+user.getPassword());
                }
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
