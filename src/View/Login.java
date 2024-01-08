package View;
import Controller.ControllerPetugas;
import Model.ModelPetugas;

import java.util.Scanner;
public class Login {
    static ModelPetugas modelPetugas = new ModelPetugas();
    static ControllerPetugas cPetugas = new ControllerPetugas(modelPetugas);
    public static String username = "";
    static String pass = "";
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("----- LOGIN -----");
        while(true){
            System.out.print("Masukkan Username : ");
            username = input.nextLine();
            System.out.print("Masukkan Password : ");
            pass = input.nextLine();

            if (username.equals("admin") && pass.equals("admin")) {
                System.out.println("Login Admin Berhasil !");
                AdminMain.main(null);
            }
            boolean cekLogin = cPetugas.login(username, pass);
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
