/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basic;

import static basic.Controller1.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Simon
 */
public class Producer1 extends Thread{
    @Override
    public void run(){
        while (true) {
            System.out.println("Prod:ich bin wach");
            //item = produce();
            if (count >= N) {
                System.out.println("Prod:Ich gehe schlafen");
                try {
                    synchronized(this){ wait(); }
                } catch (InterruptedException ex) {
                    Logger.getLogger(Producer1.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            insert_item();
            if (count >= 1) {
               synchronized(consThread){ consThread.notify();}
            }
                


        }
    }
}
