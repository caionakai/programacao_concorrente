/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at7_exerc1;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Exercicio 1: Enviar sinal para outra thread para indicar que um evento ocorreu.
 * Faça um código que uma thread t1 e t2 são inicializadas simultaneamentem, mas a t2
 * pode somente continuar após a sinalização de t1.
 * @author kaioe
 */
public class at7_exerc1 {
    public static void main(String[] args) {
        Semaphore s = new Semaphore(0);
        ThreadExample1 t1 = new ThreadExample1(s);
        ThreadExample2 t2 = new ThreadExample2(s);
        t1.start();
        t2.start();
    }
}

class ThreadExample1 extends Thread {
    private final Semaphore semaphore;
    
    public ThreadExample1(Semaphore semaphore){
        this.semaphore = semaphore;
    }
    
    @Override
    public void run(){
        try {
            System.out.println("Thread 1");
            Thread.sleep(5000); // wait 5sec
            semaphore.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(ThreadExample1.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}

class ThreadExample2 extends Thread{
    private final Semaphore semaphore;;

    public ThreadExample2(Semaphore semaphore){;
        this.semaphore = semaphore;
    }
    
    @Override
    public void run(){
        try {
            System.out.println("esperando evento de t1");
            semaphore.acquire();
            System.out.println("uhu executei");
        } catch (InterruptedException ex) {
            Logger.getLogger(ThreadExample2.class.getName()).log(Level.SEVERE, null, ex);
        }
        semaphore.release();
    }
}
