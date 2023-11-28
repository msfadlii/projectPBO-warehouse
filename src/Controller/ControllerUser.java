package Controller;

import Model.User;
import Model.UserJSON;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ControllerUser {
    static UserJSON userJSON = new UserJSON();
    String fname = "src/Database/user.json";
    static Scanner input = new Scanner(System.in);
    public boolean cekFile(){
        boolean cek = false;
        try {
            File file = new File(fname);
            if(file.exists()){
                cek = true;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return cek;
    }

     public JSONArray convertArrayListtoJSONArray(ArrayList<User> listUser){
        if(listUser == null){
            return null;
        } else {
            JSONArray arrUser = new JSONArray();
            for (User user : listUser) {
                JSONObject objUser = new JSONObject();
                objUser.put(userJSON.getUsername(), user.getUsername());
                objUser.put(userJSON.getPassword(), user.getPassword());
                objUser.put(userJSON.getNama(), user.getNama());
                arrUser.add(objUser);
            }
            return arrUser;
        }
    }

    public void writeFileJSON(ArrayList<User> listUser){
        JSONArray arrUser = convertArrayListtoJSONArray(listUser);
        try {
            FileWriter fileWriter = new FileWriter(fname);
            fileWriter.write(arrUser.toJSONString());
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public ArrayList<User> convertJSONArraytoArrayList(JSONArray arrUser){
        if(arrUser == null){
            return null;
        } else {
            ArrayList<User> listUser = new ArrayList<>();
            for(Object objUser : arrUser){
                JSONObject jsonObject = (JSONObject) objUser;
                String username = jsonObject.get(userJSON.getUsername()).toString();
                String password = jsonObject.get(userJSON.getPassword()).toString();
                String nama = jsonObject.get(userJSON.getNama()).toString();
                listUser.add(new User(username, password, nama));
            }
            return listUser;
        }
    }

    public ArrayList<User> readFromFile(){
        if(cekFile()){
            ArrayList listUser = null;
            JSONParser parser = new JSONParser();
            try {
                Reader reader = new FileReader(fname);
                JSONArray jsonArray = (JSONArray) parser.parse(reader);
                listUser = convertJSONArraytoArrayList(jsonArray);
            } catch (FileNotFoundException e){
                throw new RuntimeException(e);
            } catch (IOException e){
                throw new RuntimeException(e);
            } catch (ParseException e){
                throw new RuntimeException(e);
            }
            return listUser;
        }else{
            return null;
        }
    }

    public void addPetugas(String unamePetugas, String passPetugas, String namaPetugas){
        int cek = 0;
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