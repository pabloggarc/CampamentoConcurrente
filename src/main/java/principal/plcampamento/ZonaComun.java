package principal.plcampamento;

public class ZonaComun {
    private ListaCampistas campistas; 
    private ListaMonitores monitores; 
    private Interfaz interfaz; 
    
    public ZonaComun(Interfaz interfaz){
        this.campistas=new ListaCampistas(); 
        this.monitores=new ListaMonitores();
        this.interfaz=interfaz; 
    }

    public void descanso(Campista campista){
        interfaz.comprobarPausa();
        System.out.println("El campista "+campista.getID()+" descansa en la zona común");
        campistas.meterCampista(campista);
        actualizarInterfaz(); 
        interfaz.comprobarPausa();
        try{
            Thread.sleep((int)Math.floor(Math.random()*(4000-2000+1)+4000));
        }
        catch(InterruptedException ie){
            System.out.println("Error mientras el campista "+campista.getID()+" descansa");
        }
        interfaz.comprobarPausa();
        campistas.sacarCampista(campista); 
        actualizarInterfaz(); 
        System.out.println("El campista "+campista.getID()+" sale de la zona común");
    }
    
    public void descanso(Monitor monitor){
        interfaz.comprobarPausa();
        System.out.println("El monitor "+monitor.getID()+" descansa en la zona común");
        monitores.meterMonitor(monitor);
        actualizarInterfaz(); 
        interfaz.comprobarPausa();
        try{
            Thread.sleep((int)Math.floor(Math.random()*(2000-1000+1)+2000));
        }
        catch(InterruptedException ie){
            System.out.println("Error mientras el monitor "+monitor.getID()+" descansa");
        }
        interfaz.comprobarPausa();
        monitores.sacarMonitor(monitor); 
        actualizarInterfaz(); 
        System.out.println("El monitor "+monitor.getID()+" sale de la zona común");
    }
    
    public void actualizarInterfaz(){
        interfaz.setTextoZonaDescansoCampistas(campistas.getIntegrantes());
        interfaz.setTextoZonaDescansoMonitores(monitores.getIntegrantes());
    }
}
