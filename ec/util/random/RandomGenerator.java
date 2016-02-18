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
package ec.util.random;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import ec.algorithms.ga.Evolve;

/**
 *
 * @author anthony
 */
public class RandomGenerator {
    
    /**
     * Returns a pseudorandom, uniformly distributed value between the
     * given least value (inclusive) and bound (exclusive).
     * 
     * @param least
     * @param bound
     * @return random number
     */
    public static int getMultiThreadedRandNumber(Evolve e,int least, int bound)
    {
    	return e.random.nextInt(bound-least) + least;
       //return ThreadLocalRandom.current().nextInt(least,bound);
    }
    
   /**
    * 
    * @param e
    * @param array
    * @return
    */
    public static int getRandomFromArray(Evolve e,ArrayList<Integer> array) 
    {
        int rnd = e.random.nextInt(array.size());
        return array.get(rnd);
    }
    
    /**
     * @param min
     * @param max
     * @return random number
     */
    public static int getRandomNumberBetween(Evolve e,int min, int max) 
    {
        return e.random.nextInt((max + 1) - min) + min;
    }


    
    
}
