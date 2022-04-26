package principal.plcampamento;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.*;

public class Soga {
    private ListaCampistas campistas; 
    private ListaCampistas equipoA;
    private ListaCampistas equipoB; 
    private Monitor monitor;  
    private Lock control; 
    private int aforo; 
    private CyclicBarrier esperaSoga;
    private boolean ganadorA; 
    private Interfaz interfaz; 

    public Soga(Interfaz interfaz){
        this.campistas=new ListaCampistas(); 
        this.equipoA=new ListaCampistas(); 
        this.equipoB=new ListaCampistas(); 
        this.monitor=null; 
        this.control=new ReentrantLock(); 
        this.aforo=0; 
        this.esperaSoga=new CyclicBarrier(11); 
        this.ganadorA=false; 
        this.interfaz=interfaz; 
    }
    
    public boolean entrarSoga(Campista campista){
        /*Cuando llega un campista a la soga intenta entrar si hay espacio. Si lo hay se queda, espera a que 
        llegue un monitor, y entonces espera a que este haga los equipos. Si no hay hueco se marcha. El quedarse
        o marcharse se indica con el boolean que devuelve la funcion*/
        
        control.lock(); 
        boolean id; 
        if(aforo<10){
            id=true; 
            aforo++; 
        }
        else{
            id=false; 
        }
        control.unlock(); 
        
        if(id){
            campistas.meterCampista(campista);
            interfaz.setTextoSoga(campistas.getIntegrantes());
            System.out.println("El campista "+campista.getID()+" espera para competir en la soga");

            try{
                esperaSoga.await(); 
            }
            catch(Exception e){
                System.out.println("Error mientras el campista "+campista.getID()+" espera a que llegue un monitor a la soga");
            }

            try{
                //El campista espera a que el monitor haga los equipos
                esperaSoga.await(); 
            }
            catch(Exception e){
                System.out.println("Error mientras el campista espera a que el monitor haga los equipos");
            }
            
            System.out.println("El campista "+campista.getID()+" tira de la soga!");
        }
        return id; 
    }
    
    public boolean entrarSoga(Monitor monitor){
        //Simula la llegada del monitor a la actividad de soga
        
        if(this.monitor==null){
            this.monitor=monitor;
            interfaz.setTextoSogaMonitor(monitor.getID());
            System.out.println("El monitor "+monitor.getID()+" llega a la actividad de soga");
            
            return true; 
        }
        else{
            System.out.println("El monitor "+monitor.getID()+" no gestiona la soga porque ya hay otro monitor");
            return false; 
        }
    }
    
    public void salirSoga(Campista campista){
        //Simula la salida de un campista de la actividad de soga
        
        control.lock(); 
        System.out.println("El campista "+campista.getID()+" abandona la actividad de soga");
        aforo--; 
        control.unlock(); 
    }
    
    public void salirSoga(Monitor monitor){
        //Simula la salida del monitor de la actividad de soga
        
        System.out.println("El monitor "+monitor.getID()+" abandona la soga");
        this.monitor=null;
        interfaz.setTextoSogaMonitor("");
    }
    
    public void avisarSoga(){
        //Simula cuando un campista o el monitor avise al resto desde sus run()
        
        try{
            esperaSoga.await(); 
        }
        catch(Exception e){
            System.out.println("Error cuando un monitor o campista avisa al resto");
        }
    }
    
    public void hacerEquipos(){
        //Simula la accion del monitor de realizar los equipos de la actividad soga una vez han llegado 10 campistas
        
        for(int i=0; i<10; i++){
            int c=(int)(Math.random()*campistas.cuantosIntegrantes()); 
            Campista campista=campistas.getIntegrante(c); 
            if(equipoA.cuantosIntegrantes()!=5){
                equipoA.meterCampista(campista);
            }
            else{
                equipoB.meterCampista(campista);
            }
            campistas.sacarCampista(campista); 
        }
    }
    
    public void limpiarSoga(){
        //Simula el fin de la actividad y la prepara para los siguientes campistas
        
        campistas=new ListaCampistas(); 
        equipoA=new ListaCampistas(); 
        equipoB=new ListaCampistas(); 
        ganadorA=false; 
        
        interfaz.setTextoSoga("");
        interfaz.setTextoSogaEquipoA("");
        interfaz.setTextoSogaEquipoB(""); 
    }
    
    public boolean haGanado(Campista campista){
        //Comprueba si un campista ha ganado la partida de soga
        
        if(ganadorA){
            return equipoA.pertenece(campista); 
        }
        else{
            return equipoB.pertenece(campista); 
        }
    }
    
    public ListaCampistas getEquipoA(){
        return equipoA; 
    }
    
    public ListaCampistas getEquipoB(){
        return equipoB; 
    }
    
    public void setGanador(boolean b){
        ganadorA=b; 
    }
    
    public void actualizarInterfazEquipos(){
        interfaz.setTextoSogaEquipoA(equipoA.getIntegrantes());
        interfaz.setTextoSogaEquipoB(equipoB.getIntegrantes()); 
    }
    
    public void actualizarInterfazGanadores(){
        if(ganadorA){
            interfaz.setTextoSogaGanadores(equipoA.getIntegrantes());
        }
        else{
            interfaz.setTextoSogaGanadores(equipoB.getIntegrantes());
        }
    }
    
}