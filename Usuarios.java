import java.util.Scanner;
public class Usuarios {
    Scanner myObj = new Scanner(System.in);
    private String password;
    private int rol;
    private String name;
    private boolean estado;
    public Usuarios(String password, String name, int rol){
        this.password=password;
        this.name=name;
        this.rol=rol;
        estado=true;
    }
    public void setName(String name){
        this.name=name;
    }
    public void setPassword(String password){
        this.password=password;
    }
    public void setRol(int rol){
        this.rol=rol;
    }
    public void setEstado(boolean estado){
        this.estado=estado;
    }
    public String getName(){
        return name;
    }
    public String getPassword(){
        return name;
    }
    public int getRol(){
        return rol;
    }
    public boolean getEstado(){
        return estado;
    }
}
