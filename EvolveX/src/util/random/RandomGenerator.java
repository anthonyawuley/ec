/*******************************************************************************
 * Copyright (c) 2014, 2015 
 * Anthony Awuley - Brock University Computer Science Department
 * All rights reserved. This program and the accompanying materials
 * are made available under the Academic Free License version 3.0
 * which accompanies this distribution, and is available at
 * https://aawuley@bitbucket.org/aawuley/evolvex.git
 *
 * Contributors:
 *     ECJ                     MersenneTwister & MersenneTwisterFast (https://cs.gmu.edu/~eclab/projects/ecj)
 *     voidException      Tabu Search (http://voidException.weebly.com)
 *     Lucia Blondel       Simulated Anealing 
 *     
 *
 *        
 *******************************************************************************/
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
