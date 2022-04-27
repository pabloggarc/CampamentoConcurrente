package principal.plcampamento;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Test {


    public static void main(String[] args){
        try{ 
            PrintStream ps=new PrintStream(new BufferedOutputStream(new FileOutputStream(new File("logCampamento.txt"), true)), true);
            //System.setOut(ps);
        } 
        catch(FileNotFoundException ex){
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Interfaz interfaz=new Interfaz(new Pausa()); 
        interfaz.setVisible(true); 
        
        Campamento campamento=new Campamento(interfaz); 
        
        for(int j=1; j<5; j++){
            Monitor m=new Monitor(j, campamento); 
            m.start(); 
        }
        
        for(int i=1; i<=20000; i++){
            Campista c=new Campista(i, campamento); 
            c.start(); 
            
            try{
                Thread.sleep((int)Math.floor(Math.random()*(3000-1000+1)+3000)); 
            }
            catch(InterruptedException ie){
                System.out.println("Error al crear campista");
            }
        }
        
    }
    
}
