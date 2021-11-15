import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class LecturaEscritura {
    String nombre, password;
    int rol;
    public void setEscritura() throws IOException{
        File f=new File("usuarios.obj");
        FileOutputStream fos=new FileOutputStream(f);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(new Usuarios("admin","admin",1));
        oos.close();
    }
    public int getLectura(String nombre, String password) throws ClassNotFoundException, IOException{
        ObjectInputStream ois = null;
        try{
            File f = new File("usuarios.obj");
            FileInputStream fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            while(true){
                Usuarios u = (Usuarios) ois.readObject();
                if(u.getUsername()==nombre){
                    if(u.getPassword()==password){
                        return u.getRole();
                    }
                }
            }
        }catch(IOException io){
            System.out.println("\n");
        }finally{
            ois.close();
        }
        return 0;
    }
}
