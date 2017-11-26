package deadlock3;

import static deadlock3.Controller.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import deadlock3.Controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Simon
 */
public class Consumer extends Thread {
  @Override
    public void run(){
        while (true) {
            //System.out.println("Cons:Ich bin wach");
            
            synchronized(Controller.class){             
                if (getCount() <= 0) {
                    //System.out.println("Cons:Ich gehe schlafen");
                    try {
                        synchronized(this){ wait(); }
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }                
            };
            

            synchronized(Controller.class){             
                Controller.remove();         
            };
            
            synchronized(Controller.class){             
                if (getCount() >= N-1) {
                    synchronized(prodThread){ prodThread.notify();}
                }          
            };
            
            
            

        }
    }
    
    
    
}
