import java.util.ArrayList;

public class Asignacion {
    private ArrayList<Entrega> entregas = new ArrayList<Entrega>();
    private int id;
    private String name;
    private String descripcion;
    private static int idGeneral;

    public Asignacion(String name, String descripcion,int id) {
        this.name = name;
        this.descripcion = descripcion;
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getName() {
        return name;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void addEntrega(Entrega e) {
        entregas.add(e);
    }
    public ArrayList<Entrega> getEntregas(){
        return entregas;
    }
    public void ajustarEntrega(Entrega entre, int pos){
        entregas.set(pos, entre);
    }
}