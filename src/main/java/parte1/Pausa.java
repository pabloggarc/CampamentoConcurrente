package parte1;

import java.util.concurrent.locks.*;

public class Pausa {
    private Lock cerrojo; 
    private Condition pausa;
    private boolean pulsado; 
    
    public Pausa(){
        this.cerrojo=new ReentrantLock(); 
        this.pausa=cerrojo.newCondition(); 
        this.pulsado=false; 
    }
    
    public void pasar(){
        try{
            cerrojo.lock();
            while(pulsado){
                pausa.await();
            }
        }
        catch(InterruptedException ie){
            System.out.println("Error mientras se espera a retomar la pausa");
        }
        finally{
            cerrojo.unlock(); 
        }
    }
    
    public void pausar(){
        try{
            cerrojo.lock(); 
            pulsado=true; 
        }
        finally{
            cerrojo.unlock();
        }
        
    }
    
    public void continuar(){
        try{
            cerrojo.lock(); 
            pulsado=false; 
            pausa.signalAll();
        }
        finally{
            cerrojo.unlock();
        }
    }
}
