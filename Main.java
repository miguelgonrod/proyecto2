import java.util.Scanner;
import java.io.*;
public class Main{
    public static void main(String[] args){
        Scanner myObj = new Scanner(System.in);
        Administrador admin = new Administrador();
        String file = "Usuarios.txt";
        String name;
        String password;
        String defaultA[]={"admin","admin"};
        int rol;
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            while((line = br.readLine())!=null){
                System.out.println(line);
            }
        }
        catch(IOException e){
            System.out.println("An error ocurred");
            e.printStackTrace();
        }
        System.out.println("Ingrese el nombre de usuario: ");
        name = myObj.nextLine();
        System.out.println("Ingrese la contraseña: ");
        password = myObj.nextLine();
        if(name.compareTo(defaultA[0])==0 && password.compareTo(defaultA[1])==0){
            admin.menu();
        }
        else{
        }
    }
}