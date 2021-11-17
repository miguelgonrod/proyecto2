import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.NotSerializableException;
import java.io.Serializable;

public class Curso throws ClassNotFoundException,IOException implements Serializable{
    private String name;
    private int id;
    private static int idGeneral;
    private Profesor profesor;
    private ArrayList<Asignacion> Asignaciones = new ArrayList<Asignacion>();
    private ArrayList<Comunicado> comunicados = new ArrayList<Comunicado>();
    private ArrayList<Estudiante> estudiantes = new ArrayList<Estudiante>();

    public Curso(String name, Profesor profesor) {
        this.name = name;
        this.profesor = profesor;
        File file=new File("Cursos.cur");
        ObjectInputStream ois=null;
        ArrayList<Curso> cursos=new ArrayList<Curso>();
        try{
            FileInputStream fis=new FileInputStream(file);
            ois=new ObjectInputStream(fis);
            while(true){
                Curso cur=(Curso) ois.readObject();
                cursos.add(cur);
            }
        }catch(Exception e){

        }finally{
            if(ois!=null){
                ois.close();
            }
        }
        id = cursos.size();
        profesor.addCurso(this);
        Profesor.actualizarProfesor(profesor);
    }
    public static void actualizarCurso(Curso curso){
        ArrayList <Curso> cursos=new ArrayList<Curso>();
        File file=new File("Cursos.cur");
        ObjectInputStream ois= null;
        try{
            FileInputStream fis= new FileInputStream(file);
            ois= new ObjectInputStream(fis);
            while(true){
                Curso cur=(Curso) ois.readObject();
                if(curso.getName().equals(cur.getName())){
                    cursos.add(curso);
                }else{
                    cursos.add(cur);
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
        for(int i=0; i<cursos.size();i++){
            oos.writeObject(cursos.get(i));
        }
        oos.close();
        
    }
    public void addAsignacion(Asignacion a) {
        Asignaciones.add(a);
    }

    public void addEstudiante(Estudiante e) {
        estudiantes.add(e);
    }
    public void subEstudiante(Estudiante e) {
        ArrayList <Estudiante> estudis= new ArrayList<Estudiante>();
        for(int i=0; i<estudiantes.size();i++){
            if(!estudiantes.get(i).getName().equals(e.getName())){
                estudis.add(estudiantes.get(i));
            }
        }
        estudiantes=estudis;
    }
    public void addComunicado(Comunicado c) {
        comunicados.add(c);
    }
    public String getName(){
        return name;
    }
    public int getAsigs(){
        return Asignaciones.size();
    }
    public ArrayList<Asignacion> getAsignaciones(){
        return Asignaciones;
    }
    public void ajustarAsignacion(Asignacion asig, int pos){
        Asignaciones.set(pos, asig);
    }
}
