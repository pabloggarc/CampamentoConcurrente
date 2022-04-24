package principal.plcampamento;

import java.util.concurrent.CountDownLatch;

public class Monitor extends Thread{
    private String identificador; 
    private Campamento campamento;
    private CountDownLatch colaEntradaA; 
    private CountDownLatch colaEntradaB; 
    
    public Monitor(int identificador, Campamento campamento, CountDownLatch colaEntradaA, CountDownLatch colaEntradaB){
        this.identificador="M"+identificador; 
        this.campamento=campamento; 
        this.colaEntradaA=colaEntradaA; 
        this.colaEntradaB=colaEntradaB; 
    }
    
    public String getID(){
        return identificador; 
    }
    
    public void entrarCampamento(){
        /* Simula la entrada del monitor al campamento. Un monitor llega a una puerta
        y en caso de estar cerrada la abre si no hay nadie abriendola, si esta cerrada y alguien 
        la esta abriendo le espera, y en caso contrario pasa. 
        
        SUPOSICION 1: los monitores con ID par entran por la puerta A, y los impares por la B. 
        
        SUPOSICION 2: en una misma puerta, los monitores pasan por un lado y los campistas por otro, 
        porque si compartieran cola y llega un campista antes que un monitor se produciria deadlock
        al estar un campista esperando que un monitor abra, y el monitor que un campista se quite. 
        */
        
        int entrada=Character.getNumericValue(identificador.charAt(1))%2; 
        if(entrada==0){
            System.out.println("El monitor "+identificador+" se sitúa en la entrada A");
            if(campamento.getCuentaMonitoresEA()==1){
                campamento.contarMonitorEA();
                try{
                    Thread.sleep((int)Math.floor(Math.random()*(1000-500+1)+1000)); 
                }
                catch(InterruptedException e){
                    System.out.println("Error al abrir la puerta A");
                }
                campamento.abrirEntrada('A', this);
            }
            else{
                try{
                    colaEntradaA.await();
                } 
                catch(InterruptedException ex){
                    System.out.println("Error al esperar que otro monitor abra la entrada A");;
                }
            }
            System.out.println("El monitor "+identificador+" ha entrado (A) dentro del campamento ");
        }
        else if(entrada==1){
            System.out.println("El monitor "+identificador+" se sitúa en la entrada B");
            if(campamento.getCuentaMonitoresEB()==1){
                campamento.contarMonitorEB();
                try{
                    Thread.sleep((int)Math.floor(Math.random()*(1000-500+1)+1000)); 
                }
                catch(InterruptedException e){
                    System.out.println("Error al abrir la puerta B");
                }
                campamento.abrirEntrada('B', this);
            }
            else{
                try{
                    colaEntradaB.await();
                }
                catch(InterruptedException ex){
                    System.out.println("Error al esperar que otro monitor abra la entrada B");
                }
            }
            System.out.println("El monitor "+identificador+" ha entrado (B) dentro del campamento ");
        }
        else{
            System.out.println("Error: el monitor "+identificador+" intenta entrar por una entrada inexistente");
        }
    }
    
    public void cocinar(){
        //Simula el trabajo de cocinero de un monitor
        
        campamento.prepararMeriendas(this);
        
        int bandejasPreparadas=0; 
        while(bandejasPreparadas<10){
            Bandeja b=campamento.prepararMeriendaA(this); 
            try{
                Thread.sleep((int)Math.floor(Math.random()*(5000-3000+1)+5000)); 
            }
            catch(InterruptedException ie){
                System.out.println("Error mientras se preparaba la bandeja "+b.getID());
            }
            finally{
                campamento.prepararMeriendaB(this, b);
                bandejasPreparadas++; 
            }
        }
        
        campamento.finPrepararMeriendas(this);
    }
    
    public void tirarCampistas(){
        //Simula el trabajo de preparar campistas y lanzarlos por la tirolina
        
        campamento.irTirolina(this);
        System.out.println("El monitor "+identificador+" llega a la tirolina");
        for(int i=0; i<10; i++){
            campamento.avisarCampista(this);
            try{
                System.out.println("El monitor "+identificador+" prepara a un campista");
                Thread.sleep(1000); 
            }
            catch(InterruptedException ie){
                System.out.println("Error mientras el monitor "+identificador+" prepara a un campista en la tirolina");
            }
            System.out.println("El monitor "+identificador+" va a lanzar a un campista");
            campamento.avisarCampista(this);
        }
        campamento.irseTirolina(this);
    }
    
    @Override
    public void run(){
        try{
            Thread.sleep((int)Math.floor(Math.random()*(5000-500+1)+5000)); 
        }
        catch(InterruptedException e){}
        finally{
            entrarCampamento(); 
        }
        
        if(campamento.consultarCocineros()<2){
            cocinar(); 
        }
        else{
            if(campamento.consultarTirolina()<1){
                tirarCampistas(); 
            }
            else{
                //Elegir otro trabajo
            }
        }
        System.out.println("El monitor "+identificador+" se va del campamento");
    }
}