/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at3_exer4;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kaioe
 */
public class at3_exer4 {
    public static void main(String[] args) throws InterruptedException {
        int numThread = 4; // numThread
        List<Integer> vetor = new ArrayList<>(); // array de inteiros
        int valorProcurado = 15; // valor aleatorio para teste
//      insere numeros de 0 a 100 no vetor
        for(int i=0; i<=100; i++){
            vetor.add(i);
        }
        metodoBusca(valorProcurado, vetor, numThread);
        
    }
    
    public static void metodoBusca(int valorProcurado, List<Integer> vetor, int numThread) throws InterruptedException{
        // lista de Threads
        List<Thread> vetorThread = new ArrayList<>();
        // intervalo para busca de cada thread
        int intervalo = vetor.size() / numThread;
        List<Integer> resultado = new ArrayList<>();
        // cria threads e armazenam no vetor
        for(int i=1; i <= numThread; i++){
            if(i==1){ // caso incial
                vetorThread.add(new BuscaValor(0, intervalo, resultado, valorProcurado));
            }else{
                vetorThread.add(new BuscaValor(intervalo*(i-1), intervalo*i, resultado, valorProcurado));
            }
        }
        // starta as threads
        for(Thread t: vetorThread){
            t.start();
        }
        // espera todas acabar
        for(Thread t: vetorThread){
            t.join();
        }
        System.out.println("Numero Encontrado: " + resultado);        
    }
}

class BuscaValor extends Thread {;
    int inicio, fim, valorProcurado;
    List<Integer> resultado;
    
    @Override
    public void run(){        
        for(int i = inicio; i<=fim; i++){ // for que percorre todo o intervalo
           if(i == valorProcurado){
               resultado.add(i);
           }
        }
    }
    public BuscaValor(int inicio, int fim, List<Integer> resultado, int valorProcurado){
        this.inicio = inicio;
        this.fim = fim;
        this.resultado = resultado;
        this.valorProcurado = valorProcurado;
    }
}
