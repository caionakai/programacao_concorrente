/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at7_exerc5;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Exercicio 5: Após n threads sincronizarem em um ponto em comum, um trecho específico (ponto crítico) pode
ser executado por cada uma delas. Faça um código que possibilite barrar N threads em um ponto específico de 
* execução e, após todas alcançarem o ponto, todas devem executar o trecho de código após esse ponto.
 * @author Caio Nakai
 */
public class at7_exerc5 {
    public static void main(String[] args) {
        int n = 3;
        Barreira bar = new Barreira(n);
        MyThread t1 = new MyThread(bar);
        MyThread t2 = new MyThread(bar);
        MyThread t3 = new MyThread(bar);
        MyThread t4 = new MyThread(bar);
        
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
   
}

class MyThread extends Thread {
    Barreira barreira;
    
    public MyThread(Barreira bar){
        this.barreira = bar;
    }
    @Override
    public void run(){
        System.out.println("Executando até antes da barreira.");
        
        // barreira
        try {
            barreira.block();
        } catch (InterruptedException ex) {
            Logger.getLogger(MyThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Código depois da barreira.");
        
    }
}

