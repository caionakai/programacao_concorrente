/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at6_exerc1;

/**
 *
 * @author kaioe
 */
public class Consumidor extends Thread{
    Buffer buf;
    public Consumidor(Buffer buf){
        this.buf = buf;
    }
    
    public void run(){
        while(true){
            System.out.println("Consumi: " + buf.removeFirst());
        }
    }
}
