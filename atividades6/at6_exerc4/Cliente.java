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
public class Cliente extends Thread{
    Barbearia ba;
    
    public Cliente(Barbearia ba){
        this.ba = ba;
    }
    
    @Override
    public void run(){
            // só senta se tiver cadeira livre
            if(ba.clientes_esperando < ba.cadeiras){
                System.out.println("Cliente chegou!!");
                ba.clientes_esperando++;
                // se for igual a 1 então só tem este cliente, consequentemente o barbeiro estara dormindo
                if(ba.clientes_esperando == 1){
                    System.out.println("Cliente acordando o Barbeiro...");
                    synchronized(ba){
                        ba.notify(); // acorda o barbeiro
                    }
                }
                try {
                    System.out.println("Cliente Esperando...");
                    while(!ba.signaled){
                        synchronized(ba){
                            ba.wait();                        
                        }
                    }

                    ba.clientes_esperando--;
                    System.out.println("Cliente atendido e indo embora...  ");

                } catch (InterruptedException ex) {
                    Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else{
                System.out.println("Não tinha cadeira pra mim... ");
            }
    }
}
