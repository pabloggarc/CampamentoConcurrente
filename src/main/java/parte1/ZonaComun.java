package parte1;

public class ZonaComun {
    private ListaCampistas campistas; 
    private ListaMonitores monitores; 
    private Interfaz interfaz; 
    private Registro registro; 
    
    public ZonaComun(Interfaz interfaz, Registro registro){
        this.campistas=new ListaCampistas(registro); 
        this.monitores=new ListaMonitores(registro);
        this.interfaz=interfaz; 
        this.registro=registro; 
    }

    public void descanso(Campista campista){
        interfaz.comprobarPausa();
        registro.escribir("El campista "+campista.getID()+" descansa en la zona común");
        campistas.meterCampista(campista);
        actualizarInterfaz(); 
        interfaz.comprobarPausa();
        try{
            Thread.sleep((int)Math.floor(Math.random()*(4000-2000+1)+4000));
        }
        catch(InterruptedException ie){
            registro.escribir("Error mientras el campista "+campista.getID()+" descansa");
        }
        interfaz.comprobarPausa();
        campistas.sacarCampista(campista); 
        actualizarInterfaz(); 
        registro.escribir("El campista "+campista.getID()+" sale de la zona común");
    }
    
    public void descanso(Monitor monitor){
        interfaz.comprobarPausa();
        registro.escribir("El monitor "+monitor.getID()+" descansa en la zona común");
        monitores.meterMonitor(monitor);
        actualizarInterfaz(); 
        interfaz.comprobarPausa();
        try{
            Thread.sleep((int)Math.floor(Math.random()*(2000-1000+1)+2000));
        }
        catch(InterruptedException ie){
            registro.escribir("Error mientras el monitor "+monitor.getID()+" descansa");
        }
        interfaz.comprobarPausa();
        monitores.sacarMonitor(monitor); 
        actualizarInterfaz(); 
        registro.escribir("El monitor "+monitor.getID()+" sale de la zona común");
    }
    
    public void actualizarInterfaz(){
        interfaz.setTextoZonaDescansoCampistas(campistas.getIntegrantes());
        interfaz.setTextoZonaDescansoMonitores(monitores.getIntegrantes());
    }
}
