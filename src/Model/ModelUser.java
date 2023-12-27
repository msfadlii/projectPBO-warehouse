package Model;

import Model.JSON.ModelJSON;
import Node.NodeUser;
import View.PetugasMain;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Scanner;

public class ModelUser{
    static Scanner input = new Scanner(System.in);
    ArrayList<NodeUser> listUser;
    ModelJSON<NodeUser> modelUserJSON;

    public ModelUser(){
        listUser = new ArrayList<NodeUser>();
        modelUserJSON = new ModelJSON<>("src/Database/user.json");
        loadData();
    }
    public NodeUser login(){
        NodeUser nodeUser = null;
        if(modelUserJSON.checkFile()){
            if(listUser != null){
                for(int i = 0; i< listUser.size(); i++){
                    nodeUser = listUser.get(i);
                    return nodeUser;
                }
            }
        }
        return nodeUser;
    }
    public void addUser(String username, String pass, String nama) {
        listUser.add(new NodeUser(username, pass, nama));
        commitData();
    }

    public void deleteUser(String uname){
        listUser.remove(searchUser(uname));
    }

    public ArrayList<NodeUser> listUser(){
        return listUser;
    }

    public NodeUser getUser(int id) {
        return listUser.get(id);
    }

    public int searchUser(String nama) {
        for (NodeUser user : listUser) {
            if (user.getUsername().equals(nama)) {
                return listUser.indexOf(user);
            }
        }
        return -1;
    }

    private void loadData() {
        listUser = modelUserJSON.readFromFile(new TypeToken<ArrayList<NodeUser>>() {}.getType());
    }

    public void commitData() {
        modelUserJSON.writeToFile(listUser);
    }

}