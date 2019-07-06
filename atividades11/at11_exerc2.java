/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at11_exerc2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Exercicio 2: Faca um programa que calcule a soma dos elementos de uma matriz
 * MxN. Divida o programa em tarefas que somam as linhas. O programa deve
 * possibilitar especificar o numero de threads para resolver o problema.
 *
 * @author Caio Nakai
 */
public class at11_exerc2 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Scanner scan = new Scanner(System.in);;

        System.out.println("Digite o numero de threads: ");
        int numThread = scan.nextInt();;

        int[][] matrix_a = new int[][]{
            {1, 2},
            {1, 5},
            {1, 2},};
        
        int rows = matrix_a.length;

        ExecutorService executor = Executors.newFixedThreadPool(numThread);
        
        // cria uma lista de future para armazenar os resultados em forma de future
        List<Future<Integer>> futures = new ArrayList<>();
        
        // itera sobre as linhas da matriz para fazer o calculo da soma
        for (int i = 0; i < rows; i++) {
            int linha = i;
            Future<Integer> future = executor.submit(
                    new Callable<Integer>() {
                        @Override
                        public Integer call() throws Exception {
                            return sum_row_matrix(linha, matrix_a);
                        }
                    }
            );
            futures.add(future);
        }

        executor.shutdown();
        while (!executor.isTerminated()) {
        }

        int resultado = 0;
        for (Future<Integer> fut : futures) {

            resultado += fut.get();
        }

        System.out.println("Meu Deus Caio: " + resultado);

    }

    static int sum_row_matrix(int row_number, int[][] matrix) {
        int soma = 0;
        for (int i = 0; i < matrix[0].length; i++) {
            soma += matrix[row_number][i];
        }
        return soma;
    }

}