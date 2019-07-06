/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at11_exerc1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import javafx.concurrent.Worker;

/**
 * Exercicio 1: Faca um programa que localize o maior valor em um vetor.  
 * Divida oprograma em tarefas que localizam o maior valor em um segmento dovetor.  
 * O programa deve possibilitar especificar o numero de tarefas e on ́umero de threads para resolver o problema.
 * @author Caio Nakai
 */
public class at11_exerc1 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Scanner scan = new Scanner(System.in);
        
        System.out.println("Digite o numero de threads: ");
        int numThread = scan.nextInt();
        
        System.out.println("Digite o numero de tarefas: ");
        int numTasks = scan.nextInt();
        
        List<Integer> array = new ArrayList<>(Arrays.asList(1,2,3,4,5,25,7,8,9,10));
        
        
        new SimpleThreadPool(numThread, numTasks, array);
        
        
    }
}


class SimpleThreadPool{
    int numThread;
    int numTasks;
    List<Integer> array;
    
    public int getHighest(int initial_idx, int final_idx, List<Integer> array){
        int maior;
        maior = array.get(initial_idx);
        
        for(int i = initial_idx + 1; i<= final_idx; i++){
           if(array.get(i) > maior){
               maior = array.get(i);
           }
        }
        
        return maior;
    }
    
    public SimpleThreadPool(int numThread, int numTasks, List<Integer> array) throws InterruptedException, ExecutionException{
        this.numThread = numThread;
        this.numTasks = numTasks;
        this.array = array;
        
        ExecutorService executor = Executors.newSingleThreadExecutor();
        
        int arraySize = array.size();
        int dividedTasks;
        dividedTasks = arraySize/numTasks;
        
        Set<Callable<Integer>> callables = new HashSet<Callable<Integer>>();
        
        for(int i=0; i<numThread; i = i + dividedTasks){
            int initial_idx = i;
            int final_idx = i+dividedTasks;
            callables.add(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    return getHighest(initial_idx, final_idx, array);
                }
            });
        }
        
        List<Future<Integer>> futures = executor.invokeAll(callables);
        List<Integer> result = new ArrayList<>();
        for(Future<Integer> future : futures){
            result.add(future.get());
        }
        result.sort(Collections.reverseOrder());
        System.out.println("Maior: " + result.get(0));
        
        executor.shutdown();
        while(!executor.isTerminated()){}
    }
        
}
    
    