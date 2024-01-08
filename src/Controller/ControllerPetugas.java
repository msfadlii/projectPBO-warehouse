package Controller;

import Model.ModelBarang;
import Model.ModelPetugas;
import Node.NodePetugas;

import java.util.ArrayList;
import java.util.Scanner;

public class ControllerPetugas {
    static ModelPetugas mPetugas;
    static Scanner input = new Scanner(System.in);

    public ControllerPetugas(ModelPetugas petugas){
        this.mPetugas = petugas;
    }

    public boolean login(String uname, String pass){
        ArrayList<NodePetugas> user = mPetugas.login();
        if(user != null){
            for (NodePetugas petugas: user) {
                if(uname.equals(petugas.getUsername())){
                    if(pass.equals(petugas.getPassword())){
                        return true;
                    }else{
                        return false;
                    }
                }
            }
        }else{
            System.out.println("Data user kosong!");
        }
        return false;
    }

    public void addUser(String uname, String pass, String nama){
        while(mPetugas.searchUser(uname) != -1){
            System.out.println("Username sudah ada!");
            System.out.println("Masukkan username Petugas baru : ");
            uname = input.nextLine();
        }
        mPetugas.addUser(uname, pass, nama);
        System.out.println("User baru berhasil ditambahkan!");
    }
    public void deleteUser(String uname){
        mPetugas.deleteUser(uname);
        System.out.println("User telah dihapus!");
    }

    public void listUser(){
        ArrayList<NodePetugas> arrUser = mPetugas.listUser();
        for (NodePetugas nodePetugas :arrUser) {
            System.out.println("Nama     : "+ nodePetugas.getNama());
            System.out.println("Username : "+ nodePetugas.getUsername());
            System.out.println("------------------------------");
        }
    }
}