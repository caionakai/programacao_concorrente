
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Exercicio 1: Faca um programa em Java que consulte periodicamente o 
 * estado de um conjunto de threads.

 * @author Caio Nakai
 */
public class at3_exer1 {
    public static void main(String[] args) {
//      cria duas threads
        Thread t1 = new HelloThread();
        Thread t2 = new HelloThread();
        t1.start();
        t2.start();
//      cria array list para inserir as threads
        List<Thread> vetor = new ArrayList<>();
//      insere as duas threads no nosso array list
        vetor.add(t1);
        vetor.add(t2);
        Thread monitor = new ThreadMonitor(vetor);
        monitor.start();

    }
}
// threads q serao monitoradas
class HelloThread extends Thread {;
    @Override
    public void run(){        
        System.out.println("Sou uma Thread c.c");
        while(true){
            
        }
    }
}
// thread que vai recuperar o estado das outras threads
class ThreadMonitor extends Thread {;
    List<Thread> vetor;
    @Override
    public void run(){        
        System.out.println("Sou uma Thread que Monitora c.c");
        while(true){
            try {
                for(Thread t0 : vetor){
                    System.out.println("Estado da Thread: " + t0.getState());
                }
                Thread.sleep(10000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadMonitor.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    public ThreadMonitor(List<Thread> vetor){
        this.vetor = vetor;
    }
}