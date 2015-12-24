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
package individuals.fitnesspackage;

import individuals.Individual;

/**
 *
 * @author anthony
 */
public interface Fitness {
    
    /**
     * Get the individual that is refereed to by the fitness
     * @return individual to which the fitness belongs
     */
    public Individual getIndividual();

    /**
     * Set individual to which the fitness belongs
     * @param i individual to which the fitness belongs
     */
    public void setIndividual(Individual i);
    /**
     * Get Best Fitness of generation
     * @return
     */
    public double getBestFitness();
    /**
     * Get Average Fitness of generation
     * @return
     public double getAverageFitness();
    */
    /**
     * Get Standard deviation of generational fitness
     * @return
     public double getStandardDeviationFitness();
    */
    /**
     * set Best Fitness value
     * @param f
     
    public void setBestFitness(double f);
    */
    /**
     * Set Average Fitness value
     * @param f
     public void setAverageFitness(double f);
     */
    /**
     * Set standard deviation of generation
     * @param f
     public void setStandardDeviationFitness(double f);
     */
    /**
     * 
     * @return
     */
    public double getDouble();
    /**
     * 
     * @param f
     */
    public void setDouble(double f);
    /**
     * 
     * @return
     */
    public int getInt();
    /**
     * 
     * @param f
     */
    public void setInt(int f);
    /**
     * 
     * @return
     */
    public double getMaxDoubleFitness();
    /**
     * 
     * @return
     */
    public double getMinDoubleFitness();
    /**
     * 
     * @return
     */
    public int getMaxIntFitness();
    /**
     * 
     * @return
     */
    public int getMinIntFitness();
    /**
     * 
     * @param maxD
     * @return
     */
    public double setMaxDoubleFitness(int maxD);
    /**
     * 
     * @param minD
     * @return
     */
    public double setMinDoubleFitness(int minD);
    /**
     * 
     * @param maxF
     * @return
     */
    public int setMaxIntFitness(int maxF);
    /**
     * 
     * @param minF
     * @return
     */
    public int setMinIntFitness(int minF);
    /**
     * Set the default value of fitness. This can be given to
     * unevaluated individuals, such as newly created or invalids
     */
    public void setDefault();
    //GET DEFAULT??!!
   
    
}
