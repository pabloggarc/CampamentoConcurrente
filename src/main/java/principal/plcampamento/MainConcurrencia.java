package principal.plcampamento;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class MainConcurrencia {


    public static void main(String[] args){
        //Cargamos el log
        Registro registro=new Registro(); 
        registro.escribir("--------------------EJECUCIÓN "+LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS).toString()+"--------------------");
        
        //Arrancamos interfaz
        Interfaz interfaz=new Interfaz(new Pausa(), registro); 
        interfaz.setVisible(true); 
        
        //Creamos un campamento
        Campamento campamento=new Campamento(interfaz, registro); 
        
        //Creamos monitores
        for(int j=1; j<5; j++){
            Monitor m=new Monitor(j, campamento); 
            m.start(); 
        }
        
        //Creamos campistas poco a poco
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
    
}
