package parte2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import principal.plcampamento.Campamento;

public class RespuestaServidor implements Runnable{
    private Campamento campamento; 
    private Socket conexion; 
    private DataInputStream entrada; 
    private DataOutputStream salida; 
    
    public RespuestaServidor(Campamento campamento, Socket conexion){
        this.campamento=campamento; 
        this.conexion=conexion; 
        try{
            this.entrada=new DataInputStream(conexion.getInputStream()); 
            this.salida=new DataOutputStream(conexion.getOutputStream());
        }
        catch(IOException ioe){
            campamento.escribirRegistro("Error al iniciar respuesta desde el servidor");
        }
    }
    
    @Override
    public void run(){
        String peticion=""; 
        try{
            peticion=entrada.readUTF();
        } 
        catch(IOException ioe){
            campamento.escribirRegistro("Error al leer petición desde el servidor");
        }
        
        int respuesta=-1; 
        if(Integer.parseInt(String.valueOf(peticion.charAt(0)))==1){
            respuesta=campamento.getActividadesCampista(Integer.parseInt(peticion.substring(1))); 
        }
        else{
            switch(Integer.parseInt(peticion.substring(1))){
                case 0:{
                    respuesta=campamento.getEsperaTirolina();
                    break; 
                }
                case 1:{
                    respuesta=campamento.getUsosTirolina(); 
                    break; 
                }
                case 2:{
                    respuesta=campamento.getCampistasSoga();
                    break; 
                }
                case 3:{
                    respuesta=campamento.getCampistasMerendando(); 
                    break; 
                }
                case 4:{
                    respuesta=campamento.getBandejasSucias(); 
                    break; 
                }
                case 5:{
                    respuesta=campamento.getBandejasLimpias(); 
                    break; 
                }
            }
        }
        try{
            salida.writeInt(respuesta);
        }
        catch(IOException ioe){
            campamento.escribirRegistro("Error al enviar respuesta desde el servidor");
        }
    }
}
