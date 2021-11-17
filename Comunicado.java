public class Comunicado {
    private String name;
    private String Cuerpo;

    public Comunicado(String name, String cuerpo) {
        this.name = name;
        this.Cuerpo = cuerpo;
    }

    public void SetCuerpo(String Cuerpo) {
        this.Cuerpo = Cuerpo;
    }

    public String GetName() {
        return name;
    }

    public String GetCuerpo() {
        return Cuerpo;
    }
}