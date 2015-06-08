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
package algorithms.simulatedannealing.core;

/**
 * @author Lucia Blondel
 */

public class Map {

        private long seed;
        private double startTemperature;
        private double alpha;
        private boolean twoOptInitial;
        
        /**
         * Constructor
         * @param seed
         * @param start temperature
         * @param alpha
         */
        public Map(final long seed, final double startTemperature, final double alpha, final boolean twoOptInitial) {
                this.seed = seed;
                this.startTemperature = startTemperature;
                this.alpha = alpha;
                this.twoOptInitial = twoOptInitial;
        }
        
        /**
         * @return the seed of the random
         */
        public long getSeed() {
                return seed;
        }
        
        /**
         * @return the start temperature
         */
        public double getStartTemperature() {
                return startTemperature;
        }
        
        /**
         * @return the alpha, i.e how much the temperature decrease every
         * time we finish the simulated annealing for the previous one
         */
        public double getAlpha() {
                return alpha;
        }
        
        /**
         * @return true or false
         */
        public boolean getTwoOptInitial() {
                return twoOptInitial;
        }
}

