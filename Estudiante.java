import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.io.Serializable;

public class Estudiante extends Usuarios implements Serializable {
    private Curso[] cursos = new Curso[50];
    private int cantcursos = 0;
    private Curso cur = null;
    private Asignacion asignacion = null;
    private Scanner scan = new Scanner(System.in);
    private int asigpos;

    public Estudiante(String password, String name, int rol) {
        super(password, name, rol);
    }
    public void menu() throws ClassNotFoundException, IOException{
        int opcion = 0;
        System.out.println("Bienvenido " + name + "\n");
        while (opcion != 6) {
            System.out.println("Que opcion deseas realizar?\n 1)Ver cursos\n2)Enviar proyecto\n3)Agregar curso\n4)Eliminar curso\n5)Comunicar\n6)Salir");
            opcion = myObj.nextInt();
            myObj.skip("\n");
            if (opcion == 1) {
                System.out.println("luego");
            } 
            else if (opcion == 2) {
                hacerEntrega();
            } 
            else if (opcion == 3) {
                pedirCurso();
            }
            else if (opcion == 4) {
                eliminarCurso();
            }
            else if (opcion == 4) {
                Comunicar();
            }
        }
    }
    public static void actualizarEstudiante(Estudiante estudiante) throws ClassNotFoundException, IOException {
        ArrayList<Estudiante> estudiantes = new ArrayList<Estudiante>();
        File file = new File("Estudiantes.usu");
        ObjectInputStream ois = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            while (true) {
                Estudiante estu = (Estudiante) ois.readObject();
                if (estudiante.getName().equals(estu.getName())) {
                    estudiantes.add(estudiante);
                } else {
                    estudiantes.add(estu);
                }
            }
        } catch (Exception e) {

        } finally {
            if (ois != null) {
                ois.close();
            }
        }
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        for (int i = 0; i < estudiantes.size(); i++) {
            oos.writeObject(estudiantes.get(i));
        }
        oos.close();
    }

    public void actualizarCursos() throws ClassNotFoundException, IOException {
        File file = new File("Estudiantes.usu");
        ObjectInputStream ois = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            while (true) {
                Estudiante estu = (Estudiante) ois.readObject();
                if (this.getName().equals(estu.getName())) {
                    this.cursos = estu.cursos;
                }
            }
        } catch (Exception e) {

        } finally {
            if (ois != null) {
                ois.close();
            }
        }
    }

    public void setCurso() throws ClassNotFoundException, IOException {
        String name;
        Curso cur = null;
        boolean is = true;
        actualizarCursos();
        do {
            System.out.print("Indique el nombre del curso: ");
            name = scan.nextLine();
            for (int i = 0; i < cantcursos; i++) {
                if (cursos[i].getName().equals(name)) {
                    is = false;
                    cur = cursos[i];
                    break;
                }
            }
        } while (is);
        this.cur = cur;
    }

    public void setAsignacion() {
        String name;
        Asignacion as = null;
        boolean is = true;
        if (cur == null) {
            System.out.println("Primero establesca un curso...");
            return;
        }
        do {
            System.out.print("Indique el nombre de la asignacion: ");
            name = scan.nextLine();
            for (int i = 0; i < cur.getTamA(); i++) {
                if (cur.getAsignaciones()[i].getName().equals(name)) {
                    is = false;
                    as = cur.getAsignaciones()[i];
                    asigpos = i;
                    break;
                }
            }
        } while (is);
        this.asignacion = as;
    }

    public void hacerEntrega() throws ClassNotFoundException, IOException {
        String cuerpo;
        String adjunto;
        Comunicado comentario;
        String cuerpoc;
        if (asignacion == null) {
            System.out.println("Primero establezca una asignación...");
            return;
        }
        System.out.print("Indique el cuerpo de la entrega: ");
        cuerpo = scan.nextLine();
        System.out.print("Indique el path del archivo adjunto: ");
        adjunto = scan.nextLine();
        System.out.print("Indique un comentario: ");
        cuerpoc = scan.nextLine();
        comentario = new Comunicado(this.getName(), cuerpoc);
        asignacion.addEntrega(new Entrega(this.getName(), cuerpo, adjunto, comentario));
        cur.ajustarAsignacion(asignacion, asigpos);
        Curso.actualizarCurso(cur);
        Estudiante.actualizarEstudiante(this);
    }

    public void Comunicar() throws ClassNotFoundException, IOException {
        String cuerpo;
        System.out.print("Escriba el mensaje: ");
        cuerpo = scan.nextLine();
        Comunicado com = new Comunicado(getName(), cuerpo);
        cur.addComunicado(com);
        Curso.actualizarCurso(cur);
        Estudiante.actualizarEstudiante(this);
    }

    public void pedirCurso() throws ClassNotFoundException, IOException {
        File file = new File("Peticiones.pet");
        FileOutputStream fos = new FileOutputStream(file, true);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        String cuerpo;
        ArrayList<Curso> curs = new ArrayList<Curso>();
        File f = new File("Cursos.cur");
        ObjectInputStream ois = null;
        try {
            FileInputStream fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            while (true) {
                Curso curso = (Curso) ois.readObject();
                curs.add(curso);
            }
        } catch (Exception e) {

        } finally {
            if (ois != null) {
                ois.close();
            }
        }
        boolean is = true;
        do {
            System.out.print("Indique el curso que desea pedir: ");
            cuerpo = scan.nextLine();
            for (int i = 0; i < curs.size(); i++) {
                if (curs.get(i).getName().equals(cuerpo)) {
                    is = false;
                    break;
                }
            }
        } while (is);
        oos.writeObject(new Comunicado(this.getName(), cuerpo));
    }
    public void eliminarCurso() throws ClassNotFoundException, IOException {
        File file = new File("Peticiones.pet");
        FileOutputStream fos = new FileOutputStream(file, true);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        String cuerpo;
        ArrayList<Curso> curs = new ArrayList<Curso>();
        File f = new File("Cursos.cur");
        ObjectInputStream ois = null;
        try {
            FileInputStream fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            while (true) {
                Curso curso = (Curso) ois.readObject();
                curs.add(curso);
            }
        } catch (Exception e) {

        } finally {
            if (ois != null) {
                ois.close();
            }
        }
        boolean is = true;
        do {
            System.out.print("Indique el curso que desea eliminar: ");
            cuerpo = scan.nextLine();
            for (int i = 0; i < curs.size(); i++) {
                if (curs.get(i).getName().equals(cuerpo)) {
                    is = false;
                    break;
                }
            }
        } while (is);
        oos.writeObject(new Comunicado(this.getName(), cuerpo));
    }
}