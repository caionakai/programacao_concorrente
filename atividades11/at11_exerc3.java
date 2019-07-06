/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at11_exerc3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Exercicio 3: Faca um programa concorrente para multiplicar duas matrizes.

 * @author Caio Nakai
 */
public class at11_exerc3 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int[][] matrix_a = new int[][]{
            {1, 2},
            {3, 4},
        };
        int[][] matrix_b = new int[][]{
            {-1, 3},
            {4, 2},
        };
        
        
        int rows_a = matrix_a.length;
        int cols_b = matrix_b[0].length;
        
        int numThread = 3;
        
        ExecutorService executor = Executors.newFixedThreadPool(numThread);
        
        List<Future<int[]>> futures = new ArrayList<>();
        
        // realiza o calculo da multiplicacao linha por linha
        for(int i=0; i<rows_a; i++){
            int linha = i;
            Future<int[]> future = executor.submit(
                    new Callable<int[]>() {
                        @Override
                        public int[] call() throws Exception {
                            return get_matrix_row_multiplied(linha, matrix_a, matrix_b);
                        }
                    }
            );
            futures.add(future);            
        }
        
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        
        int[][] matrix_result = new int[rows_a][cols_b];
        int count = 0;
        
        // pega o resultado armazenado na lista de future e coloca na matriz resultante
        for (Future<int[]> fut : futures) {
            matrix_result[count] = fut.get();
            count++;
        }
        print_matrix(matrix_result);
    }
    
    // calcula a multiplicacao de matriz de acordo com a linha especificada
    static int[] get_matrix_row_multiplied(int row_number, int[][] matrix_a, int[][] matrix_b){
        int cols_a = matrix_a[0].length;
        int rows_b = matrix_b.length;
        int[] row_multiplied = new int[cols_a];
        int soma;
        
        for(int i=0; i<cols_a; i++){
            soma = 0;
            for(int j=0; j<rows_b; j++){
                soma = soma + (matrix_a[row_number][j] * matrix_b[j][i]);
            }
            row_multiplied[i] = soma;
        }
        
        return row_multiplied;
    }

    static void print_matrix(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(" " + matrix[i][j]);
            }
            System.out.println("");
        }
    }
}
