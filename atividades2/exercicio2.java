/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atividades2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Exercicio 2: Faca uma thread Java que realize a leitura de um arquivo
texto com frases e exiba as frases a cada 10 segundos.
 * Nome: Caio Nakai
 * @author a1816403
 */
public class exercicio2 {

    public static void main(String[] args) {
        Thread t1 = new HelloThread2();
        t1.start();
    }
}
class HelloThread2 extends Thread {;
    @Override
    public void run(){        
        try {
//          colocar o caminho do arquivo texto aqui
            FileReader arq = new FileReader("/home/todos/alunos/cm/a1816403/NetBeansProjects/prog_concorrente/src/atividades2/text.txt");
            BufferedReader lerArq = new BufferedReader(arq);
//          le uma linha do arquivo
            String linha = lerArq.readLine();
//            laço que realiza a leitura das linhas do arquivo 
            while (linha != null) {
//              printa uma linha
                System.out.printf("%s\n", linha);
//              espera 10 segundos antes de continuar a execucao
                Thread.sleep(10000);
                linha = lerArq.readLine(); // lê da segunda até a última linha
                if(Thread.interrupted()){
                    break;
                }
                
            }
            } catch (FileNotFoundException ex) {
            Logger.getLogger(HelloThread2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(HelloThread2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(HelloThread2.class.getName()).log(Level.SEVERE, null, ex);
        }

            arq.close();

    }
}