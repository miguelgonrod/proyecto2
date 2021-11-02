import java.util.Scanner;
public class Usuarios {
    Scanner myObj = new Scanner(System.in);
    private String password;
    private int rol;
    private String name;
    private boolean estado;
    public void login(){
        System.out.println("Ingrese el nombre de usuario: ");
        name = myObj.nextLine();
        System.out.println("Ingrese la contraseña: ");
        password = myObj.nextLine();
    }
}
