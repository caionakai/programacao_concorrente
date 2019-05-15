/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at7_exerc7;

import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *Exercicio 7: Semáforos podem ser usadas para representar uma fila
 * Faça um código que implemente duas filas (F1 e F2) com semáforos. As threads colocadas na F1 só
    podem executar se tiver um par na F2. Nesse caso, ambas as threads na primeira fila são executadas.
 * @author Caio Nakai
 */
public class at7_exerc7 {
    public static void main(String[] args) {
        FilaThread ft = new FilaThread();
        // 1 para enfileirar na F1 e qualquer coisa pra F2
        ThreadTest tt1 = new ThreadTest(ft, 1);
        ThreadTest tt2 = new ThreadTest(ft, 2);
        ThreadTest tt3 = new ThreadTest(ft, 1);
        
        tt1.start();
        tt2.start();
        tt3.start();
    }
   
}

class ThreadTest extends Thread{
    FilaThread ft;
    int codigo_fila = 0;
    public ThreadTest(FilaThread ft, int fila){
        this.ft = ft;
        this.codigo_fila = fila;
    }
    @Override
    public void run(){
        System.out.println("Eu sou uma thread");
        try {
            sleep(2000); // espera um pouco
        } catch (InterruptedException ex) {
            Logger.getLogger(ThreadTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            if(codigo_fila == 1){
                ft.enfileiraF1();
                System.out.println("Thread da fila 1 executada");
            }else{
                ft.enfileiraF2();
                System.out.println("Thread da fila 2 executada");
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(ThreadTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
