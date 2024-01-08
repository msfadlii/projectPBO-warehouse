package Model;

import Model.JSON.ModelJSON;
import Node.NodePetugas;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Scanner;

public class ModelPetugas {
    static Scanner input = new Scanner(System.in);
    ArrayList<NodePetugas> listUser;
    ModelJSON<NodePetugas> modelUserJSON;


    public ModelPetugas(){
        listUser = new ArrayList<NodePetugas>();
        modelUserJSON = new ModelJSON<>("src/Database/user.json");
        listUser = modelUserJSON.readFromFile(new TypeToken<ArrayList<NodePetugas>>() {}.getType());
        loadData();
    }
    public ArrayList<NodePetugas> login(){
        ArrayList<NodePetugas> nodePetugas = new ArrayList<>();
        if(modelUserJSON.checkFile()){
            if(listUser != null){
                return listUser;
            }
        }
        return nodePetugas;
    }
    public void addUser(String username, String pass, String nama) {
        listUser.add(new NodePetugas(username, pass, nama));
        commitData();
    }

    public void deleteUser(String uname){
        listUser.remove(searchUser(uname));
    }

    public ArrayList<NodePetugas> listUser(){
        return listUser;
    }

    public NodePetugas getUser(int id) {
        return listUser.get(id);
    }

    public int searchUser(String nama) {
        for (NodePetugas user : listUser) {
            if (user.getUsername().equals(nama)) {
                return listUser.indexOf(user);
            }
        }
        return -1;
    }

    private void loadData() {
        listUser = modelUserJSON.readFromFile(new TypeToken<ArrayList<NodePetugas>>() {}.getType());
    }

    public void commitData() {
        modelUserJSON.writeToFile(listUser);
    }

}