/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at8_exerc1;

import java.util.concurrent.Semaphore;

/**
 * Exercicio 1: Implementar solucoes para o problema dos leitores-escritores
   que:    
 *      1.1 priorize os leitores
 *      1.2 sem inanicao.
 *      1.3 priorize os escritores.

 * @author Caio Nakai
 */
public class at8_exerc1 {
    public static void main(String[] args) {
        
    }
}
/* //Priorizando o Leitor:

class ReaderWriter {
    int numReaders = 0;
    Semaphore mutex = new Semaphore(1);
    Semaphore wlock = new Semaphore(1);
    
    public void startRead() throws InterruptedException{
        mutex.acquire();
        numReaders++;
        if(numReaders == 1){
            wlock.acquire();
        }
        
        mutex.release();
    }
    public void endRead() throws InterruptedException{
        mutex.acquire();
        numReaders--;
        if(numReaders == 0){
            wlock.release();
        }
        mutex.release();
    }
    public void startWrite() throws InterruptedException{
        mutex.acquire();
        if(numReaders == 0){
            wlock.acquire();
        }
        mutex.release();
    }
    public void endWrite(){
        wlock.release();
    }    
}
*/

// Sem Starvation

class ReaderWriter {
    Semaphore mutex = new Semaphore(1);
    Semaphore wlock = new Semaphore(1);
    
    public void startRead() throws InterruptedException{
        mutex.acquire();
        wlock.acquire();
        mutex.release();
    }
    public void endRead() throws InterruptedException{
        mutex.acquire();
        wlock.release();
        mutex.release();
    }
    public void startWrite() throws InterruptedException{
        mutex.acquire();
        wlock.acquire();
        mutex.release();
    }
    public void endWrite() throws InterruptedException{
        mutex.acquire();
        wlock.release();
        mutex.release();
    }    
}

/* // Priorizando o Escritor
class ReaderWriter {
    int numReaders = 0;
    int numWriters = 0;
    Semaphore mutex = new Semaphore(1);
    Semaphore wlock = new Semaphore(1);
    
    public void startRead() throws InterruptedException{
        mutex.acquire();
        if(numReaders == 0 && numWriters == 0){
            wlock.acquire();
        }
        numReaders++;
        mutex.release();
    }
    public void endRead() throws InterruptedException{
        mutex.acquire();
        numReaders--;
        if(numReaders == 0){
            wlock.release();
        }
        mutex.release();
    }
    public void startWrite() throws InterruptedException{
        mutex.acquire();
        numWriters++;
        if(numReaders == 0){
            wlock.acquire();
        }
        mutex.release();
    }
    public void endWrite() throws InterruptedException{
        mutex.acquire();
        numWriters--;
        wlock.release();
        mutex.release();
    }    
}
*/