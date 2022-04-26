package principal.plcampamento;

public class Monitor extends Thread{
    private String identificador; 
    private Campamento campamento;
    
    public Monitor(int identificador, Campamento campamento){
        this.identificador="M"+identificador; 
        this.campamento=campamento; 
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
                campamento.contarColaA(); 
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
                campamento.contarColaB();
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
    
    public void arbitrarSoga(){
        campamento.entrarSoga(this);
        for(int i=0; i<10; i++){
            //Aviso que el monitor esta listo para arbitrar
            System.out.println("El monitor "+identificador+" espera campistas en la soga");
            campamento.avisoSoga();
            campamento.hacerEquipos();
            campamento.actualziarInterfazEquiposSoga();
            System.out.println("\t------EQUIPOS SOGA------\t\n"
                    +"EQUIPO A: "+campamento.getEquipo(0).getIntegrantes()+"\n"
                    +"EQUIPO B: "+campamento.getEquipo(1).getIntegrantes()+
                    "\n\t------------------------\t\n");
            
            //Aviso que los equipos estan listos y se puede empezar
            campamento.avisoSoga();
            
            //Mientras juegan se decide el ganador y se espera a que acaben de jugar
            int equipoGanador=(int)(Math.floor(Math.random()*2)); 
            String victoria;
            if(equipoGanador==0){
                victoria="VICTORIA del equipo A: "; 
            }
            else{
                victoria="VICTORIA del equipo B: "; 
            }
            victoria+=campamento.getEquipo(equipoGanador).getIntegrantes(); 
            //Espero a que terminen de jugar
            campamento.avisoSoga();
            campamento.actualizarInterfazGandoresSoga();
            
            //Cuando terminan de jugar se espera a que el monitor anuncie el ganador
            System.out.println(victoria);
            campamento.anunciarGanador(equipoGanador==0);
            campamento.avisoSoga();
            
            //Se espera a que todos los campistas conozcan el ganador para marcharse
            campamento.avisoSoga();
            campamento.prepararSoga();
        }
        campamento.salirSoga(this);
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
        
        int trabajo; 
        
        if(campamento.consultarCocineros()<2){
            trabajo=0; 
        }
        else{
            if(campamento.consultarTirolina()<1){
                trabajo=1; 
            }
            else{
                trabajo=2; 
            }
        }
        
        while(true){
            switch(trabajo){
                case 0:{
                    cocinar(); 
                    break; 
                }
                case 1:{
                    tirarCampistas(); 
                    break; 
                }
                case 2:{
                    arbitrarSoga(); 
                    break; 
                }
            }
            campamento.descanso(this); 
        }
    }
}