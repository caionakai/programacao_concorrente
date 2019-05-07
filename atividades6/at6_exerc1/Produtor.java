/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at6_exerc1;

import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author kaioe
 */
public class Produtor extends Thread{
    Buffer buf;
    public Produtor(Buffer buf){
        this.buf = buf;
    }
    
    public void run(){
        while(true){
            try {
                buf.add(1);
                // espera 1 a 5 segundos para produzir outro numero um
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 5000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Interrupted exception: " + e);
            }
        }
    }
}
