package parte1;

public class Campista extends Thread{
    private String identificador; 
    private int[] actividades; 
    private Campamento campamento; 
    
    public Campista(int identificador, Campamento campamento){
        this.identificador="N"+identificador; 
        this.campamento=campamento; 
        this.actividades=new int[]{0, 0, 0}; 
    }
    
    public int getSogas(){
        return actividades[0]; 
    }
    
    public void incSogas(){
        actividades[0]++; 
        campamento.incrementarEstadisticas(Integer.parseInt(identificador.substring(1))); 
    }
    
    public int getTirolinas(){
        return actividades[1]; 
    }
    
    public void incTirolinas(){
        actividades[1]++; 
        campamento.incrementarEstadisticas(Integer.parseInt(identificador.substring(1))); 
    }
    
    public int getMeriendas(){
        return actividades[2]; 
    }
    
    public void incMeriendas(){
        actividades[2]++; 
        campamento.incrementarEstadisticas(Integer.parseInt(identificador.substring(1))); 
    }
    
    public int getActividades(){
        int act=0; 
        for(int i=0; i<3; i++){
            act+=actividades[i]; 
        }
        return act; 
    }
    
    public String getID(){
        return identificador; 
    }
    
    public void llegarCampamento(){
        //Simula la espera de entre 1 y 3 segundos
        try{
            Thread.sleep((int)Math.floor(Math.random()*(3000-1000+1)+3000)); 
        }
        catch(InterruptedException e){
            campamento.escribirRegistro("El campista "+identificador+" se ha perdido de camino al campamento");
        }
        finally{
            campamento.escribirRegistro("El campista "+identificador+" ha llegado al campamento");
        }
    }
    
    public void merendar(){
        //Simula la merienda de un campista 
        campamento.escribirRegistro("El campista " + identificador + " se dispone a merendar");
        Bandeja b=campamento.merendar(this);
        
        try{
            Thread.sleep(7000);
        } 
        catch(InterruptedException ie){
            campamento.escribirRegistro("Error mientras el campista " + identificador + "merienda");
        } 
        finally{
            campamento.finMerendar(this, b);
        }

        incMeriendas();
    }
    
    public void tirarse(){
        //Simula la actividad de tirolina de los campistas
        
        campamento.irTirolina(this);
        try{
            Thread.sleep(3500); 
        }
        catch(InterruptedException ie){
            campamento.escribirRegistro("Error mientras el campista "+identificador+" se tiraba por la tirolina");
        }
        finally{
            campamento.irseTirolina(this);
            incTirolinas(); 
        }
    }
    
    public boolean competir(){
        //Simula la actividad de competir en la soga
        
        if(campamento.entrarSoga(this)){ 
            try{
                Thread.sleep(7000); 
            }
            catch(InterruptedException ie){
                campamento.escribirRegistro("Error mientras el campista "+identificador+" tira de la soga");
            }
            
            //Aviso que he terminado de jugar
            campamento.avisoSoga();
            
            //Aviso que estoy listo para conocer los resultados
            campamento.avisoSoga();
            
            if(campamento.esGanadorSoga(this)){
                incSogas(); 
                incSogas();
            }
            else{
                incSogas(); 
            }
            
            //Aviso que he terminado y me voy
            campamento.avisoSoga();
            return true; 
        }
        else{
            return false; 
        }
    }
    
    public void realizarActividad(){
        //Simula el esquema general de las actividades
        int actividad=(int)Math.floor(Math.random()*3);
        switch(actividad){
            case 0:{
                if(getSogas()>=3 || getTirolinas()>=3){
                    merendar(); 
                    campamento.descanso(this); 
                    break; 
                }
                else{
                    campamento.escribirRegistro("El campista "+identificador+" no puede merendar porque no ha realizado suficientes actividades, se dispone a jugar");
                }
            }
            case 1:{
                campamento.escribirRegistro("El campista "+identificador+" elige competir en la soga");
                if(!competir()){
                    campamento.escribirRegistro("El campista "+identificador+" no ha podido competir en la soga porque no hay hueco");
                }
                else{
                    campamento.salirSoga(this);
                    campamento.descanso(this); 
                }
                break; 
            }
            case 2:{
                campamento.escribirRegistro("El campista "+identificador+" elige tirarse por la tirolina");
                tirarse(); 
                incTirolinas(); 
                campamento.descanso(this); 
                break;
            }
        }
    }
    
    @Override
    public void run(){
        llegarCampamento(); 
        campamento.entrar(this);
        while(getActividades()<15){
            realizarActividad(); 
        }
        campamento.marchar(this); 
    }
}
