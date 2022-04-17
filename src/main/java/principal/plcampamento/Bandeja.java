package principal.plcampamento;

public class Bandeja {
    private int id; 
    
    public Bandeja(int id){
        this.id=id; 
    }
    
    public String getID(){
        return "Bandeja"+id; 
    }
}
