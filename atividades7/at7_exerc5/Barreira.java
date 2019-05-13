/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at7_exerc5;

import java.util.concurrent.Semaphore;

/**
 *
 * @author kaioe
 */
public class Barreira{
    int n;
    int count=0;
    Semaphore semaforo = new Semaphore(0);
    
    public Barreira(int n){
        this.n = n;
    }
    public synchronized void conta(){
        count++;
    }
    public void block() throws InterruptedException{
        conta();

        if(count >= n){
            semaforo.release(n);
        }
        semaforo.acquire();
        

    }
}
