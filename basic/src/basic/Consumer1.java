package basic;

import static basic.Controller1.*;
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
public class Consumer1 extends Thread {
    
    @Override
        public void run(){
        while (true) {
            System.out.println("Cons:Ich bin wach");
            if (count <= 0) {
                System.out.println("Cons:Ich gehe schlafen");
                try {
                    synchronized(this){ wait(); }
                } catch (InterruptedException ex) {
                    Logger.getLogger(Consumer1.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            remove();
            if (count >= N-1) {
                synchronized(prodThread){ prodThread.notify();}
            }
        } 
    }
}
