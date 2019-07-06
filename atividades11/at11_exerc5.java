/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at11_exerc5;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Exercicio 5: Faca um programa que possibilite agendar uma tarefa para ser executada
   em um horario especıfico.
 * @author Caio Nakai
 */
public class at11_exerc5 {
    public static void main(String[] args) throws ParseException, InterruptedException, ExecutionException {
        // codigo p/ data
        SimpleDateFormat dateformat2 = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        String strdate2 = "24-06-2019 00:57:42";
        Date determined_date = dateformat2.parse(strdate2);
        Date current_date = new Date();
        long delay = (determined_date.getTime() - current_date.getTime());
        
        
        // codigo p/ agendar tarefa
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(5);
        ScheduledFuture sf = 
            ses.schedule(
                new Callable(){
                    public Object call(){
                        System.out.println("Tarefa Agendada Executada");
                        return "anything!";
                    }
                }, 
                delay, 
                TimeUnit.MILLISECONDS
            );
        
        System.out.println("Result :" + sf.get());
        ses.shutdown();
        
    }
}
