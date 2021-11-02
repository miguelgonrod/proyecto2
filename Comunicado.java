public class Comunicado{
    private String name;
    private String Cuerpo;
    public Comunicado(String name, String cuerpo){
        this.name=name;
        this.cuerpo=cuerpo;
    }
    public void SetCuerpo(String Cuerpo){
        this.cuerpo=cuerpo;
    }
    public String GetName(){
        return name;
    }
    public String GetCuerpo(){
        return cuerpo;
    }
}