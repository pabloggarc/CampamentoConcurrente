package principal.plcampamento;

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
    }
    
    public int getTirolinas(){
        return actividades[1]; 
    }
    
    public void incTirolinas(){
        actividades[1]++; 
    }
    
    public int getMeriendas(){
        return actividades[2]; 
    }
    
    public void incMeriendas(){
        actividades[2]++; 
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
            System.out.println("El campista "+identificador+" se ha perdido de camino al campamento");
        }
        finally{
            System.out.println("El campista "+identificador+" ha llegado al campamento");
        }
    }
    
    public void merendar(){
        //Simula la merienda de un campista 
        System.out.println("El campista " + identificador + " se dispone a merendar");
        Bandeja b=campamento.merendar(this);
        
        try{
            Thread.sleep(7000);
        } 
        catch(InterruptedException ie){
            System.out.println("Error mientras el campista " + identificador + "merienda");
        } 
        finally{
            campamento.finMerendar(this, b);
        }

        incMeriendas();
    }
    
    public void realizarActividad(){
        //Simula el esquema general de las actividades
        int actividad=(int)Math.floor(Math.random()*3);
        switch(actividad){
            case 0:{
                if(getSogas()>=3 || getTirolinas()>=3){
                    merendar(); 
                    break; 
                }
                else{
                    System.out.println("El campista "+identificador+" no puede merendar porque no ha realizado suficientes actividades, se dispone a jugar");
                }
            }
            case 1:{
                //TODO campamento.soga.jugar(this)
                System.out.println("El campista "+identificador+" elige jugar a la soga");
                incSogas(); 
                break; 
            }
            case 2:{
                //TODO campamento.tirolina.jugar(this)
                System.out.println("El campista "+identificador+" elige jugar a la tirolina");
                incTirolinas(); 
                break;
            }
        }
    }
    
    public void descanso(){
        System.out.println("El campista "+identificador+" ha terminado una actividad y va a descansar en la zona común");
        //TODO campamento.zonaComun.descansar(this)
        try{
            Thread.sleep((int)Math.floor(Math.random()*(4000-2000+1)+4000));
        }
        catch(InterruptedException e){
            System.out.println("El campista "+identificador+" ha tenido problemas descansando");
        }
        finally{
            //TODO campamento.zonaComun.finDescanso(this)
            System.out.println("El campista "+identificador+" ha terminado de descansar");
        }
    }
    
    public void abandonarCampamento(){
        campamento.marchar(this); 
    }
    
    @Override
    public void run(){
        llegarCampamento(); 
        campamento.entrar(this);
        while(getActividades()<3){
            realizarActividad(); 
            descanso(); 
        }
        realizarActividad(); 
        abandonarCampamento(); 
    }
}
