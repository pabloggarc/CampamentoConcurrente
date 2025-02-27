package parte1;

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
        */
        campamento.comprobarPausa();
        int entrada=Character.getNumericValue(identificador.charAt(1))%2; 
        if(entrada==0){
            campamento.escribirRegistro("El monitor "+identificador+" se sitúa en la entrada A");
            if(campamento.getCuentaMonitoresEA(this)==1){
                campamento.contarMonitorEA();
                try{
                    Thread.sleep((int)Math.floor(Math.random()*(1000-500+1)+1000)); 
                }
                catch(InterruptedException e){
                    campamento.escribirRegistro("Error al abrir la puerta A");
                }
                campamento.comprobarPausa();
                campamento.abrirEntrada('A', this);
            }
            else{
                campamento.comprobarPausa();
                campamento.contarColaA(this); 
            }
            campamento.escribirRegistro("El monitor "+identificador+" ha entrado (A) dentro del campamento ");
        }
        else if(entrada==1){
            campamento.escribirRegistro("El monitor "+identificador+" se sitúa en la entrada B");
            if(campamento.getCuentaMonitoresEB(this)==1){
                campamento.contarMonitorEB();
                campamento.comprobarPausa();
                try{
                    Thread.sleep((int)Math.floor(Math.random()*(1000-500+1)+1000)); 
                }
                catch(InterruptedException e){
                    campamento.escribirRegistro("Error al abrir la puerta B");
                }
                campamento.comprobarPausa();
                campamento.abrirEntrada('B', this);
            }
            else{
                campamento.comprobarPausa();
                campamento.contarColaB(this);
            }
            campamento.escribirRegistro("El monitor "+identificador+" ha entrado (B) dentro del campamento ");
        }
        else{
            campamento.escribirRegistro("Error: el monitor "+identificador+" intenta entrar por una entrada inexistente");
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
                campamento.escribirRegistro("Error mientras se preparaba la bandeja "+b.getID());
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
        
        campamento.comprobarPausa();
        campamento.irTirolina(this);
        campamento.escribirRegistro("El monitor "+identificador+" llega a la tirolina");
        for(int i=0; i<10; i++){
            campamento.comprobarPausa();
            campamento.avisarCampista(this);
            try{
                campamento.escribirRegistro("El monitor "+identificador+" prepara a un campista");
                Thread.sleep(1000); 
            }
            catch(InterruptedException ie){
                campamento.escribirRegistro("Error mientras el monitor "+identificador+" prepara a un campista en la tirolina");
            }
            campamento.comprobarPausa();
            campamento.escribirRegistro("El monitor "+identificador+" va a lanzar a un campista");
            campamento.avisarCampista(this);
        }
        campamento.comprobarPausa();
        campamento.irseTirolina(this);
    }
    
    public void arbitrarSoga(){
        campamento.comprobarPausa();
        campamento.entrarSoga(this);
        for(int i=0; i<10; i++){
            campamento.comprobarPausa();
            //Aviso que el monitor esta listo para arbitrar
            campamento.escribirRegistro("El monitor "+identificador+" espera campistas en la soga");
            campamento.avisoSoga();
            campamento.comprobarPausa();
            campamento.hacerEquipos();
            campamento.actualziarInterfazEquiposSoga();
            campamento.escribirRegistro("\t------EQUIPOS SOGA------\t\n"
                    +"EQUIPO A: "+campamento.getEquipo(0).getIntegrantes()+"\n"
                    +"EQUIPO B: "+campamento.getEquipo(1).getIntegrantes()+
                    "\n\t------------------------\t\n");
            
            //Aviso que los equipos estan listos y se puede empezar
            campamento.avisoSoga();
            campamento.comprobarPausa();
            
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
            campamento.comprobarPausa();
            campamento.actualizarInterfazGandoresSoga();
            
            //Cuando terminan de jugar se espera a que el monitor anuncie el ganador
            campamento.comprobarPausa();
            campamento.escribirRegistro(victoria);
            campamento.anunciarGanador(equipoGanador==0);
            campamento.avisoSoga();
            
            //Se espera a que todos los campistas conozcan el ganador para marcharse
            campamento.avisoSoga();
            campamento.comprobarPausa();
            campamento.prepararSoga();
        }
        campamento.comprobarPausa();
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