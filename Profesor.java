import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.io.Serializable;

public class Profesor extends Usuarios implements Serializable{
    private ArrayList<Curso> cursos = new ArrayList<Curso>();
    private Curso cur=null;
    private Asignacion asignacion=null;
    private Scanner scan=new Scanner(System.in);
    private int asigpos;
    public Profesor(String password, String name, int rol) {
        super(password, name, rol);
    }

    public void menu() throws ClassNotFoundException,IOException{
        int opcion=0;
        while(opcion!=4){
            System.out.println("Que opcion deseas realizar? \n1)Crear asignacion\n2)Comunicar\n3)Comentar\n4)salir");
            if(opcion==1){
                addAsignacion();
            }
            else if(opcion==2){
                Comunicar();
            }
            else if(opcion==3){
                comentar();
            }
        }
    }
    public static void actualizarProfesor(Profesor profe) throws ClassNotFoundException,IOException{
        ArrayList <Profesor> profes=new ArrayList<Profesor>();
        File file=new File("Profesores.usu");
        ObjectInputStream ois= null;
        try{
            FileInputStream fis= new FileInputStream(file);
            ois= new ObjectInputStream(fis);
            while(true){
                Profesor pro=(Profesor) ois.readObject();
                if(profe.getName().equals(pro.getName())){
                    profes.add(profe);
                }else{
                    profes.add(pro);
                } 
            }
        }catch(Exception e){
            
        }finally{
            if(ois!=null){
                ois.close();
            }
        }
        FileOutputStream fos=new FileOutputStream(file);
        ObjectOutputStream oos=new ObjectOutputStream(fos);
        for(int i=0; i<profes.size();i++){
            oos.writeObject(profes.get(i));
        }
        oos.close();
        return;
    }
    private void actualizarCursos() throws ClassNotFoundException,IOException{
        File file=new File("Cursos.cur");
        ObjectInputStream ois=null;
        try{
            FileInputStream fis=new FileInputStream(file);
            ois=new ObjectInputStream(fis);
            while(true){
                Curso cur=(Curso) ois.readObject();
                for(int i=0; i<cursos.size();i++){
                    if(cursos.get(i).getName().equals(cur.getName())){
                        cursos.set(i,cur);
                    }
                }
            }
        }catch(Exception e){

        }finally{
            if(ois!=null){
                ois.close();
            }
        }
        return;
    }
    public void addAsignacion() throws ClassNotFoundException,IOException{
        String name;
        String decrip;
        System.out.print("Nombre de la Asignacion: ");
        name=scan.nextLine();
        System.out.print("Descripcion de la Asignacion: ");
        decrip=scan.nextLine();
        Asignacion asig=new Asignacion(name,decrip,cur.getAsigs());
        cur.addAsignacion(asig);
        Curso.actualizarCurso(cur);
    }
    public void setCurso() throws ClassNotFoundException,IOException{
        String name;
        Curso cur=null;
        boolean is=true;
        actualizarCursos();
        do{
            System.out.print("Indique el nombre del curso: ");
            name=scan.nextLine();
            for(int i=0;i<cursos.size();i++){
                if(cursos.get(i).getName().equals(name)){
                    is=false;
                    cur=cursos.get(i);
                    break;
                }
            }
        }while(is);
        this.cur=cur;
    }
     public void setAsignacion() throws ClassNotFoundException,IOException{
        String name;
        Asignacion as=null;
        boolean is=true;
        if(cur==null){
            System.out.println("Primero establesca un curso...");
            return;
        }
        do{
            System.out.print("Indique el nombre de la asignacion: ");
            name=scan.nextLine();
            for(int i=0;i<cur.getAsignaciones().size();i++){
                if(cur.getAsignaciones().get(i).getName().equals(name)){
                    is=false;
                    as=cur.getAsignaciones().get(i);
                    asigpos=i;
                    break;
                }
            }
        }while(is);
        this.asignacion=as;
    }
    public void Comunicar() throws ClassNotFoundException,IOException{
        String cuerpo;
        System.out.print("Escriba el mensaje: ");
        descrip=scan.nextLine();
        Comunicado com=new Asignacion(getName(),descrip);
        cur.addComunicado(com);
        Curso.actualizarCurso(cur);
    }
    public ArrayList<Asignacion> getAsignaciones(){
        if(cur==null){
            System.out.println("Primero establezca un curso...");
            return null;
        }
        return cur.getAsignaciones();
    }
    public ArrayList<Entrega> getEntregas() throws ClassNotFoundException,IOException{
        if(asignacion==null){
            System.out.println("Primero establezca una asignacion...");
            return null;
        }
        return cur.getAsignaciones();
    }
    public ArrayList<Curso> getCursos(){
        actualizarCursos();
        return cursos;
    }
    public void comentar() throws ClassNotFoundException,IOException{
        if(asignacion==null){
            System.out.println("Primero establezca una asignacion...");
            return;
        }
        String name;
        boolean is=true;
        int j;
        do{
            System.out.print("Indique el nombre del estudiante: ");
            name=scan.nextLine();
            for(int i=0; i<asig.getEntregas().size();i++){
                if(asig.getEntregas().get(i).getName().equals(name)){
                    is=false;
                    entre=asig.getEntregas().get(i);
                    j=i;
                    break;
                }
            }
        }while(is);
        System.out.print("Indique el mensaje: ");
        entre.anadirComentario(new Comunicado(name,scan.nextLine()));
        asig.ajustarEntrega(entre, j);
        cur.ajustarAsignacion(asig, asigpos);
        Curso.actualizarCurso(cur);
    }
}