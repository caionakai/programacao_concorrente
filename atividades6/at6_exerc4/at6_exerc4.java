/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at6_exerc4;

/**
 * Exercicio 4: Implemente uma solucao para o problema do Barbeiro
Dorminhoco usando monitores.
* Considerando: 
* A barbearia tem:
    - um barbeiro
    - uma cadeira de barbeiro
    - algumas cadeiras para os fregueses esperarem
 * Se o movimento estiver fraco (nenhum freguês), o barbeiro senta na sua cadeira e dorme;
 * Quando um freguês chega no salão vazio, ele tem que acordar o barbeiro;
 * Se outros fregueses chegam enquanto o barbeiro está ocupado, eles:
    - Sentam na cadeira de espera
    - Vão embora, se não houver cadeira vazia.
    
 * @author Caio Nakai
 */
public class at6_exerc4 {
    public static void main(String[] args) {
        Barbearia ba = new Barbearia();
        Barbeiro bo = new Barbeiro(ba);
        Cliente c = new Cliente(ba);
        Cliente c2 = new Cliente(ba);
        Cliente c3 = new Cliente(ba);

        
        bo.start();
        c.start();
        c2.start();
        c3.start();

        
    }
}
