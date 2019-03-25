/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prog_concorrente;

import java.util.Scanner;

/**
 * Atividade 2: Faça um programa que receba um valor indicando um número de threads a serem 
 * instanciadas e teste os dois modos de criar threads em Java. 
 * dica: use o Thread.sleep para pausar as threads por um intervalo de tempo.
 * 
 * @author Caio Nakai, RA:a1816403
 */
public class ThreadJava {
    public static void main(String args[]){
        
        Scanner scan = new Scanner(System.in);
        
        System.out.println("Digite o numero de threads: ");
        int numThread = scan.nextInt();

        for(int i=0; i<numThread; i++){
            new HelloThread().start();
        }
    }
  
}

class HelloThread extends Thread {;
    @Override
    public void run(){
        System.out.println("Hello from a Thread");
    }
}