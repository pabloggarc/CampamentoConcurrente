package principal.plcampamento;

import java.util.ArrayList;

public class ListaMonitores {
    private ArrayList<Monitor> monitores; 
    
    public ListaMonitores(){
        this.monitores=new ArrayList<Monitor>(); 
    }
    
    public synchronized void meterMonitor(Monitor monitor){
        monitores.add(monitor); 
    }
    
    public synchronized void sacarMonitor(Monitor monitor){
        if(monitores.contains(monitor)){
            monitores.remove(monitor); 
        }
        else{
            System.out.println("Error al sacar al "+monitor.getID());
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
