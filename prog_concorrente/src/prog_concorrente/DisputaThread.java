/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prog_concorrente;

/**
 *Atividade 3: Implemente o exemplo de código que gera a condição de disputa e 
 * tente gerar um teste para que ocorra um problema de segurança (safety).
 * 
 * @author Caio Nakai, RA:a1816403
 */

public class DisputaThread {
    public static void main(String[] args) {
        Counter numCount = new Counter(); // instancia o contador
        
        new Thread(new JavaThread(numCount)).start(); // instancia as threads passando o contador
        new Thread(new JavaThread(numCount)).start();
        new Thread(new JavaThread(numCount)).start();
    }
}

// Thread que altera o Contador
class JavaThread extends Thread{
    Counter count;

    @Override
    public void run(){
        while(true){
            System.out.println("lido: " + this.count.getCount());
            this.count.addCount();
        }
        
    }
    
    public JavaThread(Counter count){
        this.count = count;
    }
}

// Contador
class Counter extends Thread{
    private int count = 0;
    
    public int getCount(){
        return count;
    }
    
    public void addCount(){
        count = count +1;
    }
}