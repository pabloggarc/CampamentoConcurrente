package principal.plcampamento;

import java.util.ArrayList;

public class ListaCampistas {
    private ArrayList<Campista> campistas; 
    private Registro registro; 
    
    public ListaCampistas(Registro registro){
        this.campistas=new ArrayList<Campista>(); 
        this.registro=registro; 
    }
    
    public synchronized void meterCampista(Campista campista){
        campistas.add(campista); 
    }
    
    public synchronized void sacarCampista(Campista campista){
        if(campistas.contains(campista)){
            campistas.remove(campista); 
        }
        else{
            registro.escribir("Error al colocar al campista "+campista.getID()+" en una entrada");
        }
    }
    
    public synchronized String getIntegrantes(){
        String integ=""; 
        for(int i=0; i<campistas.size(); i++){
            if(i==0){
                integ+=campistas.get(i).getID(); 
            }
            else{
                integ+=", "+campistas.get(i).getID(); 
            }
        }
        return integ; 
    }
    
    public synchronized int cuantosIntegrantes(){
        return campistas.size(); 
    }
    
    public synchronized Campista getIntegrante(int i){
        return campistas.get(i); 
    }
    
    public synchronized boolean pertenece(Campista campista){
        return campistas.contains(campista); 
    }
}
