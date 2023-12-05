package Controller;

import Controller.JSON.CUserJSON;
import Model.User;
import java.util.ArrayList;
import java.util.Scanner;

public class ControllerUser extends CUserJSON {
        static Scanner input = new Scanner(System.in);
    public void addPetugas(String unamePetugas, String passPetugas, String namaPetugas){
        if (cekFile()){
            ArrayList<User> userArrayList = readFromFile();
            if(userArrayList != null){
                while(cekUsername(unamePetugas)){
                    System.out.println("Username sudah ada!");
                    System.out.print("Masukkan Username Petugas yang baru : ");
                    unamePetugas = input.nextLine();
                }
                userArrayList.add(new User(unamePetugas, passPetugas, namaPetugas));
                System.out.println("Data berhasil ditambahkan!");
                writeFileJSON(userArrayList);
            }else{
                userArrayList = new ArrayList<User>();
                userArrayList.add(new User(unamePetugas, passPetugas,namaPetugas));
                writeFileJSON(userArrayList);
            }
        } else {
            System.out.println("Database User tidak ada !");
        }
    }

    public void deletePetugas(String unamePetugas){
        int terhapus = 0;
        if (cekFile()){
            ArrayList<User> userArrayList = readFromFile();
            if(userArrayList != null){
                for (User user:userArrayList){
                    if(unamePetugas.equals(user.getUsername())){
                        userArrayList.remove(user);
                        terhapus = 1;
                        break;
                    }
                }
                if(terhapus == 1){
                    System.out.println("Petugas Berhasil di hapus !");
                }else{
                    System.out.println("Petugas tidak ditemukan!");
                }
                writeFileJSON(userArrayList);
            } else {
                System.out.println("Tidak ada Data !");
            }
        } else {
            System.out.println("Database tidak ada");
        }
    }

    public void showPetugas(){
        ArrayList<User> listUser = readFromFile();
        for (User user:listUser){
            System.out.println("-----------------");
            System.out.println("Username : "+user.getUsername());
            System.out.println("Nama : "+user.getNama());
        }
    }

    public boolean cekUsername(String username){
        ArrayList<User> userArrayList = readFromFile();
        for(User user:userArrayList){
            if(username.equals(user.getUsername())){
                return true;
            }
        }
        return false;
    }
}