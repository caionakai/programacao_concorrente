/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at7_exerc3;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Exercicio 3: Mutex
 * Garantir acesso exclusivo a seção critica. 
 * Faça um código que possibilite que 2 ou mais threads realizem o incremento
 * de um contador. Faça a exclusão mútua com semáforo.
 * @author Caio Nakai
 */
public class at7_exerc3 {
    public static void main(String[] args) {
        Contador c = new Contador();
        Semaphore s = new Semaphore(1);
        
        for(int i = 0; i<100; i++){
            new ThreadIncrement(c, s).start();         
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

class ThreadIncrement extends Thread{
    Contador c;
    Semaphore s;
    
    public ThreadIncrement(Contador c, Semaphore s){
        this.c = c;
        this.s = s;
    }
    
    @Override
    public void run(){
        try {
            for(int i =0; i<1000; i++){
                s.acquire();
                c.incremento();
                s.release();
                
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(ThreadIncrement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}