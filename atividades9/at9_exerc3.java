/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arraylistthreadsafe;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Exercicio 3. Faca uma classe ArrayListThreadSafe usando ReadWriteLock.
Teste usando threads que realizam leitura e escrita para essa
estrutura.
 * @author a1816403
 */
public class arraylistthreadsafe {
    public static void main(String[] args) {
        ArrayListThreadSafe2 alts = new ArrayListThreadSafe2();
        // segundo parametro da THreadTest indica se a thread eh de leitura ou nao
        ThreadTest tt = new ThreadTest(alts, false);
        tt.start();
        ThreadTest tt3 = new ThreadTest(alts, false);
        tt3.start();        
        ThreadTest tt4 = new ThreadTest(alts, false);
        tt4.start();
        ThreadTest tt2 = new ThreadTest(alts, true);
        tt2.start();
        
    }
}

class ArrayListThreadSafe2{
    final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    ArrayList<Integer> data = new ArrayList();
    
    public void add(int num){
        rwl.writeLock().lock();
        try {
            data.add(num);
        } finally {
            rwl.writeLock().unlock();
        }
    }
    
    public void read(){
        rwl.readLock().lock();
        try {
            System.out.println("ArrayList: " + data);
        } finally {
            rwl.readLock().unlock();
        }
    }
    
}

class ThreadTest extends Thread{
    ArrayListThreadSafe2 alts;
    boolean isRead = false;
    
    public ThreadTest(ArrayListThreadSafe2 alts, boolean isRead){
        this.alts = alts;
        this.isRead = isRead;
    }
    
    @Override
    public void run(){
        while(true){
            try {
                if(isRead){
                    alts.read();
                    this.sleep(3000);
                }else{
                    alts.add(10);
                    this.sleep(1000);
                }
                
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadTest.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
}
