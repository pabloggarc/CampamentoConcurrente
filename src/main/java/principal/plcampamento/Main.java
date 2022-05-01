package principal.plcampamento;

import parte2.*; 
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Main {

    public static void main(String[] args){
        //Cargamos el log
        Registro registro=new Registro(); 
        registro.escribir("--------------------EJECUCIÓN "+LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS).toString()+"--------------------");
        
        //Arrancamos interfaz
        Interfaz interfaz=new Interfaz(new Pausa(), registro); 
        interfaz.setVisible(true); 
        
        //Creamos un campamento
        Campamento campamento=new Campamento(interfaz, registro);
        
        //Iniciamos servidor
        Servidor servidor=new Servidor(campamento); 
        servidor.start();
        
        //Creamos monitores y campistas
        Creador creador=new Creador(campamento);
        creador.start();
    }
    
}
