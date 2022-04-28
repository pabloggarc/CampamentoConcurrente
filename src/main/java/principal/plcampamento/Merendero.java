package principal.plcampamento;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;

public class Merendero {
    private ListaCampistas campistas; 
    private ListaMonitores monitores; 
    private ListaCampistas campistasEspera; 
    private Semaphore aforo; 
    private LinkedBlockingQueue pilaSucias; 
    private LinkedBlockingQueue pilaLimpias; 
    private Interfaz interfaz; 
    private Registro registro; 
    
    public Merendero(Interfaz interfaz, Registro registro){
        this.campistas=new ListaCampistas(registro); 
        this.monitores=new ListaMonitores(registro); 
        this.campistasEspera=new ListaCampistas(registro); 
        this.aforo=new Semaphore(20, true); 
        this.pilaSucias=new LinkedBlockingQueue(25); 
        this.pilaLimpias=new LinkedBlockingQueue(25); 
        this.interfaz=interfaz; 
        this.registro=registro; 
        
        for(int i=1; i<=25; i++){
            interfaz.comprobarPausa();
            try{
                pilaSucias.put(new Bandeja(i));
                interfaz.setTextoMerenderoBandejasSucias(Integer.toString(pilaSucias.size())); 
            }
            catch(InterruptedException ie){
                registro.escribir("Error al apilar la bandeja "+i);
            }
        }
    }
    
    public Bandeja cogerBandeja(Campista campista){
        Bandeja b=new Bandeja(0); 
        try{
            interfaz.comprobarPausa();
            b=(Bandeja)pilaLimpias.take();
            actualizarInterfazBandejas();  
            registro.escribir("El campista "+campista.getID()+" coge la merienda "+b.getID());
        }
        catch(InterruptedException ie){
            registro.escribir("Error cuando el campista "+campista.getID()+" intenta coger una bandeja limpia");
        }
        finally{
            return b; 
        }
    }
    
    public Bandeja cogerBandeja(Monitor monitor){
        Bandeja b=new Bandeja(0); 
        try{
            interfaz.comprobarPausa();
            b=(Bandeja)pilaSucias.take();
            actualizarInterfazBandejas();  
            registro.escribir("El monitor "+monitor.getID()+" empieza a preparar la merienda "+b.getID());
        }
        catch(InterruptedException ie){
            registro.escribir("Error cuando el monitor "+monitor.getID()+" intenta coger una bandeja sucia");
        }
        finally{
            return b; 
        }
    }
    
    public void dejarBandeja(Campista campista, Bandeja bandeja){
        try{
            interfaz.comprobarPausa();
            registro.escribir("El campista "+campista.getID()+" deja la bandeja "+bandeja.getID());
            pilaSucias.put(bandeja); 
            actualizarInterfazBandejas(); 
        }
        catch(InterruptedException ie){
            registro.escribir("Error cuando el campista "+campista.getID()+" intenta dejar su bandeja sucia");
        }
    }
    
    public void dejarBandeja(Monitor monitor, Bandeja bandeja){
        try{
            interfaz.comprobarPausa();
            registro.escribir("El monitor "+monitor.getID()+" ha preparado la merienda "+bandeja.getID());
            pilaLimpias.put(bandeja); 
            actualizarInterfazBandejas(); 
        }
        catch(InterruptedException ie){
            registro.escribir("Error cuando el monitor "+monitor.getID()+" intenta dejar una bandeja limpia");
        }
    }
    
    public void entrar(Monitor monitor){
        interfaz.comprobarPausa();
        monitores.meterMonitor(monitor);
        interfaz.setTextoMerenderoMonitores(monitores.getIntegrantes()); 
        registro.escribir("El monitor "+monitor.getID()+" va a preparar meriendas");
    }
    
    public void sacar(Monitor monitor){
        interfaz.comprobarPausa();
        monitores.sacarMonitor(monitor);
        interfaz.setTextoMerenderoMonitores(monitores.getIntegrantes()); 
        registro.escribir("El monitor "+monitor.getID()+" se va del merendero");
    }
    
    public void entrar(Campista campista){
        interfaz.comprobarPausa();
        campistasEspera.meterCampista(campista);
        interfaz.setTextoMerenderoEspera(campistasEspera.getIntegrantes());
        try{
            aforo.acquire();
            interfaz.comprobarPausa();
            campistasEspera.sacarCampista(campista);
            interfaz.setTextoMerenderoEspera(campistasEspera.getIntegrantes());
        }
        catch(InterruptedException ie){
            registro.escribir("Error cuando el campista "+campista.getID()+" intentaba entrar al merendero");
        }
        interfaz.comprobarPausa();
        campistas.meterCampista(campista);
        interfaz.setTextoMerendero(campistas.getIntegrantes());
    }
    
    public void sacar(Campista campista){
        interfaz.comprobarPausa();
        aforo.release();
        campistas.sacarCampista(campista);
        interfaz.setTextoMerendero(campistas.getIntegrantes()); 
    }
    
    public void actualizarInterfazBandejas(){
        interfaz.setTextoMerenderoBandejasSucias(Integer.toString(pilaSucias.size())); 
        interfaz.setTextoMerenderoBandejasListas(Integer.toString(pilaLimpias.size()));
    }
}