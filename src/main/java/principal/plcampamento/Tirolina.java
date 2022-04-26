package principal.plcampamento;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Tirolina {
    private ListaCampistas campistas; 
    private Monitor monitor; 
    private Campista campista;
    private Semaphore aforo; 
    private CyclicBarrier esperaTirolina;
    private Interfaz interfaz; 
    
    public Tirolina(Interfaz interfaz){
        this.campistas=new ListaCampistas();  
        this.monitor=null;  
        this.campista=null; 
        this.aforo=new Semaphore(1, true); 
        this.esperaTirolina=new CyclicBarrier(2); 
        this.interfaz=interfaz; 
    }
    
    public void entrarTirolina(Campista campista){
        /*Dado un campista lo mete a la lista de campistas en la tirolina, si no hay nadie esperando
        pasa a la tirolina. En caso de haber alguien espera hasta que salga o sea su turno. 
        Una vez es su turno espera a que llegue un monitor. Cuando este llega espera a que lo prepare y entonces 
        es lanzado. */
        
        campistas.meterCampista(campista);
        System.out.println("El campista "+campista.getID()+" se ha puesto a la cola de la tirolina");
        try{
            aforo.acquire();
        }
        catch(InterruptedException ie){
            System.out.println("Error cuando el campista "+campista.getID()+" intenta coger turno de tirolina");
        }
        
        interfaz.setTextoTirolina(campistas.getIntegrantes());
        
        try{
            esperaTirolina.await(); 
        }
        catch(Exception e){
            System.out.println("Error al esperar a la llegada del monitor");
        }
        
        this.campista=campista; 
        interfaz.setTextoTirolinaCampistaPreparacion(campista.getID());
        
        try{
            esperaTirolina.await(); 
        }
        catch(Exception e){
            System.out.println("Error al esperar a que el monitor prepare al campista "+campista.getID());
        }
        System.out.println("El campista "+campista.getID()+" se tira por la tirolina!");
        
        interfaz.setTextoTirolinaCampistaPreparacion("");
        interfaz.setTextoTirolinaTirandose(campista.getID());
    }
    
    public void entrarTirolina(Monitor monitor){
        //Simula la entrada del monitor a la tirolina
        
        if(this.monitor!=null){
            System.out.println("Error cuando el monitor "+monitor.getID()+" entraba en la tirolina, ya había uno");
        }
        else{
            this.monitor=monitor; 
            interfaz.setTextoTirolinaMonitor(monitor.getID());
        } 
    }
    
    public void bajarseTirolina(Campista campista){
        //Quita al campista de la tirolina y da paso al siguiente de la cola en caso de haberlo
        
        this.campista=null;
        campistas.sacarCampista(campista); 
        
        interfaz.setTextoTirolinaTirandose("");
        interfaz.setTextoTirolina(campistas.getIntegrantes()); 
        System.out.println("El campista "+campista.getID()+" se baja de la tirolina");
        
        aforo.release(); 
    }
    
    public void salirTirolina(Monitor monitor){
        //Simula la salida del monitor de la tirolina
        
        this.monitor=null; 
        
        interfaz.setTextoTirolinaMonitor("");
        System.out.println("El monitor "+monitor.getID()+" ha lanzado a 10 campistas y se marcha de la tirolina");
    }
    
    public void avisarCampista(Monitor monitor){
        //Simula cuando el monitor avisa por su estado de listo en la tirolina y que el campista se puede tirar
        
        try{
            esperaTirolina.await(); 
        }
        catch(Exception e){
            System.out.println("Error mientras el monitor notifica a un campista en la tirolina");
        }
    }
}
