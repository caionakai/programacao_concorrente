/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at6_exerci2;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kaioe
 */
public class Counter{
    int valor = 0;
    
    public Counter(){
         
    }
    public synchronized void increment(){
        valor++;
        System.out.println("valor: " + valor);
        notifyAll();
        
    }
    public synchronized void sleepUntil(int x){
        // considera x como quantidade de incrementos
        int v = valor;
        while(valor <= x+v){
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Counter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
