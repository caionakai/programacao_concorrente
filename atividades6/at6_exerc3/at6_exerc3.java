/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at6_exerc3;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Exercicio 3: Escreva um monitor BoundedCounter que possui um valor
    mınimo e maximo. A classe possui dois metodos: increment()
    e decrement(). Ao alcancar os limites mınimo ou maximo, a
    thread que alcancou deve ser bloqueada
 * @author Caio Nakai
 */
public class at6_exerc3 {
    public static void main(String[] args) {
        BoundedCounter bc = new BoundedCounter();
        
    }
}

class ThreadIncrement extends Thread{
    BoundedCounter bc;
    public void run(){
        try {
            Thread.sleep(1000);
            bc.increment();
        } catch (InterruptedException ex) {
            Logger.getLogger(ThreadIncrement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public ThreadIncrement(BoundedCounter bc){
        this.bc = bc;
    }
}

class ThreadDecrement extends Thread{
    BoundedCounter bc;
    public void run(){
        try {
            Thread.sleep(1000);
            bc.decrement();
        } catch (InterruptedException ex) {
            Logger.getLogger(ThreadDecrement.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public ThreadDecrement(BoundedCounter bc){
        this.bc = bc;
    }
}
