package parte1;

import java.util.ArrayList;

public class ListaMonitores {
    private ArrayList<Monitor> monitores; 
    private Registro registro; 
    
    public ListaMonitores(Registro registro){
        this.monitores=new ArrayList<Monitor>(); 
        this.registro=registro; 
    }
    
    public synchronized void meterMonitor(Monitor monitor){
        monitores.add(monitor); 
    }
    
    public synchronized void sacarMonitor(Monitor monitor){
        if(monitores.contains(monitor)){
            monitores.remove(monitor); 
        }
        else{
            registro.escribir("Error al sacar al "+monitor.getID());
        }
    }
    
    public synchronized String getIntegrantes(){
        String integ=""; 
        for(int i=0; i<monitores.size(); i++){
            if(i==0){
                integ+=monitores.get(i).getID(); 
            }
            else{
                integ+=", "+monitores.get(i).getID(); 
            }
        }
        return integ; 
    }
}
