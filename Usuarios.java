import java.util.Scanner;

import org.w3c.dom.NamedNodeMap;
public class Usuarios {
    Scanner myObj = new Scanner(System.in);
    private String password;
    private int rol;
    private String name;
    private boolean estado;
    public Usuarios(){}
    public Usuarios(String name, String password, int rol){
        this.name = name;
        this.password= password;
        this.rol = rol;
    }
    public void login(){
        System.out.println("Ingrese el nombre de usuario: ");
        name = myObj.nextLine();
        System.out.println("Ingrese la contraseña: ");
        password = myObj.nextLine();
    }
    public String getUsername(){
        return name;
    }
    public String getPassword(){
        return password;
    }
    public int getRole(){
        return rol;
    }
}
