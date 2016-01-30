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
package ec.individuals.fitnesspackage;

import java.util.ArrayList;


/**
 *
 * @author anthony
 */
public interface PopFitnessInterface {
    
	
    /**
     * @return
     */
	public ArrayList<Double> getGenerationFitness();
	/**
	 * @param generationFitness the generationFitness to set
	 */
	public void setGenerationFitness(ArrayList<Double> generationFitness);
	/**
	 * 
	 * @return
	 */
    public double getBestFitness();
    /**
     * Get Average Fitness of generation
     * @return
     */
    public double getAverageFitness();
    /**
     * Get Standard deviation of generational fitness
     * @return
     */
    public double getStandardDeviationFitness();
    /**
     * set Best Fitness value
     * @param f
     */
    public void setBestFitness(double f);
    /**
     * Set Average Fitness value
     * @param f
     */
    public void setAverageFitness(double f);
    /**
     * Set standard deviation of generation
     * @param f
     */
    public void setStandardDeviationFitness(double f);
    
    /**
     * Set the default value of fitness. This can be given to
     * unevaluated individuals, such as newly created or invalids
     */
    //public void setDefault();
    //GET DEFAULT??!!
   
    
}
