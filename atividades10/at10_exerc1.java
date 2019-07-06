/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at10_exerc1;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

/**
 * Exercicio: Problema One-Dimensional Stencil
 * Implementação com Latch
 * @author Caio Nakai
 */
public class at10_exerc1 {
    public static void main(String[] args) throws InterruptedException {
        int n = 5;
        ArrayList<Integer> arr = new ArrayList<Integer>(n);
        ArrayList<Integer> tempArr = new ArrayList<Integer>(n);
        arr.add(1);
        tempArr.add(1);
        for(int i=0; i<n-2; i++){
            arr.add(5);
            tempArr.add(5);
        }
        arr.add(1);
        tempArr.add(1);
        System.out.println("arr: " + arr);
        
        int change = 1;
        int media = 0;
        int nIteracoes = 10;
        for(int k=0; k<nIteracoes; k++){
            CountDownLatch cdl = new CountDownLatch(n-2);
            for(int j=1; j<n-1; j++){
                if(change == 1){
                    new Worker(arr, tempArr, j, true, cdl).start();

                }else{
                    new Worker(arr, tempArr, j, false, cdl).start();
                }
            }
            cdl.await();
            
            if(change == 1)
                change = 2;
            else
                change = 1;
        }
        System.out.println("arr: " + tempArr);
        System.out.println("temparr: " + arr);
        
    }
}

class Worker extends Thread{
    ArrayList<Integer> arr;
    ArrayList<Integer> tempArr;    
    int j;
    boolean isTemp;
    CountDownLatch cdl;
    
    @Override
    public void run(){
        int media = 0;
        media = (arr.get(j-1) + arr.get(j + 1)) / 2;
        if(isTemp){
            tempArr.set(j, media);
        }else{
            arr.set(j, media);  
        }
        cdl.countDown();
    }

    public Worker(ArrayList<Integer> arr, ArrayList<Integer> tempArr, int j, boolean isTemp, CountDownLatch cdl) {
        this.arr = arr;
        this.tempArr = tempArr;
        this.j = j;
        this.isTemp = isTemp;
        this.cdl = cdl;
    }
    
    
    
}
