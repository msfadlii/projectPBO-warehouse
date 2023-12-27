package View;
import Controller.ControllerUser;
import Model.ModelUser;

import java.util.Scanner;
public class Login {
    static ModelUser modelUser = new ModelUser();
    static ControllerUser cUser = new ControllerUser();
    public static String username;
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("----- LOGIN -----");
        while(true){
            System.out.print("Masukkan Username : ");
            username = input.nextLine();
            System.out.print("Masukkan Password : ");
            String pass = input.nextLine();

            if (username.equals("admin") && pass.equals("admin")) {
                System.out.println("Login Admin Berhasil !");
                AdminMain.main(null);
            }
            boolean cekLogin = cUser.login(username, pass);
            if(cekLogin){
                System.out.println("Login Berhasil !");
                PetugasMain.main(null);
                break;
            }else{
                System.out.println("Username atau Password salah !");
                continue;
            }
        }
    }
}
