/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at4_exerc2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Exercicio 2: Modifique o codigo para garantir que sera thread-safe.
Implemente tres versoes: Usando Atomic, sincronizando o
metodo e sincronizando o bloco.
 *
 * @author Caio Nakai
 */
public class at4_exerc2 {

    public static void main(String[] args) throws InterruptedException {
        int numThread = 4; // numero de threads
        // intervalo
        int inicio = 0;
        int fim = 20;
        
        contaPrimo(inicio, fim, numThread);

    }

    public static void contaPrimo(int inicio, int fim, int numThread) throws InterruptedException {
        Counter contador = new Counter(inicio,fim); // instancia o contador
        List<Thread> vetor = new ArrayList<>(); // cria um arraylist de thread
        for (int i = 1; i <= numThread; i++) {
            vetor.add(new JavaThread(contador, inicio, fim)); // insere threads no array list
        }
        // inicia as threads
        for (Thread t : vetor) {
            t.start();
        }
        // espera as threads acabar
        for (Thread t : vetor) {
            t.join();
        }        
        System.out.println("Qntd de num primos: " + contador.getCount());

    }
}

// Thread que altera o Contador
class JavaThread extends Thread {

    Counter count;
    int inicio, fim;

    @Override
    public void run() {
        int valor = inicio; // valor a ser verificado
        while (true) {
            if(fim < valor || valor == -1){ // controle pra verificar se terminou o intervalo
                break;
            }
            ehPrimo(valor);
            valor = count.numToVerify();

        }

    }

    public JavaThread(Counter count, int inicio, int fim) {
        this.count = count;
        this.inicio = inicio;
        this.fim = fim;
    }

    public void ehPrimo(int num) {
        boolean isPrimo = true; // reseta variavel
        for (int j = 2; j < num; j++) { // for que verifica se o num é primo
            if ((num % j) == 0) { // se o resto da divisao por qualquer outro numero alem de 1 e ele mesmo for 0 entao nao é primo
                isPrimo = false; // seta falso na variavel pois nao eh primo
            }
        }
        if (isPrimo && num != 1 && num != 0) {
            count.addCount(); // soma 1 no contador
        }
        
    }
}

// classe do Contador
class Counter extends Thread {

    private final AtomicLong contador = new AtomicLong(0);
    int inicio, fim, valor;
    int count = 0;
    // usando variavel atomic
//    public long getCount() {
//        return contador.get();
//    }
    // usando variavel atomic
//    public void addCount() {;
//        contador.incrementAndGet();
//    }
    public int getCount(){
        return count;
    }
    // usando bloco sincronizado
//    public void addCount(){
//        synchronized(this){
//            count++;
//        }
//    }
    // usando método sincronizado
    public synchronized void addCount(){
        count++;
    }
    // devolve o numero que deve ser verificado se eh primo
    public int numToVerify(){
        // se o valor extrapolou o intervalo
        if(fim < valor){
            return -1;
        }
        valor++;
        return valor;
        
    }
    
    public Counter(int inicio, int fim){
        this.inicio = inicio;
        this.valor = inicio;
        this.fim = fim;
    }
}
