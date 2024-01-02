package Controller;

import Model.ModelPetugas;
import Node.NodePetugas;

import java.util.ArrayList;
import java.util.Scanner;

public class ControllerPetugas {
    static ModelPetugas mUser = new ModelPetugas();
    static Scanner input = new Scanner(System.in);

    public boolean login(String uname, String pass){
        NodePetugas user = mUser.login();
        if(user != null){
            if(uname.equals(user.getUsername())){
                if(pass.equals(user.getPassword())){
                    return true;
                }else{
                    return false;
                }
            }
        }else{
            System.out.println("Data user kosong!");
        }
        return false;
    }

    public void addUser(String uname, String pass, String nama){
        while(mUser.searchUser(uname) != -1){
            System.out.println("Username sudah ada!");
            System.out.println("Masukkan username Petugas baru : ");
            uname = input.nextLine();
        }
        mUser.addUser(uname, pass, nama);
        System.out.println("User baru berhasil ditambahkan!");
    }
    public void deleteUser(String uname){
        mUser.deleteUser(uname);
        System.out.println("User telah dihapus!");
    }

    public void listUser(){
        ArrayList<NodePetugas> arrUser = mUser.listUser();
        for (NodePetugas nodePetugas :arrUser) {
            System.out.println("Nama     : "+ nodePetugas.getNama());
            System.out.println("Username : "+ nodePetugas.getUsername());
            System.out.println("------------------------------");
        }
    }
}