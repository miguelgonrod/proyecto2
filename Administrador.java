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

public class Administrador extends Usuarios throws ClassNotFoundException, IOException implements Serializable{
    public Scanner scan = new Scanner(System.in);

    public Administrador(String password, String name, int rol) {
        super(password, name, rol);
    }

    public void menu(){
        int opcion=0;
        while(opcion!=4){
            System.out.println("Que opcion deseas realizar?");
        }
    }

    public void crearProfesor() {
        ArrayList<Profesor> profes = new ArrayList<Profesor>();
        String name;
        String password;
        System.out.print("Indique el nombre: ");
        name = scan.nextLine();
        System.out.print("Indique la contraseña: ");
        password = scan.nextLine();
        Profesor profesor = new Profesor(password, name, 2);
        File file = new File("Profesores.usu");
        ObjectInputStream ois = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            while (true) {
                Profesor prof = (Profesor) ois.readObject();
                profes.add(prof);
            }
        } catch (Exception e) {

        } finally {
            if (ois != null) {
                ois.close();
            }
        }
        profes.add(profesor);
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        for (int i = 0; i < profes.size(); i++) {
            oos.writeObject(profes.get(i));
        }
        oos.close();
    }

    public void crearEstudiante() {
        ArrayList<Estudiante> estudiantes = new ArrayList<Estudiante>();
        String name;
        String password;
        System.out.print("Indique el nombre: ");
        name = scan.nextLine();
        System.out.print("Indique la contraseña: ");
        password = scan.nextLine();
        Estudiante estudiante = new Estudiante(password, name, 3);
        File file = new File("Estudiantes.usu");
        ObjectInputStream ois = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            while (true) {
                Estudiante estu = (Estudiante) ois.readObject();
                estudiantes.add(estu);
            }
        } catch (Exception e) {

        } finally {
            if (ois != null) {
                ois.close();
            }
        }
        estudiantes.add(estudiante);
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        for (int i = 0; i < estudiantes.size(); i++) {
            oos.writeObject(estudiantes.get(i));
        }
        oos.close();
    }

    public void crearAdmin() {
        ArrayList<Administrador> admins = new ArrayList<Administrador>();
        String name;
        String password;
        System.out.print("Indique el nombre: ");
        name = scan.nextLine();
        System.out.print("Indique la contraseña: ");
        password = scan.nextLine();
        Administrador admin = new Administrador(password, name, 1);
        File file = new File("Admins.usu");
        ObjectInputStream ois = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            while (true) {
                Administrador adm = (Administrador) ois.readObject();
                admins.add(adm);
            }
        } catch (Exception e) {

        } finally {
            if (ois != null) {
                ois.close();
            }
        }
        admins.add(admin);
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        for (int i = 0; i < admins.size(); i++) {
            oos.writeObject(admins.get(i));
        }
        oos.close();
    }

    public void crearCurso() {
        ArrayList<Curso> cursos = new ArrayList<Curso>();
        String name;
        String curso;
        System.out.print("Indique el nombre del profesor: ");
        name = scan.nextLine();
        File file = new File("Profesores.usu");
        ObjectInputStream ois = null;
        Profesor prof=null;
        try {
            FileInputStream fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            while (true) {
                prof = (Profesor) ois.readObject();
                if(name.equals(prof.getName())){
                    int i=1/0;
                }
                else{
                    prof=null;
                }
            }
        } catch (Exception e) {

        } finally {
            if (ois != null) {
                ois.close();
            }
        }
        System.out.print("Indique el nombre del curso: ");
        curso = scan.nextLine();
        Curso curs = new Curso(curso,prof);
        file = new File("Cursos.cur");
        ois = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            while (true) {
                Curso cur = (Curso) ois.readObject();
                cursos.add(cur);
            }
        } catch (Exception e) {

        } finally {
            if (ois != null) {
                ois.close();
            }
        }
        cursos.add(curs);
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        for (int i = 0; i < cursos.size(); i++) {
            oos.writeObject(cursos.get(i));
        }
        oos.close();
    }
    public void eliminarCurso(){
        String name;
        ArrayList<Curso> cursos=new ArrayList<Curso>();
        System.out.print("Indique el curso que desea eliminar: ");
        name=scan.nextLine();
        File file = new File("Cursos.cur");
        ObjectInputStream ois = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            while (true) {
                Curso cur = (Curso) ois.readObject();
                if(!cur.getName().equals(name)){
                    cursos.add(cur);
                }
            }
        } catch (Exception e) {

        } finally {
            if (ois != null) {
                ois.close();
            }
        }
        FileOutputStream fos=new FileOutputStream(file);
        ObjectOutputStream oos=new ObjectOutputStream(fos);
        for (int i = 0; i < cursos.size(); i++) {
            oos.writeObject(cursos.get(i));
        }
        oos.close();
    }
    public void inscribirEstudiante(){
        System.out.print("Indique el nombre del estudiante: ");
        String name=scan.nextLine();
        System.out.print("Indique el nombre del curso: ");
        String namec=scan.nextLine();
        File file=new File("Cursos.cur");
        ObjectInputStream ois= null;
        Curso cur=null;
        try{
            FileInputStream fis= new FileInputStream(file);
            ois= new ObjectInputStream(fis);
            while(true){
                cur=(Curso) ois.readObject();
                if(cur.getName().equals(namec)){
                    int i=1/0;
                }
                cur=null;
            }
        }catch(Exception e){
            
        }finally{
            if(ois!=null){
                ois.close();
            }
        }
        file=new File("Estudiantes.usu");
        ois= null;
        Estudiante e=null;
        try{
            FileInputStream fis= new FileInputStream(file);
            ois= new ObjectInputStream(fis);
            while(true){
                e=(Estudiante) ois.readObject();
                if(e.getName().equals(name)){
                    int i=1/0;
                }
                e=null;
            }
        }catch(Exception ex){
            
        }finally{
            if(ois!=null){
                ois.close();
            }
        }
        cur.addEstudiante(e);
        Curso.actualizarCurso(cur);
    }
    public void eliminarrEstudiante(){
        System.out.print("Indique el nombre del estudiante: ");
        String name=scan.nextLine();
        System.out.print("Indique el nombre del curso: ");
        String namec=scan.nextLine();
        File file=new File("Cursos.cur");
        ObjectInputStream ois= null;
        Curso cur=null;
        try{
            FileInputStream fis= new FileInputStream(file);
            ois= new ObjectInputStream(fis);
            while(true){
                cur=(Curso) ois.readObject();
                if(cur.getName().equals(namec)){
                    int i=1/0;
                }
                cur=null;
            }
        }catch(Exception e){
            
        }finally{
            if(ois!=null){
                ois.close();
            }
        }
        file=new File("Estudiantes.usu");
        ois= null;
        Estudiante e=null;
        try{
            FileInputStream fis= new FileInputStream(file);
            ois= new ObjectInputStream(fis);
            while(true){
                e=(Estudiante) ois.readObject();
                if(e.getName().equals(name)){
                    int i=1/0;
                }
                e=null;
            }
        }catch(Exception ex){
            
        }finally{
            if(ois!=null){
                ois.close();
            }
        }
        cur.subEstudiante(e);
        Curso.actualizarCurso(cur);
    }
}
