/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at12_exerc1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Exercicio 1: Implemente duas versoes do problema do produtor/consumidor com M
	produtores e N consumidores usando ArrayBlockingQueue e
	LinkedBlockingQueue. Compare o desempenho das duas implementacoes.
 * @author Caio Nakai
 */
public class at12_exerc1 {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);
//      BlockingQueue<Integer> queue = new LinkedBlockingDeque<>(100);
        int m = 7;
        int n = 4;

        // vetor para armazenar a referencia das threads para poder dar join
        List<Thread> vetor_prod = new ArrayList<>();
        List<Thread> vetor_cons = new ArrayList<>();

        long timeStart = System.currentTimeMillis();

        for(int i=0; i<m; i++){
            vetor_prod.add(new Thread(new Produtor(queue)));
        }
        for(int j=0; j<n; j++){
            vetor_cons.add(new Thread(new Consumidor(queue)));
        }
        // inicia as threads
        for(Thread t1 : vetor_prod){
        	t1.start();
        }
        for(Thread t2 : vetor_cons){
        	t2.start();
        }
        // espera as threds acabarem pra medir o tempo
        for(Thread t3 : vetor_prod){
        	t3.join();
        }
        for(Thread t4 : vetor_cons){
        	t4.join();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Tempo decorrido: "+ (endTime - timeStart ));
    }
}

class Consumidor implements Runnable{
    private final BlockingQueue<Integer> queue;
    
    @Override
    public void run(){
        try {
            while(true){
                Thread.sleep(2000);
                queue.take();
                System.out.println("TAKE Queue: " + queue.toString());
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Consumidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public Consumidor(BlockingQueue<Integer> queue){
        this.queue = queue;
    }
}
class Produtor implements Runnable {
    private final BlockingQueue<Integer> queue;
    
    @Override
    public void run(){
        try {
            while(true){
                Thread.sleep(1000);
                queue.add(500);
                System.out.println("ADD Queue: " + queue.toString());
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Produtor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    
    public Produtor(BlockingQueue<Integer> queue){
        this.queue = queue;
    }
}

