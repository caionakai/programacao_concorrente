/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercicio3;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kaioe
 */
public class codigo_ex1 {
    public static void main(String[] args) {
        
    }
}
class HelloThread extends Thread {;
    @Override
    public void run(){        
        // Gera um numero randomico para desligar a thread
        Random rand = new Random();
        int numRand = rand.nextInt(100);
        System.out.println("Vou esperar "+ numRand + " milisegundos para morrer");
        if(Thread.interrupted()){
            try {
                throw new InterruptedException();
            } catch (InterruptedException ex) {
                Logger.getLogger(HelloThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            Thread.sleep(numRand);
        } catch (InterruptedException ex) {
            Logger.getLogger(HelloThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}