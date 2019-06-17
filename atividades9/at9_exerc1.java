/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locks;

import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *1.  Faca um programa usando Lock para simular a atualizacao de
um contador que  ́e acessado por multiplas threads.  O
contador pode diminuir e aumentar.
 * @author a1816403
 */
public class locks {
    public static void main(String[] args) {
        LockObject lo = new LockObject();
        TesteLock tl = new TesteLock(lo);
        TesteLock tl2 = new TesteLock(lo);
        tl.start();
        tl2.start();
    }
}

class LockObject {
    private final ReentrantLock lock = new ReentrantLock();
    private int counter = 0;
    public void soma(){
        lock.lock();
        try{
            counter++;
            System.out.println("Valor: " + counter);
        } finally{
            lock.unlock();
        }
    }
    public void diminui(){
        lock.lock();
        try{
            counter--;
            System.out.println("Valor: " + counter);
        } finally{
            lock.unlock();
        }
    }    
}

class TesteLock extends Thread{
    LockObject lo;
    public TesteLock(LockObject lo){
        this.lo = lo;
    }
    
    @Override
    public void run(){
        while(true){
            try {
                this.sleep(1000);
                lo.soma();
                this.sleep(2000);
                lo.diminui();
            } catch (InterruptedException ex) {
                Logger.getLogger(TesteLock.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}