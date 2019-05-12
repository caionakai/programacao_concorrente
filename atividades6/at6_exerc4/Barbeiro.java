/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at6_exerc4;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kaioe
 */
public class Barbeiro extends Thread{
    Barbearia ba;
    
    public Barbeiro(Barbearia ba){
        this.ba = ba;
    }
    
    @Override
    public void run(){
//        synchronized(ba){
            while(true){
                while(ba.clientes_esperando == 0){
                    try {
                        System.out.println("Barbeiro dormindo...");
                        synchronized(ba){
                            ba.wait();
                            
                        }
                        System.out.println("Barbeiro acordou...");
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Barbeiro.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                try {
                    // espera 5seg e termina o corte de cabelo
                    System.out.println("Barbeiro Cortando Cabelo...");
                    Thread.sleep(1000);
                    synchronized(ba){
                        ba.signaled=true;
                        ba.notify();
                        
                    }
                    System.out.println("Corte finalizado...");
                } catch (InterruptedException ex) {
                    Logger.getLogger(Barbeiro.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
//        }
    }
}
