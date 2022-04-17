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
    
    public Campamento(
            Tirolina tirolina, Soga soga, Merendero merendero, ZonaComun zonaComun, 
            CountDownLatch colaMonitoresA, CountDownLatch colaMonitoresB
            ){
        this.entradaA=new Entrada(); 
        this.entradaB=new Entrada();
        this.aforo=new Semaphore(5, true);
        this.ultimaEntrada=new AtomicInteger(0); 
        this.tirolina=tirolina; 
        this.soga=soga; 
        this.merendero=merendero; 
        this.zonaComun=zonaComun;
        this.colaMonitoresA=colaMonitoresA; 
        this.colaMonitoresB=colaMonitoresB; 
        this.ocupacionesMonitores=new AtomicIntegerArray(3); 
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
    
    public synchronized void prepararMeriendas(Monitor monitor){
        //Un monitor entra al merendero
        
        ocupacionesMonitores.getAndIncrement(0); 
        merendero.entrar(monitor);
    }
    
    public synchronized void finPrepararMeriendas(Monitor monitor){
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
}
