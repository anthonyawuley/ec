/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util.random;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author anthony
 */
public class RandomGenerator {
    
    /**
     * @param x
     * @param y
     * @return random number based on multi-threaded environment
     */
    public static int getMultiThreadedRandNumber(int x, int y)
    {
       return ThreadLocalRandom.current().nextInt(x,y);
    }
    
   /**
    *  
    * @param array
    * @return
    */
    public static int getRandomFromArray(ArrayList<Integer> array) 
    {
        int rnd = new Random().nextInt(array.size());
        return array.get(rnd);
    }
    
    /**
     * @param min
     * @param max
     * @return random number
     */
    public static int getRandomNumberBetween(int min, int max) 
    {
        Random rnd = new Random();
        return rnd.nextInt((max + 1) - min) + min;
    }

    public double nextDouble() {
        return new Random().nextDouble();
    }
    
    
    
}
