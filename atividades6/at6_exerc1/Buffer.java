/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at6_exerc1;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kaioe
 */
public class Buffer{
    int first = 0;
    int last = 0;
    int tam_max = 0;
    int tam_atual = 0;
    int[] lista;

    public Buffer(int tamanho) {
        lista = new int[tamanho];
        this.tam_max = tamanho;
    }

    public synchronized void add(int numero) {
        while(!(tam_atual < tam_max)){
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        tam_atual++; // incrementa a variavel que guarda o tamanho do buffer
        lista[last] = numero;
        last = (last + 1) % tam_max; // se last+1 < tam_max muda indice, senao retorna 0
        notifyAll();
    }

    public synchronized int removeFirst() {
        while(tam_atual == 0) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        first++;
        tam_atual--;
        notifyAll();
        return lista[first-1];
    }

    public void printList() {
        if (tam_atual != 0) {
            for (int i = first; i < last; i++) {
                System.out.println("Num:" + lista[i]);
            }
        } else {
            System.out.println("Lista vazia X_X'");
        }
    }
}

