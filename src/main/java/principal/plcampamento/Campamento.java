package principal.plcampamento;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class Campamento {
    private Entrada entradaA; 
    private Entrada entradaB; 
    private Semaphore aforo;
    private AtomicInteger ultimaEntrada; 
    private Tirolina tirolina; 
    private Soga soga; 
    private Merendero merendero; 
    private ZonaComun zonaComun; 
    private CountDownLatch colaMonitoresA; 
    private CountDownLatch colaMonitoresB; 
    private AtomicIntegerArray ocupacionesMonitores; 
    private Interfaz interfaz; 
    
    public Campamento(Interfaz interfaz){
        this.entradaA=new Entrada(interfaz, true); 
        this.entradaB=new Entrada(interfaz, false);
        this.aforo=new Semaphore(50, true);
        this.ultimaEntrada=new AtomicInteger(0); 
        this.tirolina=new Tirolina(interfaz); 
        this.soga=new Soga(interfaz); 
        this.merendero=new Merendero(interfaz); 
        this.zonaComun=new ZonaComun(interfaz);
        this.colaMonitoresA=new CountDownLatch(1);
        this.colaMonitoresB=new CountDownLatch(1);
        this.ocupacionesMonitores=new AtomicIntegerArray(3); 
        this.interfaz=interfaz; 
    }
    
    public void entrar(Campista campista){
        /* Simula la entrada de los campistas al campamento
        SUPOSICION: entran intercalados solo cuando esta lleno (si hay colas 
        fuera porque las puertas estan cerradas, el camapamento esta vacio y
        entraran de forma aleatoria respetando el orden de llegada) */
        
        int entrada=(int)Math.floor(Math.random()*2);
        if(aforo.hasQueuedThreads()){
            entrada=ultimaEntrada.get()%2;
        }
        ultimaEntrada.set(entrada+1%2);
        
        if(entrada==0){
            System.out.println("El campista "+campista.getID()+" se sitúa en la entrada A");
            entradaA.pasarPuerta(campista);
            try{
                aforo.acquire();
                entradaA.borrarApuntado(campista);
                System.out.println("El campista "+campista.getID()+" ha entrado (A) dentro del campamento ");
            }
            catch(InterruptedException e){
                System.out.println("Error: el campista "+campista.getID()+" no pudo entrar al campamento");
            }
        }
        else{
            System.out.println("El campista "+campista.getID()+" se sitúa en la entrada B");
            entradaB.pasarPuerta(campista);
            try{
                aforo.acquire();
                entradaB.borrarApuntado(campista);
                System.out.println("El campista "+campista.getID()+" ha entrado (B) dentro del campamento");
            }
            catch(InterruptedException e){
                System.out.println("Error: el campista "+campista.getID()+" no pudo entrar al campamento");
            }
        }
    }
    
    public void marchar(Campista campista){
        //Expulsa a un campista del campamento
        
        System.out.println("El campista "+campista.getID()+" abandona el campamento");
        aforo.release();
    }
    
    public void contarMonitorEA(){
        //Almacena de forma atomica que ya ha pasado un monitor por la puerta A
        
        entradaA.contar();
    }
    
    public void contarMonitorEB(){
        //Almacena de forma atomica que ya ha pasado un monitor por la puerta B
        
        entradaB.contar();
    }
    
    public int getCuentaMonitoresEA(){
        //Comprueba de forma atomica si ha pasado algun monitor por la puerta A
        
        return entradaA.consultarContador(); 
    }
    
    public int getCuentaMonitoresEB(){
        //Comprueba de forma atomica si ha pasado algun monitor por la puerta B
        
        return entradaB.consultarContador(); 
    }
    
    public void contarColaA(){
        try{
            colaMonitoresA.await();
        } 
        catch(InterruptedException ex){
            System.out.println("Error al esperar que otro monitor abra la entrada A");;
        }
    }
    
    public void contarColaB(){
        try{
            colaMonitoresB.await();
        } 
        catch(InterruptedException ex){
            System.out.println("Error al esperar que otro monitor abra la entrada B");
        }
    }
    
    public void abrirEntrada(char entrada, Monitor monitor){
        //Dada una entrada y un monitor la abre
        
        if(entrada=='A'){
            System.out.println("El monitor "+monitor.getID()+" ha abierto la entrada A");
            entradaA.abrir();
            colaMonitoresA.countDown();
        }
        else if(entrada=='B'){
            System.out.println("El monitor "+monitor.getID()+" ha abierto la entrada B");
            entradaB.abrir();
            colaMonitoresB.countDown();
        }
        else{
            System.out.println("Un monitor ha elegido abrir una entrada inválida");
        }
    }
    
    public Bandeja merendar(Campista campista){
        //Dado un campista lo lleva al merendero y coge una bandeja si hay y si no espera a que haya
        
        merendero.entrar(campista);
        return merendero.cogerBandeja(campista); 
    }
    
    public void finMerendar(Campista campista, Bandeja bandeja){
        //Una vez se ha comido la merienda, devuelve la bandeja y se va del merendero
        
        merendero.dejarBandeja(campista, bandeja);
        merendero.sacar(campista);
    }
    
    public void prepararMeriendas(Monitor monitor){
        //Un monitor entra al merendero
        
        ocupacionesMonitores.getAndIncrement(0); 
        merendero.entrar(monitor);
    }
    
    public void finPrepararMeriendas(Monitor monitor){
        //Un monitor se marcha del merendero
        
        ocupacionesMonitores.getAndDecrement(0); 
        merendero.sacar(monitor);
    }
    
    public Bandeja prepararMeriendaA(Monitor monitor){
        //Un monitor coge una bandeja para limpiarla y poner una merienda
        
        return merendero.cogerBandeja(monitor); 
    }
    
    public void prepararMeriendaB(Monitor monitor, Bandeja bandeja){
        //Un monitor deja una bandeja limpia y con comida
        
        merendero.dejarBandeja(monitor, bandeja);
    }
    
    public int consultarCocineros(){
        //Devuelve la cantidad de monitores que hay trabajando como cocineros
        
        return ocupacionesMonitores.get(0); 
    }
    
    public void irTirolina(Campista campista){
        //Dado un campista lo lleva a la tirolina. Si no hay monitor, o cola se tira, si no se pone a la cola
        
        tirolina.entrarTirolina(campista);
    }
    
    public void irseTirolina(Campista campista){
        //Dado un campista lo expulsa de la tirolina (el campista ya se ha tirado)
        
        tirolina.bajarseTirolina(campista);
    }
    
    public void irTirolina(Monitor monitor){
        //Dado un monitor se pone como responsable de la actividad de tirolina
        
        ocupacionesMonitores.getAndIncrement(1); 
        tirolina.entrarTirolina(monitor);
    }
    
    public void irseTirolina(Monitor monitor){
        //Dado un monitor abandona el trabajo de la tirolina
        
        ocupacionesMonitores.getAndDecrement(1); 
        tirolina.salirTirolina(monitor);
    }
    
    public void avisarCampista(Monitor monitor){
        //Dado un monitor dice que ha llegado a la tirolina o que ya ha preparado a un campista
        
        tirolina.avisarCampista(monitor);
    }
    
    public int consultarTirolina(){
        //Devuelve la cantidad de monitores que hay trabajando en la tirolina
        
        return ocupacionesMonitores.get(1); 
    }
    
    public boolean entrarSoga(Campista campista){
        //Dado un campista, mete a un campista en la actividad de soga en caso de poder. Devuelve que ha sucedido
        
        return soga.entrarSoga(campista); 
    }
    
    public void entrarSoga(Monitor monitor){
        //Dado un monitor, lo pone a trabajar en la actividad de soga
        
        soga.entrarSoga(monitor); 
    }
    
    public void avisoSoga(){
        //Aviso de sincronizacion en las diferentes etapas de la actividad de soga
        
        soga.avisarSoga();
    }
    
    public boolean esGanadorSoga(Campista campista){
        //Dado un campista comprueba si ha ganado en en la actividad de soga
        
        return soga.haGanado(campista); 
    }
    
    public void hacerEquipos(){
        //Simula el trabajo que hace el monitor en la actividad de soga de hacer los equipos
        
        soga.hacerEquipos();
    }
    
    public ListaCampistas getEquipo(int n){
        //Devuelve los participantes de un equipo en la actividad de soga
        
        if(n==0){
            return soga.getEquipoA(); 
        }
        else{
            return soga.getEquipoB(); 
        }
    }
    
    public void prepararSoga(){
        //"Resetea" los datos de la partida de soga
        
        soga.limpiarSoga();
    }
    
    public void anunciarGanador(boolean b){
        //El monitor comunica si el ganador es el equipo A
        
        soga.setGanador(b); 
    }
    
    public void salirSoga(Campista campista){
        //Simula el abandono de un campista de la actividad de soga
        
        soga.salirSoga(campista);
    }
    
    public void salirSoga(Monitor monitor){
        //Simula el abandono de un monitor de la actividad de soga
        
        soga.salirSoga(monitor);
    }
    
    public void descanso(Campista campista){
        zonaComun.descanso(campista);
    }
    
    public void descanso(Monitor monitor){
        zonaComun.descanso(monitor);
    }
    
    public void actualziarInterfazEquiposSoga(){
        soga.actualizarInterfazEquipos();
    }
    
    public void actualizarInterfazZonaComun(String campistas, String monitores){
        if(campistas!=null){
            interfaz.setTextoZonaDescansoCampistas(campistas);
        }
        if(monitores!=null){
            interfaz.setTextoZonaDescansoMonitores(monitores);
        }
    }
    
    public void actualizarInterfazGandoresSoga(){
        soga.actualizarInterfazGanadores();
    }
}
