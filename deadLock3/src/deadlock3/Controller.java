/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadlock3;

import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 *
 * @author Simon
 */
public class Controller {

    /**
     * @param args the command line arguments
     */
    public static final int N = 10;
    public static int count = 10;
    public static Producer prodThread = new Producer();
    public static Consumer consThread = new Consumer();
    public static void main(String[] args){
        prodThread.start();
        //prodThread.run();
        consThread.start();
        //consThread.run();
       
    }

    public static void insert_item(){
        count++;
        System.out.println("+   " + count);
        if(count > N)
            System.out.println("Error");
    }
    public static void remove(){
        count--;
        System.out.println("-   " + count);
        if(count < 0)
            System.out.println("Error");
    }
    public static int getCount(){
        return count;
    }

    
}
