/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercicio5;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Exercicio5: Faca uma thread Java que fica aguardando uma sequencia
numerica de tamanho arbitrario digitado por usuario para
realizar uma soma. Use o join().

 * @author kaioe
 */
public class main {
    public static void main(String[] args) throws InterruptedException {
        List<Integer> vetor = new ArrayList<>();
        Thread t1 = new ThreadPegaNumeros(vetor);
        t1.start();
//      espera a thread 1 terminar para continuar a execucao
        t1.join();
        Thread t2 = new ThreadSomaNumeros(vetor);
        t2.start();
        
    }
}

class ThreadPegaNumeros extends Thread{;
    List<Integer> vetor;
    @Override
    public void run(){
//      leitura da sequencia numerica
        Scanner scan = new Scanner(System.in);
        System.out.println("Digite numeros separados por espaco: ");
        String numeros = scan.nextLine();
        
//      separa os numeros por espaco
        String[] stringNum = numeros.split(" ");
        
//      laço que converte string em Int e insere no vetor
        for(String s: stringNum){
            vetor.add(Integer.valueOf(s));
        }
        System.out.println("Numeros Digitados: " + vetor);
    }
    public ThreadPegaNumeros(List<Integer> vetor){
        this.vetor = vetor;
    }
    
}

class ThreadSomaNumeros extends Thread{;
    List<Integer> vetor;
    @Override
    public void run(){
        Integer soma = 0;
        for(Integer i: vetor){
            soma = soma + i;
        }
        System.out.println("Soma: " + soma);
    }
    public ThreadSomaNumeros(List<Integer> vetor){
        this.vetor = vetor;
    }
    
}