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
package ec.operator;

import ec.individuals.Chromosome;
import ec.individuals.Individual;
import ec.individuals.populations.Population;

import java.util.ArrayList;

/**
 *
 * @author  Anthony Awuley
 */
public abstract class MutationModule extends Operator {
    /** */
    private String mutationType;
    /** */
    private final String mutationOperation ="mutation operation";
    /** */
    private double mutationRate;
    
    
    public  MutationModule (String type)
    {
       this.mutationType = type;
    }
   
    /**
     * Returns the mutation policy.
     * @return mutation policy
     */
    public String getMutationPolicy() 
    {
        return mutationOperation;
    }

    /**
     * Returns the mutation rate.
     * @return mutation rate
     */
    public double getMutationRate() 
    {
        return this.mutationRate;
    }
    
    /**
     * 
     * @param rate 
     */
    public  void getMutationRate(double rate) 
    {
        this.mutationRate = rate;
    }
   
    
   /**
    * perform mutation operation for canonical GA
    * 
    * @param p
    * @param c1
    * @param parentId
    * @return
    */
    public abstract ArrayList<Individual> performMutationOperation(
    		Population p, 
    		Chromosome c1,
    		int parentId);
    
    /**
     * perform mutation operation for ALPS GA
     * 
     * @param p
     * @param c1
     * @param parentId
     * @param ages
     * @param replacementType
     * @return
     */
    public abstract ArrayList<Individual> performMutationOperation(
    		Population p, 
    		Chromosome c1,
    		int parentId,
    		ArrayList<Double> ages,
    		String replacementType);
    
    public String getSpecificModuleType()
    {
       return this.mutationType;
    }

     /**
     * Get operation that operator performs
     * @return operation
     */
    @Override
	public String getOperation()
    {
       return this.mutationOperation;
    }
    
    
}
