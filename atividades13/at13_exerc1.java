/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at13_exerc1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.Math.ceil;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Exercicio 1: Faca um programa usando Threads e ConcurrentMap para
   calcular a frequencia de cada letra em um texto.
 * @author Caio Nakai
 */
public class at13_exerc1 {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // quantidade de threds
        int numThread = 3;
        
        // leitura do arquivo
        String content = null;
        File file = new File(System.getProperty("user.dir") + "/src/at13_exerc1/caio.txt");
        FileReader reader = null;
        reader = new FileReader(file);
        char[] chars = new char[(int) file.length()];
        reader.read(chars);
        content = new String(chars);
        reader.close();
        
        // divisao de tarefas e start das threads
        int split_task = (int)(file.length() / numThread);

        ConcurrentHashMap<String, Integer> m = new ConcurrentHashMap();
        
        for(int i=0; i<numThread; i++){
            if(i == numThread-1){
                new ThreadWorker(content, i*split_task, (int) file.length(), m).start();
            }else{
                new ThreadWorker(content, i*split_task, (i*split_task) + split_task, m).start();
            }

        }
        System.out.println("m: " + m );

    }
}

class ThreadWorker extends Thread{
    String string_splitted;
    int init;
    int end;
    ConcurrentHashMap<String, Integer> chm;

    public ThreadWorker(String temp, int init, int end, ConcurrentHashMap<String, Integer> chm) {
        this.string_splitted = temp;
        this.init = init;
        this.end = end;
        this.chm = chm;
    }
    
    @Override
    public void run(){
        int count = 0;
        for(int i=init; i<end; i++){
            // se tiver o valor
            if(chm.containsKey(string_splitted.substring(i, i+1))){
                count = (int) chm.get(string_splitted.substring(i, i+1));
                count++;
                chm.put(string_splitted.substring(i, i+1), count);
            }else{
                chm.putIfAbsent(string_splitted.substring(i, i+1), 1);
            }
        }        
    }
    
    
}

