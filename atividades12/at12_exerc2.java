/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at12_exerc2;

import java.util.Comparator;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Exercicio 2: Implemente o problema do produtor/consumidor para uma estrutura com
    os seguintes campos: numero, simbolo, naipe, que representa uma carta
    de baralho. A implementacao deve possibilitar que apos 10 cartas
    produzidas por dois produtores, outros dois consumidores pegarao
    somente as maiores cartas. Os produtores somente devem produzir mais
    cartas apos os consumidores removerem 3 cartas cada um. As cartas
    remanescentes podem continuar na estrutura. Use a ordenacao do
    baralho da menor para maior: A, 2, ..., 10, Q, J, K.
 * @author Caio Nakai
 */
public class at12_exerc2 {
    public static void main(String[] args) {
//        BlockingQueue<Data> queue = new ArrayBlockingQueue<>(100);
        Comparator<Data> numSorter = Comparator.comparing(Data::getNumber);
        
        PriorityBlockingQueue<Data> queue = new PriorityBlockingQueue<>(11, numSorter );
        ControleDeInsercao cdi = new ControleDeInsercao();
        new Thread(new Produtor(queue, cdi)).start();
        new Thread(new Produtor(queue, cdi)).start();
        new Thread(new Consumidor(queue, cdi)).start();
        new Thread(new Consumidor(queue, cdi)).start();
        
    }
}

class Data{
    private int numero;
    private String naipe;
    
    public Data(int numero, String naipe){
        this.numero = numero;
        this.naipe = naipe;
    }
    
    void printData(){
        if(numero == 1)
            System.out.print(" Valor : A");
        else if(numero == 11)
            System.out.print(" Valor : J");
        else if(numero == 12)
            System.out.print(" Valor : Q");
        else if(numero == 13)
            System.out.print(" Valor : K");
        else
            System.out.print(" Valor: " + numero);
            
        System.out.print(" Naipe: " + naipe);
        System.out.println();
    }
    
    int getNumber(){
        return this.numero;
    }
}

class Consumidor implements Runnable{
    private final BlockingQueue<Data> queue;
    ControleDeInsercao cdi;
    
    @Override
    public void run(){
        try {
            while(true){
                if(cdi.getCountProd() >= 10){
                   queue.take().printData();
                   cdi.decrement_cons();
                   Thread.sleep(2000);
                }
                if(cdi.getCountCons() <= 0){
                    System.out.println("TO AQUI NO IF");
                    cdi.mutex.release();
                    cdi.resetCons();
                }
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Consumidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public Consumidor(BlockingQueue<Data> queue, ControleDeInsercao cdi){
        this.queue = queue;
        this.cdi = cdi;
    }
}
class Produtor implements Runnable {
    private final BlockingQueue<Data> queue;
    ControleDeInsercao cdi;
    
    @Override
    public void run(){
        try {
            while(true){
                Thread.sleep(2000);
                Random gerador = new Random();
                int randomValue = gerador.nextInt(12)+1;
                queue.put(new Data(randomValue, "copas"));
                cdi.increment_prod();
                System.out.println("Inserido: " + randomValue);
                if(cdi.getCountProd() >= 10){
                    System.out.println("VOU ESPERAR REMOVER");
                    
                    cdi.mutex.acquire();
                    cdi.setProdZero();
                }
                
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Produtor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    
    public Produtor(BlockingQueue<Data> queue, ControleDeInsercao cdi){
        this.queue = queue;
        this.cdi = cdi;
    }
}

class ControleDeInsercao{
    Semaphore mutex = new Semaphore(0);
    int countprod = 0;
    private int countcons = 6;
    Semaphore mx = new Semaphore(1);
    
    void increment_prod() throws InterruptedException{
        mx.acquire();
        countprod++;
        mx.release();
    }
    
    void decrement_cons() throws InterruptedException{
        mx.acquire();
        countcons--;
        mx.release();
    }    
    
    int getCountProd(){

        return countprod;

    }
    int getCountCons(){
        return countcons;
    }    
    
    void resetCons(){
        countcons = 6;
    }
    void setProdZero(){
        countprod = 0;
    }
}