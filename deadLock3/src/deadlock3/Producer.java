/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadlock3;

import java.util.logging.Level;
import java.util.logging.Logger;
import static deadlock3.Controller.*;

/**
 *
 * @author Simon
 */
public class Producer extends Thread{
    @Override
    public void run(){
        Boolean sleep = false,wakeup = false;
        while (true) {
            //System.out.println("Prod:ich bin wach");
            //item = produce();
            
            synchronized(Controller.class){             
                if (getCount() >= N) {
                    //System.out.println("Prod:Ich gehe schlafen");
                    sleep = true;
                }      
            };
            
            if(sleep){
                try {
                    synchronized(this){ wait(); }
                } catch (InterruptedException ex) {
                    Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            sleep = false;
            synchronized(Controller.class){             
                insert_item();        
            };

            synchronized(Controller.class){             
                if (getCount() >= 1) {
                    wakeup = true;
                    
                }       
            };
            
            if(wakeup)
                synchronized(consThread){ consThread.notify();}
            
            wakeup = true;
        }
    }
}
}
