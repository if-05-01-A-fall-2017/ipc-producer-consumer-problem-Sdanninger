/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basic;

import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Simon
 */
public class Controller1 {

    /**
     * @param args the command line arguments
     */
    public static final int N = 100;
    public static int count = 5;
    public static Producer1 prodThread = new Producer1();
    public static Consumer1 consThread = new Consumer1();
    public static void main(String[] args){
        prodThread.start();
        //prodThread.run();
        consThread.start();
        //consThread.run();
       
        //System.out.println("Im Done");
    }

    public static void insert_item(){
        count++;
    }
    public static void remove(){
        count--;
    }
    
}
