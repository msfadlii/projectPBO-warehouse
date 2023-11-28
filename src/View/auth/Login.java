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
    //public static String uname;
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
                        if(uname.equals(userList.get(i).getUsername())){
                            if(pass.equals(userList.get(i).getPassword())){
                                System.out.println("Login Berhasil !");
                                PetugasMain.main(args);
                                break input;
                            }else{
                                System.out.println("Username atau Password salah !");
                                break;
                            }
                        }else{
                            System.out.println("Username salah !");
                            break;
                        }
                    }
                }
            }
        }

    }
}
