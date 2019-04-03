/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercicio3;

/**
 * * Exercicio: Faca um programa Java que envia interrupcoes para as
threads dos exerćıcios anteriores.  As threads devem fazer o
tratamento dessas interrup̧ccoes e realizar uma finalizacao
limpa.
 * Nome: Caio Nakai
 * @author kaioe
 */
public class main {
    public static void main(String[] args) {
//      instanciando e executando a thread do exercicio 2
        Thread t1 = new HelloThread2();
        t1.start();
        t1.interrupt();
        
//      instanciando e executando a thread do exercicio 1
        Thread t2 = new HelloThread();
        t2.start();
        t2.interrupt();
    }
}
