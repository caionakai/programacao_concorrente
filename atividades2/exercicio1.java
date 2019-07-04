/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atividades2;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Faca um programa em Java que inicie tres threads e, cada
 * thread, espere um tempo aleatorio para terminar.
 * @author Caio Naki, RA:a1816403
 */
public class exercicio1 {
    public static void main(String[] args) {
        Thread t1 = new HelloThread();
        Thread t2 = new HelloThread();
        Thread t3 = new HelloThread();
        t1.start();
        t2.start();
        t3.start();
        
        
    }
}
class HelloThread extends Thread {;
    @Override
    public void run(){        
        // Gera um numero randomico para desligar a thread
        Random rand = new Random();
        int numRand = rand.nextInt(100);
        System.out.println("Vou esperar "+ numRand + " milisegundos para morrer");
        try {
            Thread.sleep(numRand);
        } catch (InterruptedException ex) {
            Logger.getLogger(HelloThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
