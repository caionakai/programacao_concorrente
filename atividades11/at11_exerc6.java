/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at11_exerc6;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Exercicio 6: Faca um programa que execute tres algoritmos de ordenacao para um
   conjunto de valores e exiba o resultado apenas do algoritmo que finalizar
   primeiro (use invokeAny).

 * @author Caio Nakai
 */
public class at11_exerc6 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        
        int numThread = 3;
        ExecutorService executor = Executors.newFixedThreadPool(numThread);
        
        int[] intArray = new int[]{ 7,3,1,0,2,4,9 };
        
        Set<Callable<int[]>> callables = new HashSet<Callable<int[]>>();
        callables.add(new Callable<int[]>(){
            public int[] call(){
                // bubble sort
                int[] vet = new int[]{ 7,3,1,0,2,4,9 };
                int aux = 0;
                int i = 0;
                for(i = 0; i<5; i++){
                    for(int j = 0; j<4; j++){
                        if(vet[j] > vet[j + 1]){
                            aux = vet[j];
                            vet[j] = vet[j+1];
                            vet[j+1] = aux;
                        }
                    }
                }
                return vet;
            }
        });
        callables.add(new Callable<int[]>(){
            public int[] call(){
                // insertion sort
                int[] vetor = new int[]{ 7,3,1,0,2,4,9 };
                int j;
                int key;
                int i;
                for (j = 1; j < vetor.length; j++)
                {
                  key = vetor[j];
                  for (i = j - 1; (i >= 0) && (vetor[i] > key); i--)
                  {
                     vetor[i + 1] = vetor[i];
                   }
                    vetor[i + 1] = key;
                }
                return vetor;
            }
        });
        callables.add(new Callable<int[]>(){
            public int[] call(){
                // selection sort
                int[] array = new int[]{ 7,3,1,0,2,4,9 };
                for (int fixo = 0; fixo < array.length - 1; fixo++) {
                  int menor = fixo;

                  for (int i = menor + 1; i < array.length; i++) {
                     if (array[i] < array[menor]) {
                        menor = i;
                     }
                  }
                  if (menor != fixo) {
                    int t = array[fixo];
                    array[fixo] = array[menor];
                    array[menor] = t;
                  }
                }            
                return array;
            }
        });
        
        int[] result = executor.invokeAny(callables);
        // printa o vetor ordenado
        for(int i=0; i<result.length; i++){
            System.out.print(" " + result[i]);
        }

        executor.shutdown();
    }
 
}
