/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at8_exerc2;

import java.util.concurrent.Semaphore;

/**
 * Exercicio 2: Implementar 3 solucoes distintas para o jantar dos filosofos
   que nao causem deadlock.
 * @author Caio Nakai
 */
public class at8_exerc2 {
    public static void main(String[] args) {
        
    }
}

/*// Primeira Solucao pega dois chopsticks de uma unica vez
class DinningResource {
    int numberResources = 0; // numberPhilosophers
    Semaphore mx = new Semaphore(1);
    Semaphore[] fork = null;
    
    public DinningResource(int initResources){
        numberResources = initResources;
        fork  = new Semaphore[numberResources];
        for(int i=0; i<numberResources; i++){
            fork[i] = new Semaphore(1);
        }
        
    }
    
    public void take(int idPhilosopher) throws InterruptedException{
        mx.acquire();
        fork[idPhilosopher].acquire();
        fork[(idPhilosopher+1)%numberResources].acquire();
        mx.release();
    }
    
    public void release(int idPhilosopher){
        fork[idPhilosopher].release();
        fork[(idPhilosopher+1)%numberResources].release();
    }
}*/

/*// Segunda Solucao pega sempre o chopstick com menor index
class DinningResource {
    int numberResources = 0; // numberPhilosophers
    Semaphore[] fork = null;
    
    public DinningResource(int initResources){
        numberResources = initResources;
        fork  = new Semaphore[numberResources];
        for(int i=0; i<numberResources; i++){
            fork[i] = new Semaphore(1);
        }
        
    }
    
    public void take(int idPhilosopher) throws InterruptedException{
        if(idPhilosopher != 0){
            fork[idPhilosopher-1].acquire();
            fork[idPhilosopher].acquire();
        }else{
            fork[idPhilosopher].acquire();
            fork[(idPhilosopher+1)%numberResources].acquire();            
        }
    }
    
    public void release(int idPhilosopher){
        fork[idPhilosopher].release();
        fork[(idPhilosopher+1)%numberResources].release();
    }
}
*/

// Terceira Solucao os filosofos pares pegam o hashi da direita primeiro
// enquanto os filosofos impares pegam o da esquerda primeiro
class DinningResource {
    int numberResources = 0; // numberPhilosophers
    Semaphore[] fork = null;
    
    public DinningResource(int initResources){
        numberResources = initResources;
        fork  = new Semaphore[numberResources];
        for(int i=0; i<numberResources; i++){
            fork[i] = new Semaphore(1);
        }
        
    }
    
    public void take(int idPhilosopher) throws InterruptedException{
        if(idPhilosopher % 2 == 0){
            fork[idPhilosopher].acquire();
            fork[(idPhilosopher+1)%numberResources].acquire();            
        }else{
            fork[idPhilosopher-1].acquire();
            fork[(idPhilosopher)%numberResources].acquire();            
        }
    }
    
    public void release(int idPhilosopher){
        if(idPhilosopher % 2 == 0){
            fork[idPhilosopher].release();
            fork[(idPhilosopher+1)%numberResources].release();            
        }else{
            fork[idPhilosopher-1].release();
            fork[(idPhilosopher)%numberResources].release();
        }
    }
}