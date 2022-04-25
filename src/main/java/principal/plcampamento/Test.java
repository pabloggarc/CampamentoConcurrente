package principal.plcampamento;

public class Test {

    public static void main(String[] args){
        Campamento campamento=new Campamento(); 
        for(int i=1; i<=20; i++){
            Campista c=new Campista(i, campamento); 
            c.start(); 
        }
        for(int j=1; j<5; j++){
            Monitor m=new Monitor(j, campamento); 
            m.start(); 
        }
    }
    
}
