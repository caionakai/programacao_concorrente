/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at7_exerc6;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Exercicio 6: Threds em um laço executam uma série de passos e sincronizam em uma barreira a cada passo.
    Faça um código que implemente uma barreira reusável que feche a si própria após todas as threads
    passarem
 * @author Caio Nakai
 */
public class at7_exerc6 {
    public static void main(String[] args) {
        
    }
}

class MyThread extends Thread {
    Semaphore sem;
    Barreira so;
    int n; // numero de threads que devem sincronizar
    
    public MyThread(Semaphore sem, int n, Barreira so){
        this.sem = sem;
        this.n = n;
        this.so = so;
    }
    @Override
    public void run(){
        System.out.println("Executando até antes da barreira.");
        
        // barreira
        try {
            so.conta1();
            if(so.count == n - 1){
                sem.release(n);
                System.out.println("Liberei os tickets");
            }
            sem.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(MyThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Código depois da barreira.");
        sem.release();
        
    }
}


class Barreira{
    int count=0;
    public synchronized void conta1(){
        count++;
    }
}