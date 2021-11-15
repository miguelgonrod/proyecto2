import java.util.Scanner;
import java.io.*;
public class Main{
    public static void main(String[] args){
        Scanner myObj = new Scanner(System.in);
        Administrador admin = new Administrador();
        LecturaEscritura us = new LecturaEscritura();
        String file = "Usuarios.txt";
        String name="0";
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
        while(name!="1"){
            System.out.println("Ingrese el nombre de usuario, ingrese 1 para salir: ");
            name = myObj.nextLine();
            System.out.println("Ingrese la contraseña: ");
            password = myObj.nextLine();
            if(name=="1"){
                break;
            }
            if(name.compareTo(defaultA[0])==0 && password.compareTo(defaultA[1])==0){
                admin.menu();
            }
            else{
                rol=us.getLectura(name, password);
                if(rol==0){
                    System.out.println("Usuario o contraseña incorrectos\n");
                }
                else if(rol==1){
                    admin.menu();
                }
            }
        }
    }
}