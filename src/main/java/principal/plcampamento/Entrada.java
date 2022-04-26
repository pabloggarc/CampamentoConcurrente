package principal.plcampamento;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.*;

public class Entrada {
    private ListaCampistas campistas; 
    private boolean estado; 
    private Lock cerrojo;
    private Condition esperar;
    private AtomicInteger contadorMonitores; 
    private Interfaz interfaz; 
    private boolean entradaA; 
    
    public Entrada(Interfaz interfaz, boolean entradaA){
        this.campistas=new ListaCampistas(); 
        this.estado=false; 
        this.cerrojo=new ReentrantLock(); 
        this.esperar=cerrojo.newCondition();
        this.contadorMonitores=new AtomicInteger(1); 
        this.interfaz=interfaz; 
        this.entradaA=entradaA; 
    }
    
    public void abrir(){
        try{
            cerrojo.lock(); 
            estado=true;
            esperar.signalAll();
        }
        finally{
            cerrojo.unlock(); 
        }
    }
    
    public void cerrar(){
        try{
            cerrojo.lock(); 
            estado=false; 
        }
        finally{
            cerrojo.unlock(); 
        }
    }
    
    public void pasarPuerta(Campista campista){
        try{
            cerrojo.lock(); 
            campistas.meterCampista(campista);
            actualizarInterfaz(); 
            while(!estado){
                try{
                    esperar.await(); 
                }
                catch(InterruptedException e){
                    System.out.println("Error al esperar para entrar");
                }
            }
        }
        finally{
            cerrojo.unlock(); 
        }
    }
    
    public void borrarApuntado(Campista campista){
        campistas.sacarCampista(campista);
        actualizarInterfaz(); 
    }
    
    public boolean estaAbierta(){
        return estado; 
    }
    
    public void contar(){
        contadorMonitores.decrementAndGet(); 
    }
    
    public int consultarContador(){
        return contadorMonitores.get(); 
    }
     
    public void actualizarInterfaz(){
        if(entradaA){
            interfaz.setTextoEntradaA(campistas.getIntegrantes()); 
        }
        else{
            interfaz.setTextoEntradaB(campistas.getIntegrantes()); 
        }
    }
}
