/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package condition_lock;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *Crie uma classe SharedFifoQueue e use Conditions para
controlar se a fila est ́a vazia ou cheia.  Teste usando threads
produtoras e consumidoras
 * @author a1816403
 */
public class condition_lock {
    public static void main(String[] args) {
        SharedFifoQueue sfq = new SharedFifoQueue(10);
        Produtora pd = new Produtora(sfq);
        Consumidora cs = new Consumidora(sfq);
        pd.start();
        cs.start();
    }
}

class SharedFifoQueue{
    Queue<Integer> fifo = new LinkedList<Integer>();
    private int size;
    final Lock lock = new ReentrantLock();
    final Condition prodQueue = lock.newCondition();
    final Condition consQueue = lock.newCondition();
    
    
    public SharedFifoQueue(int size){
        this.size = size;
    }
    
    public void add(int x) throws InterruptedException{
        lock.lock();
        try{
            // enquanto estiver cheia espera
            while(fifo.size() == size){
                prodQueue.await();
            }
            fifo.add(x);
            System.out.println("Fifo " + Arrays.toString(fifo.toArray()));         
            consQueue.signal(); // sinaliza a fila de consumidor
            
        } finally{
            lock.unlock();
        }

    }
    
    public void remove() throws InterruptedException{
        lock.lock();
        try{
            while(fifo.size() == 0){
                consQueue.await();
            }
            fifo.remove();
            System.out.println("Fifo " + Arrays.toString(fifo.toArray()));            
            prodQueue.signal();
        }finally{
            lock.unlock();
        }
    }
    
}

class Produtora extends Thread{
    SharedFifoQueue sfq;
    public Produtora(SharedFifoQueue sfq){
        this.sfq = sfq;
    }
    
    @Override
    public void run(){
        while(true){
            try {
                this.sleep(1000);
                sfq.add(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(Produtora.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
class Consumidora extends Thread{
    SharedFifoQueue sfq;
    public Consumidora(SharedFifoQueue sfq){
        this.sfq = sfq;
    }
    
    @Override
    public void run(){
        while(true){
            try {
                this.sleep(2000);
                sfq.remove();
            } catch (InterruptedException ex) {
                Logger.getLogger(Consumidora.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
}