/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercicio4;

/**
 * Exercicio 4: Faca uma Thread que monitora um conjunto de threads e
exiba quais threads receberam sinais de interrupcao.
 * @author Caio Nakai, RA:1816403
 */
public class main {
    public static void main(String[] args) {
//      instancia duas threads e inicia
        Thread t1 = new HelloThread();
        Thread t2 = new HelloThread();
        t1.start();
        t2.start();
        new ThreadMonitor(t1, t2).start();
        t2.interrupt();
    }
}
class ThreadMonitor extends Thread {;
    Thread t1;
    Thread t2;
    @Override
    public void run(){        
        while(true){
            if(t1.isInterrupted() | t2.isInterrupted()){
                System.out.println("Uma thread foi interrompida");
                break;
            }
        }
    }
    public ThreadMonitor(Thread t1, Thread t2){
        this.t1 = t1;
        this.t2 = t2;
    }
}