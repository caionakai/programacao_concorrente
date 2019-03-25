/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prog_concorrente;

/**
 * Atividade 1: Implemente o exemplo anterior usando Lambda Expression.
 * 
 * @author  Caio Nakai, RA:a1816403
 */
public class HelloRunnableLambda {
    public static void main(String[] args){
        new Thread( () -> {
            System.out.println("Hello World from a thread. LAMBDA, LAMBDA");
            System.out.println("My ID is " + Thread.currentThread().getId());
        }).start();
    }
}
