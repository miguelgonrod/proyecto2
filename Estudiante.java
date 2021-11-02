import java.util.Scanner;

public class Estudiante extends Usuarios{
    Scanner myObj = new Scanner(System.in);
    private String name;
    public void SetNombre(String name){
        this.name = name;
    }
    public void menu(){
        int opcion=0;
        System.out.println("Bienvenido " + name + "\n");
        while(opcion!=4){
            System.out.println("Que opcion deseas realizar?\n 1)Ver cursos\n2)Enviar proyecto\n3)Agregar\\Eliminar curso\n4)Salir");
            opcion = myObj.nextInt();
            myObj.skip("\n");
            if(opcion==1){
                System.out.println("luego");
            }
            else if(opcion==2){
                System.out.println("luego2");
            }
            else if(opcion==3){
                System.out.println("luego3");
            }
        }
    }
}