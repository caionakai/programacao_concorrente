/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at6_exerc3;

/**
 *
 * @author kaioe
 */
public class BoundedCounter {
    int valor = 0;
    public synchronized void increment(){
        valor++;
        notifyAll();
    }
    public synchronized void decrement(){
        valor--;
        notifyAll();
    }
}
