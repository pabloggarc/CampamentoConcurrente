package parte1;

public class Creador extends Thread{
    private Campamento campamento; 
    
    public Creador(Campamento campamento){
        this.campamento=campamento; 
    }
    
    public void crearMonitores(){
        for(int j=1; j<5; j++){
            Monitor m=new Monitor(j, campamento); 
            m.start(); 
        }
    }
    
    public void crearCampistas(){
        for(int i=1; i<=20000; i++){
            Campista c=new Campista(i, campamento); 
            c.start(); 
            
            try{
                Thread.sleep((int)Math.floor(Math.random()*(3000-1000+1)+3000)); 
            }
            catch(InterruptedException ie){
                campamento.escribirRegistro("Error al crear campista");
            }
        }
    }
    
    @Override
    public void run(){
        crearMonitores(); 
        crearCampistas(); 
    }
}
