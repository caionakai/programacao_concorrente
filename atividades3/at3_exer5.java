/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at3_exer5;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Exercicio 5: Faca um programa multithreaded em Java que ordene um vetor
 * usando o Merge Sort recursivo. Faca com que a thread principal dispare duas
 * threads para classificar cada metade do vetor.
 * Obs: merge do geeks for geeks
 * @author Caio Nakai
 */
public class at3_exer5 {

    public static void main(String[] args) {
        int arr[] = {12, 11, 13, 5, 6, 7}; 
  
        System.out.println("Given Array"); 
        printArray(arr); 
  
        mergeSort ob = new mergeSort(); 
        ob.sort(arr, 0, arr.length-1); 
  
        System.out.println("\nSorted array"); 
        printArray(arr);  
    }
   static void printArray(int arr[]) 
    { 
        int n = arr.length; 
        for (int i=0; i<n; ++i) 
            System.out.print(arr[i] + " "); 
        System.out.println(); 
    }     


}

class mergeSort{
    void sort(int arr[], int l, int r) 
    { 
        if (l < r) 
        { 
            // Find the middle point 
            int m = (l+r)/2; 
            
            // dispara as duas threads
            Thread a = new Thread( () ->{
                sort(arr, l, m); 
            });
            a.start();
            Thread b = new Thread( () ->{
                sort(arr, m+1, r); 
            });
            b.start();
            
            // Merge the sorted halves 
            try {
                a.join();
                b.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(mergeSort.class.getName()).log(Level.SEVERE, null, ex);
            }
            merge(arr, l, m, r); 
        } 
    } 

     void merge(int arr[], int l, int m, int r) 
    { 
        // Find sizes of two subarrays to be merged 
        int n1 = m - l + 1; 
        int n2 = r - m; 
  
        /* Create temp arrays */
        int L[] = new int [n1]; 
        int R[] = new int [n2]; 
  
        /*Copy data to temp arrays*/
        for (int i=0; i<n1; ++i) 
            L[i] = arr[l + i]; 
        for (int j=0; j<n2; ++j) 
            R[j] = arr[m + 1+ j]; 
  
  
        /* Merge the temp arrays */
  
        // Initial indexes of first and second subarrays 
        int i = 0, j = 0; 
  
        // Initial index of merged subarry array 
        int k = l; 
        while (i < n1 && j < n2) 
        { 
            if (L[i] <= R[j]) 
            { 
                arr[k] = L[i]; 
                i++; 
            } 
            else
            { 
                arr[k] = R[j]; 
                j++; 
            } 
            k++; 
        } 
  
        /* Copy remaining elements of L[] if any */
        while (i < n1) 
        { 
            arr[k] = L[i]; 
            i++; 
            k++; 
        } 
  
        /* Copy remaining elements of R[] if any */
        while (j < n2) 
        { 
            arr[k] = R[j]; 
            j++; 
            k++; 
        } 
    } 
}
