package View.auth;
import Controller.ControllerUser;
import Model.User;
import View.petugas.PetugasMain;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

import java.util.ArrayList;
import java.util.Scanner;
public class Login {
    static ControllerUser cUser = new ControllerUser();
    public static String namaP;
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("----- LOGIN -----");
        input:while(true){
            System.out.print("Masukkan Username : ");
            String uname = input.nextLine();
            System.out.print("Masukkan Password : ");
            String pass = input.nextLine();

            if(cUser.cekFile()){
                ArrayList<User> userList = cUser.readFromFile();
                if(userList != null){
                    for(int i=0; i<userList.size(); i++){
                        User user = userList.get(i);
                        if(uname.equals(user.getUsername())){
                            if(pass.equals(user.getPassword())){
                                System.out.println("Login Berhasil !");
                                namaP = user.getNama();
                                PetugasMain.main(args);
                                break input;
                            }else{
                                System.out.println("Username atau Password salah !");
                                break;
                            }
                        }
                    }
                }
            }
        }

    }
}
