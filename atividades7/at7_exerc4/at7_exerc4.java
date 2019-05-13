/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at7_exerc4;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Exercicio 4: Multiplex
 * Garantir acesso à seção crítica para no máximo N threads.
 * Faça um código que possibilite que N threads estejam na seção crítica simultaneamente
 * @author Caio Nakai
 */
public class at7_exerc4 {
    public static void main(String[] args) {
        int n = 4;
        Contador c = new Contador();
        Semaphore s = new Semaphore(n);
        
        for(int i=0; i<4; i++){
            new ThreadMultiplex(c,s).start();            
        }
        
    }
}

class ThreadMultiplex extends Thread{
    Contador count;
    Semaphore sem;
    
    public ThreadMultiplex(Contador c, Semaphore s){
        this.count = c;
        this.sem = s;
    }
    
    @Override
    public void run(){
        try {
            sem.acquire();
            count.incremento();
            sem.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(ThreadMultiplex.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}

class Contador{
    int valor = 0;
    public void incremento(){
        valor++;
        System.out.println("Valor: " + valor);
    }
}
