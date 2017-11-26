package deadlock2;

import static deadlock2.Controller.*;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    static Boolean holding = false;
  @Override
    public void run(){
        while (true) {
            try {
                //System.out.println("Cons:Ich bin wach");
                
                sem.acquire();
                holding = true;
                if (getCount() <= 0) {
                    //System.out.println("Cons:Ich gehe schlafen");
                    try {
                        holding = false;
                        sem.release();
                        synchronized(this){ wait(); }
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if(holding){
                    sem.release();
                    holding = false;
                }
                
                sem.acquire();
                Controller.remove();
                sem.release();
                
                
                sem.acquire();
                holding = true;
                if (count >= N-1) {
                    holding = false;
                    sem.release();
                    synchronized(prodThread){ prodThread.notify();}
                }
                if(holding)
                {
                    holding = false;
                    sem.release();
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    
}
