import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Entrega{
    private String name;
    private String cuerpo;
    private static String path=" ";
    private String adjunto;
    private ArrayList<Comunicado> comentarios=new ArrayList<Comunicado>();
    public Entrega(String name, String cuerpo, String adjunto, Comunicado comentario){
        this.name=name;
        this.cuerpo=cuerpo;
        this.adjunto=adjunto;
        comentarios.add(comentario);
    }
    public String GetName(){
        return name;
    }
    public String GetCuerpo(){
        return cuerpo;
    }
    public String GetAdjunto(){
        return path+adjunto;
    }
    public void anadirComentario(Comunicado comentario){
        comentarios.add(comentario);
    }
}