/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at6_exerc3;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kaioe
 */
public class BoundedCounter {
    
    int valor = 0;
    int max;
    int min;
    
    public BoundedCounter(int max, int min){
        this.max = max;
        this.min = min;
    }
    
    public synchronized void increment(){
        valor++;
        System.out.println("Valor: " + valor);
        while (valor >= max) {
            System.out.println("Limite Maximo Atingido Esperando..");
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(BoundedCounter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        notifyAll();
    }
    
    public synchronized void decrement(){
        valor--;
        System.out.println("Valor: " + valor);
        while (valor <= min) {
            System.out.println("Limite Minimo Atingido Esperando..");
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(BoundedCounter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        notifyAll();
    }
}
