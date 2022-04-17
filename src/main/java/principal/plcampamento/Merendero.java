package principal.plcampamento;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;

public class Merendero {
    private ListaCampistas campistas; 
    private ListaMonitores monitores; 
    private Semaphore aforo; 
    private LinkedBlockingQueue pilaSucias; 
    private LinkedBlockingQueue pilaLimpias; 
    
    public Merendero(){
        this.campistas=new ListaCampistas(); 
        this.monitores=new ListaMonitores(); 
        this.aforo=new Semaphore(20, true); 
        this.pilaSucias=new LinkedBlockingQueue(25); 
        this.pilaLimpias=new LinkedBlockingQueue(25);  
        
        for(int i=1; i<=25; i++){
            try{
                pilaSucias.put(new Bandeja(i));
            }
            catch(InterruptedException ie){
                System.out.println("Error al apilar la bandeja "+i);
            }
        }
    }
    
    public Bandeja cogerBandeja(Campista campista){
        Bandeja b=new Bandeja(0); 
        try{
            b=(Bandeja)pilaLimpias.take();
            System.out.println("El campista "+campista.getID()+" coge la merienda "+b.getID());
        }
        catch(InterruptedException ie){
            System.out.println("Error cuando el campista "+campista.getID()+" intenta coger una bandeja limpia");
        }
        finally{
            return b; 
        }
    }
    
    public Bandeja cogerBandeja(Monitor monitor){
        Bandeja b=new Bandeja(0); 
        try{
            b=(Bandeja)pilaSucias.take(); 
            System.out.println("El monitor "+monitor.getID()+" empieza a preparar la merienda "+b.getID());
        }
        catch(InterruptedException ie){
            System.out.println("Error cuando el monitor "+monitor.getID()+" intenta coger una bandeja sucia");
        }
        finally{
            return b; 
        }
    }
    
    public void dejarBandeja(Campista campista, Bandeja bandeja){
        try{
            System.out.println("El campista "+campista.getID()+" deja la bandeja "+bandeja.getID());
            pilaSucias.put(bandeja); 
        }
        catch(InterruptedException ie){
            System.out.println("Error cuando el campista "+campista.getID()+" intenta dejar su bandeja sucia");
        }
    }
    
    public void dejarBandeja(Monitor monitor, Bandeja bandeja){
        try{
            System.out.println("El monitor "+monitor.getID()+" ha preparado la merienda "+bandeja.getID());
            pilaLimpias.put(bandeja); 
        }
        catch(InterruptedException ie){
            System.out.println("Error cuando el monitor "+monitor.getID()+" intenta dejar una bandeja limpia");
        }
    }
    
    public void entrar(Monitor monitor){
        monitores.meterMonitor(monitor);
        System.out.println("El monitor "+monitor.getID()+" va a preparar meriendas");
    }
    
    public void sacar(Monitor monitor){
        monitores.sacarMonitor(monitor);
        System.out.println("El monitor "+monitor.getID()+" se va del merendero");
    }
    
    public void entrar(Campista campista){
        try{
            aforo.acquire();
        }
        catch(InterruptedException ie){
            System.out.println("Error cuando el campista "+campista.getID()+" intentaba entrar al merendero");
        }
        campistas.meterCampista(campista);
    }
    
    public void sacar(Campista campista){
        aforo.release();
        campistas.sacarCampista(campista);
    }
}