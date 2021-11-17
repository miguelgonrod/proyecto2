import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
public class Main{
    public static void main(String[] args) throws ClassNotFoundException,IOException{
        Scanner myObj = new Scanner(System.in);
        ArrayList<Usuarios> usu = new ArrayList<Usuarios>();
        File file = new File("Estudiantes.usu");
        ObjectInputStream ois = null;
        String name="0";
        String password;
        String defaultA[]={"admin","admin"};
        int rol=0;
        while(name!="1"){
            System.out.println("Ingrese el nombre de usuario, ingrese 1 para salir: ");
            name = myObj.nextLine();
            System.out.println("Ingrese la contraseña: ");
            password = myObj.nextLine();
            try {
                FileInputStream fis = new FileInputStream(file);
                ois = new ObjectInputStream(fis);
                while (true) {
                    Usuarios usua = (Usuarios) ois.readObject();
                    if (name.equals(usua.getName())) {
                        if(password.equals(usua.getPassword())){
                            rol=usua.getRol();
                            break;
                        }
                    }
                }
            } catch (Exception e) {
    
            } finally {
                if (ois != null) {
                    ois.close();
                }
            }
            if(name=="1"){
                break;
            }
            if(name.compareTo(defaultA[0])==0 && password.compareTo(defaultA[1])==0){
                Administrador admin = new Administrador(password, name, rol);
                admin.menu();
            }
            else{
                if(rol==0){
                    System.out.println("Usuario o contraseña incorrectos\n");
                }
                else if(rol==1){
                    Administrador admin = new Administrador(password, name, rol);
                    admin.menu();
                }
                else if(rol==2){
                    Profesor prof = new Profesor(password, name, rol);
                    prof.menu();
                }
                else if(rol==3){
                    Estudiante estudia = new Estudiante(password, name, rol);
                    estudia.menu();
                }
            }
        }
    }
}