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
import ec.individuals.Gene;
import ec.individuals.Individual;
import ec.individuals.populations.Population;

import java.util.ArrayList;

/**
 *
 * @author anthony
 */
public abstract class CrossoverModule extends Operator {
    
    private String crossoverType;
    private final String crossoverOperation ="crossover operation";
    private double crossoverRate;
    private int childrenAdded;
    
    
    public  CrossoverModule(String type)
    {
       this.crossoverType = type;
    }
    
   
    /**
     * @param p
     * @return 
     */
    public abstract ArrayList<Individual> performCrossoverOperation(
    		Population p, 
    		Chromosome c1, 
    		Chromosome c2,
    		ArrayList<Integer> tournamentIndividuals, 
    		int numberOfChildrenToAdd);
    
   /**
    * OVERLOAD ALPS add age
    * @param p
    * @param c1
    * @param c2
    * @param tournamentIndividuals
    * @param numberOfChildrenToAdd
    * @param age[] age of parents
    * @param replacementType [steadyState:Generational:etc]
    * @return
    */
    public abstract ArrayList<Individual> performCrossoverOperation(
    		Population p, 
    		Chromosome c1, 
    		Chromosome c2,
    		ArrayList<Integer> tournamentIndividuals, 
    		int numberOfChildrenToAdd,
    		ArrayList<Double> ages,
    		String replacementType);
    
    /**
     * Returns the crossover policy.
     * @return crossover policy
     */
    public String getCrossoverPolicy() 
    {
        return crossoverOperation;
    }

    /**
     * Returns the crossover rate.
     * @return crossover rate
     */
    public double getCrossoverRate() 
    {
        return this.crossoverRate;
    }
    /**
     * @deprecated
     * @param number
     */
    @Deprecated
	public void setChildrenAdded(int number)
    {
       this.childrenAdded = number;
    }
    
    /**
     * @deprecated
     * @return
     */
    @Deprecated
	public int getChildrenAdded()
    {
      return this.childrenAdded;
    }

    
    /**
     * 
     * @param rate 
     */
    public void setCrossoverRate(double rate) 
    {
        this.crossoverRate = rate;
    }
    
    public String getSpecificModuleType()
    {
       return this.crossoverType;
    }
    
        
    /**
     * 
     * @param child
     * @param gene
     * @return 
     */
    public int returnAvailableIndex(Chromosome child, Gene gene)
    {
        for(int i=0; i<child.getGenes().size(); i++)
            if(child.getGenes().get(i).getId() == gene.getId())
                return i;
        
        return -1;
    }
   
    /**
     * Get operation that operator performs
     * @return operation
     */
    @Override
	public String getOperation()
    {
       return this.crossoverOperation;
    }
    
    
}
