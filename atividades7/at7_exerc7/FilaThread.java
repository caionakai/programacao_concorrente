/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at7_exerc7;

import java.util.concurrent.Semaphore;

/**
 *
 * @author kaioe
 */
public class FilaThread {
    Semaphore sem_fila_1 = new Semaphore(0);
    Semaphore sem_fila_2 = new Semaphore(0);
    int size_fila1 = 0;
    int size_fila2 = 0;
    int conta_release_f1 = 0;
    int conta_release_f2 = 0;
    
    public void enfileiraF1() throws InterruptedException{
        synchronized(this){
            conta_release_f1 = 0;
            size_fila1++;
            if(size_fila2 >= 1){
                sem_fila_2.release();
                conta_release_f1++;
                size_fila2--;
            }
        }
        sem_fila_1.acquire();
        synchronized(this){
            if(conta_release_f1 == 0){
                sem_fila_2.release();
            }
        }
    }
    
    public void enfileiraF2() throws InterruptedException{
        synchronized(this){
            conta_release_f2 = 0;
            size_fila2++;
            if(size_fila1 >= 1){
                sem_fila_1.release();
                conta_release_f2++;
                size_fila1--;
            }
        }
        sem_fila_2.acquire();
        synchronized(this){
            if(conta_release_f1 == 0){
                sem_fila_1.release();
            }        
        }
    }
}
