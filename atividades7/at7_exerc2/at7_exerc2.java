/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at7_exerc2;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Exercicio 2: Rendezvous. Enviar sinalização para um ponto de encontro entre threads.
 * Faça um código que uma thread t1 e t2 são inicializadas e t1 espera por t2 e vice-versa.
 * 
 * @author Caio Nakai
 */
public class at7_exerc2 {
    public static void main(String[] args) {
        Semaphore s = new Semaphore(0);
        Semaphore s2 = new Semaphore(0);
        ThreadUm t1 = new ThreadUm(s,s2);
        ThreadDois t2 = new ThreadDois(s,s2);
        t1.start();
        t2.start();
    }
}

class ThreadUm extends Thread{
    Semaphore sem;
    Semaphore sem2;
    public ThreadUm(Semaphore sem, Semaphore sem2){
        this.sem = sem;
        this.sem2 = sem2;
    }
    
    @Override
    public void run(){
        try {
            System.out.println("Trecho 1.1");
            sem2.release();
            sem.acquire();
            System.out.println("Trecho 1.2");
            sem.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(ThreadUm.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }
}

class ThreadDois extends Thread{
    Semaphore sem;
    Semaphore sem2;
    public ThreadDois(Semaphore sem, Semaphore sem2){
        this.sem =  sem;
        this.sem2 =  sem2;
    }
    
    @Override
    public void run(){
        try {
            System.out.println("Trecho 2.1");
            sem.release();
            sem2.acquire();
            System.out.println("Trecho 2.2");
            sem2.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(ThreadDois.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
