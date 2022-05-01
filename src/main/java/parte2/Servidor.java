package parte2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import principal.plcampamento.Campamento;

public class Servidor extends Thread{
    private ExecutorService pool; 
    private ServerSocket socket;
    private Campamento campamento; 
    
    public Servidor(Campamento campamento){
        this.campamento=campamento; 
        this.pool=Executors.newFixedThreadPool(10);
        try{
            this.socket=new ServerSocket(5000); 
        }
        catch(IOException ioe){
            campamento.escribirRegistro("Error al iniciar servidor");
        }
    }
    
    @Override
    public void run(){
        campamento.escribirRegistro("Servidor conectado!");
        while(true){
            try{ 
                Socket conexion=socket.accept();
                RespuestaServidor respuesta=new RespuestaServidor(campamento, conexion); 
                pool.execute(respuesta);
            } 
            catch(IOException ioe){
                campamento.escribirRegistro("Error del servidor al atender peticiones");
            }
        }
    }

}
