/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at6_exerci2;

import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Exercicio 2: Escreva uma monitor Counter que possibilita um processo
dormir ate o contador alcancar um valor. A classe Counter
permite duas operacoes: increment() e sleepUntil(int x).
 * @author Caio Nakai
 */
public class at6_exerci2 {
    public static void main(String[] args) {
        Counter c = new Counter();
        ThreadActive t = new ThreadActive(c);
        ThreadActive t2 = new ThreadActive(c);
        ThreadActive t3 = new ThreadActive(c);
        ThreadDorminhoca td = new ThreadDorminhoca(9, c);
        ThreadDorminhoca td2 = new ThreadDorminhoca(14, c);
        td.start();
        td2.start();
        t.start();
        t2.start();
        t3.start();
        
    }
}

class ThreadActive extends Thread{
    Counter count;
    
    @Override
    public void run(){
        while(true){
            try {
                Thread.sleep(2000);
                count.increment();
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadActive.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
    
    public ThreadActive(Counter count){
        this.count = count;
    }
}

class ThreadDorminhoca extends Thread{
    int x;
    Counter count;
    
    @Override
    public void run(){
        System.out.println("Vou esperar " + x + "incrementos");
        count.sleepUntil(x);
        System.out.println("Acordei uhu!");
    }
    
    public ThreadDorminhoca(int x, Counter count){
        this.x = x;
        this.count = count;
    }
}
