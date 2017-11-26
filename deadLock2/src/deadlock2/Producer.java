/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadlock2;

import java.util.logging.Level;
import java.util.logging.Logger;
import static deadlock2.Controller.*;

/**
 *
 * @author Simon
 */
public class Producer extends Thread{
    static Boolean holding = false;
    @Override
    public void run(){
        while (true) {
            try {
                //System.out.println("Prod:ich bin wach");
                //item = produce();

                
                sem.acquire();
                holding = true;
                if (getCount() >= N) {
                    //System.out.println("Prod:Ich gehe schlafen");
                    try {
                        holding = false;
                        sem.release();
                        synchronized(this){ wait(); }
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if(holding){
                    sem.release();
                    holding = false;
                }
                
                
                sem.acquire();
                insert_item();
                sem.release();
                
                sem.acquire();
                holding = true;
                if (getCount() >= 1) {
                    holding = false;
                    sem.release();
                    synchronized(consThread){ consThread.notify();}
                }
                if(holding){
                    sem.release();
                    holding = false;
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
