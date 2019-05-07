/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at6_exerc1;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Exercicio 1: Implemente uma solucao com monitor para o problema do
 * Produtor-Consumidor usando um buffer circular.
 *
 * @author Caio Nakai
 */
public class at6_exerc1 {
    public static void main(String[] args) {
        Buffer b = new Buffer(10);
        Produtor p = new Produtor(b);
        Consumidor c = new Consumidor(b);
        
        p.start();
        c.start();
        

    }
}
