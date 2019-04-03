/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercicio3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kaioe
 */
public class codigo_ex2 {
    public static void main(String[] args) {
        
    }
}
class HelloThread2 extends Thread {;
    @Override
    public void run(){        
        FileReader arq = null;
        try {
//          colocar o caminho do arquivo texto aqui
            arq = new FileReader("C:\\Users\\kaioe\\Desktop\\pesquisa.txt");
            BufferedReader lerArq = new BufferedReader(arq);
//          le uma linha do arquivo
            String linha = lerArq.readLine();
//            laço que realiza a leitura das linhas do arquivo 
            while (linha != null) {
//              printa uma linha
                System.out.printf("%s\n", linha);
//              espera 10 segundos antes de continuar a execucao
//                Thread.sleep(10000);
                linha = lerArq.readLine(); // lê da segunda até a última linha
                if(Thread.interrupted()){
                    break;
                }
                
            }
            } catch (FileNotFoundException ex) {
            Logger.getLogger(HelloThread2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(HelloThread2.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            arq.close();
        } catch (IOException ex) {
            Logger.getLogger(HelloThread2.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}