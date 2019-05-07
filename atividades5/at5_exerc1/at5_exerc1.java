/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at5_exerc1;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Exercicio 1:  Implemente o problema do produtor-consumidor que ha um
buffer compartilhado entre threads. Ha uma unica thread
produtora e uma unica consumidora. O buffer e preenchido
em tempos aleatorios pela thread produtora. Assim que for
produzido algo, a thread consumidora deve ser comunicada
para obter o valor
 * @author Caio Nakai
 */
public class at5_exerc1 {
    public static void main(String[] args) {
        ProductBuffer buff = new ProductBuffer();
        MyWaitNotify mwn = new MyWaitNotify();
        
        Thread t_prod = new Produtora(buff, mwn);
        Thread t_cons = new Consumidora(buff, mwn);
        
        t_cons.start();
        t_prod.start();
        
        
    }
}

class Produtora extends Thread {;
    ProductBuffer sharedObject;
    MyWaitNotify mwn;
    boolean wasSignalled = false;
    @Override
    public void run(){        
        System.out.println("Sou uma Thread Produtora");
        while(true){
            try {
                Thread.sleep(5000); // a cada 5seg add mais leite
            } catch (InterruptedException ex) {
                Logger.getLogger(Produtora.class.getName()).log(Level.SEVERE, null, ex);
            }
            sharedObject.addLeite();
            doNotify(); // avisa o consumidor q produziu leite
            System.out.println("Produzi 1 leite");
        }
    }
    public Produtora(ProductBuffer sharedObject, MyWaitNotify mwn){
        this.sharedObject = sharedObject;
        this.mwn = mwn;
    }
    
    public void doNotify(){
        synchronized(mwn){
            wasSignalled = true;
            mwn.notify();
        }
    }
}

class Consumidora extends Thread {;
    ProductBuffer sharedObject;
    MyWaitNotify mwn;
    boolean wasSignalled = false;
    @Override
    public void run(){        
        System.out.println("Sou uma Thread Consumidora");
        while(true){
            try {
                Thread.sleep(4000); // espera 4 seg
                
            } catch (InterruptedException ex) {
                Logger.getLogger(Consumidora.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(sharedObject.getLeite()==0){
                try {
                    System.out.println("Tentei pegar 1 leite");
                    doWait();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Consumidora.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                System.out.println("Consegui pegar o leite");
                sharedObject.getLeite();
            }
        }
    }
    public Consumidora(ProductBuffer sharedObject, MyWaitNotify mwn){
        this.sharedObject = sharedObject;
        this.mwn = mwn;
    }    
    public void doWait() throws InterruptedException{
        synchronized(mwn){
            mwn.wait();
            wasSignalled = false;
        }
    }
}

class ProductBuffer{
    int leite = 0;

    public int getLeite() {
        return leite;
    }

    public void addLeite() {
        this.leite += 1;
    }
    
    public void pegaLeite(){
        this.leite -= 1;
    }
}

class MyWaitNotify{
    
}