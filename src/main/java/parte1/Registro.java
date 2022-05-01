package parte1;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Registro {
    private FileWriter escritor; 
    private Lock cerrojo; 
    
    public Registro(){
        try{
            this.escritor=new FileWriter("evolucionCampamento.txt", true);
            this.cerrojo=new ReentrantLock();
        }
        catch(IOException ioe){
            System.out.println("Error al abrir el registro del campamento");
        }
    }
    
    public void escribir(String t){
        LocalDateTime fechaHora=LocalDateTime.now(); 
        System.out.println(t);
        try{
            cerrojo.lock();
            escritor.write("["+fechaHora.truncatedTo(ChronoUnit.SECONDS)+"]  "+t+"\n");
        }
        catch(IOException ioe){
            System.out.println("Error al escribir en el registro");
        }
        finally{
            cerrojo.unlock();
        }
    }
    
    public void cerrarRegistro(){
        try {
            escritor.close();
        } 
        catch(IOException ex){
            System.out.println("Error al cerrar el registro");
        }
    }
}
