/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at3_exer2;

/**
 * Exercicio 2: Faca um programa em Java para testar um conjunto de
 * operacoes sobre um grupo de threads. Use o ThreadGroup.

 * @author Caio Nakai
 */
public class at3_exer2 {
    public static void main(String[] args) {
        ThreadGroup group = new ThreadGroup("MyThreadsGroup");
        Thread t1 = new Thread(group, "oi");
        t1.start();
        System.out.println("Numeros de threads ativas no grupo: " + group.activeCount());
        System.out.println("Numero estimado de grupos ativos no grupo e seus subgrupos: " + group.activeGroupCount());
        System.out.println("Prioridade máxima do grupo: " + group.getMaxPriority());
        System.out.println("Testa se o grupo é daemon thread: " + group.isDaemon());
        group.setMaxPriority(5);
        System.out.println("Prioridade máxima do grupo: " + group.getMaxPriority());
        
        group.destroy();
        
    
    }

}

