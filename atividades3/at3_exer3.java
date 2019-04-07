
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kaioe
 */
public class at3_exer3 {
    public static void main(String[] args) throws InterruptedException {
        List<Thread> vetor = new ArrayList<>();
        List<Integer> resultado = new ArrayList<>(); // onde serao armazenados os numeros primos
        int numeroFinal = 100__000; // vai calcular num primo de 0 ate 100k
        int numThread = 4; // quatro threads trabalhando
        int intervalo = numeroFinal / numThread; // divisao do trabalho
//      cria as threads passando a carga de trabalho de cada uma e insere no vetor 
        for(int i=1; i <= numThread; i++){
            if(i==1){ // caso incial
                vetor.add(new ThreadCalcPrimo(0, intervalo, resultado));
            }else{
                vetor.add(new ThreadCalcPrimo(intervalo*(i-1), intervalo*i, resultado));
            }
        }
        // starta as threads
        for(Thread t: vetor){
            t.start();
        }
        // espera todas acabar
        for(Thread t: vetor){
            t.join();
        }
        System.out.println("Numeros: " + resultado);
    }
}
class ThreadCalcPrimo extends Thread {;
    int inicio, fim;
    List<Integer> resultado;
    @Override
    public void run(){        
        for(int i = inicio; i<=fim; i++){ // for que percorre todo o intervalo
            boolean isPrimo = true; // reseta variavel
            for(int j=2; j<i; j++){ // for que verifica se o num é primo
                if((i%j) == 0) { // se o resto da divisao por qualquer outro numero alem de 1 e ele mesmo for 0 entao nao é primo
                    isPrimo = false; // seta falso na variavel pois nao eh primo
                }
            }
            if(isPrimo){
                resultado.add(i); // adiciona no vetor resultado caso seja primo
            }
        }
    }
    public ThreadCalcPrimo(int inicio, int fim, List<Integer> resultado){
        this.inicio = inicio;
        this.fim = fim;
        this.resultado = resultado;
    }
}
