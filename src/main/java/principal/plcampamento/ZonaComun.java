package principal.plcampamento;

public class ZonaComun {
    private ListaCampistas campistas; 
    private ListaMonitores monitores; 
    
    public ZonaComun(){
        this.campistas=new ListaCampistas(); 
        this.monitores=new ListaMonitores(); 
    }

    public void descanso(Campista campista){
        System.out.println("El campista "+campista.getID()+" descansa en la zona común");
        campistas.meterCampista(campista);
        try{
            Thread.sleep((int)Math.floor(Math.random()*(4000-2000+1)+4000));
        }
        catch(InterruptedException ie){
            System.out.println("Error mientras el campista "+campista.getID()+" descansa");
        }
        campistas.sacarCampista(campista); 
        System.out.println("El campista "+campista.getID()+" sale de la zona común");
    }
    
    public void descanso(Monitor monitor){
        System.out.println("El monitor "+monitor.getID()+" descansa en la zona común");
        monitores.meterMonitor(monitor);
        try{
            Thread.sleep((int)Math.floor(Math.random()*(2000-1000+1)+2000));
        }
        catch(InterruptedException ie){
            System.out.println("Error mientras el monitor "+monitor.getID()+" descansa");
        }
        monitores.sacarMonitor(monitor); 
        System.out.println("El monitor "+monitor.getID()+" sale de la zona común");
    }
}
