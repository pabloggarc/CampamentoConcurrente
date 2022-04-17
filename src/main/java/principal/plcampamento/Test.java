package principal.plcampamento;

import java.util.concurrent.CountDownLatch;

public class Test {

    public static void main(String[] args){
        CountDownLatch b1=new CountDownLatch(1); 
        CountDownLatch b2=new CountDownLatch(1); 
        Campamento campamento=new Campamento(new Tirolina(), new Soga(), new Merendero(), new ZonaComun(), b1, b2); 
        for(int i=1; i<10; i++){
            Campista c=new Campista(i, campamento); 
            c.start(); 
        }
        for(int j=1; j<5; j++){
            Monitor m=new Monitor(j, campamento, b1, b2); 
            m.start(); 
        }
    }
    
}
