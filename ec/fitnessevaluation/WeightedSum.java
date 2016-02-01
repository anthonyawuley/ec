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
package ec.fitnessevaluation;

import ec.individuals.populations.Population;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;

import ec.algorithms.alps.layers.Layer;
import ec.util.Constants;
import ec.util.statistics.BasicStatistics;

/**
 * Weighted sum fitness sums up all objectives and assigns multiples it by a factor
 * 
 * @author Anthony Awuley
 */
public abstract class WeightedSum extends FitnessExtension{
    
    /** */
    private ArrayList<Integer> bestFitnessIndividualsOfGeneration;
    /** */
	@SuppressWarnings("unused")
	private ArrayList<Integer> bestFitnessIndividualsForStatistics;
    /** */
    private double averageFitnessPerGeneration, bestFitnessOfGeneration;

    /**
     * Evaluate individuals in a population - population of a generation
     * @param pop 
     */
    @Override
	@SuppressWarnings("unchecked")
	public void calculateSimpleFitness(
			Population pop, 
			final int run, 
			final int generation,
			BasicStatistics stats,
			Properties p)
    {
       
       //this.generationFitness = calcGenerationalFitness(pop,p);
       calcGenerationalFitness(pop,p);
       
       ArrayList<Double> sortedFitness = (ArrayList<Double>) this.getGenerationFitness().clone();
       Collections.sort(sortedFitness);
    
          
       /*
        * select top individuals based on user set param
        * sort 2 times the number of elit individuals (this is to prevent overhead involved in sorting all individuls in the population)
        * stat individuals could be obtained from this
        */
       
       int selectBest = Integer.parseInt(p.getProperty(Constants.ELITE_SIZE))*2<=0?
    		   Integer.parseInt(p.getProperty(Constants.NUMBER_INDIVIDUALS_PRINT)):
    			   Integer.parseInt(p.getProperty(Constants.ELITE_SIZE))*2;
       
       this.bestFitnessIndividualsOfGeneration = 
    		   minimumFitness(this.getGenerationFitness(),
    		                  sortedFitness,
    		                  selectBest);
       
       
       //this.bestFitnessIndividualsOfGeneration = minimumFitness(this.generationFitness,sortedFitness.get(0));
       this.averageFitnessPerGeneration = this.getTotalFitness()/pop.size();
       this.bestFitnessOfGeneration     = sortedFitness.get(0);
    
       //this could be deprecated
      
       pop.setBestIndividual(pop.get(this.bestFitnessIndividualsOfGeneration.get(0))); 
       //this contains records of all best individuals
       pop.setBestIndividuals(pop.get(this.bestFitnessIndividualsOfGeneration));
       
       this.setBestFitness(this.bestFitnessOfGeneration);
       this.setAverageFitness(this.averageFitnessPerGeneration);
       this.setStandardDeviationFitness(getStandardDeviationOfFitness(this.getGenerationFitness()));
       
       //print statistics
       stats.printStatsReport(
    		   pop,
    		   p,
    		   //this.bestFitnessIndividualsForStatistics,
    		   this.bestFitnessIndividualsOfGeneration,
    		   run,
    		   generation,
    		   this);
       
    }
 
    
    /**
     * Overloaded method: notice introduction of layer 
     */
    @Override
	@SuppressWarnings("unchecked")
	public void calculateSimpleFitness(Population pop, final Layer layer, 
			final int generation,final int run, BasicStatistics stats,Properties p,Boolean statsFlag)
    {
       //this.generationFitness = calcGenerationalFitness(pop,p);
       calcGenerationalFitness(pop,p);
       
       ArrayList<Double> sortedFitness = (ArrayList<Double>) this.getGenerationFitness().clone();
       Collections.sort(sortedFitness);

       /*
        * select top individuals based on user set param
        * sort the 25% of the population greater than 10 (in future reduce number to sort)
        */
       this.bestFitnessIndividualsOfGeneration = 
    		   minimumFitness(this.getGenerationFitness(),
    		                  sortedFitness,
    		                  (int) (pop.size()<10?pop.size():(Math.ceil(Integer.parseInt(p.getProperty(Constants.POPULATION_SIZE)))/4)));
       
       
       //this.bestFitnessIndividualsOfGeneration = minimumFitness(this.generationFitness,sortedFitness.get(0));
       this.averageFitnessPerGeneration = this.getTotalFitness()/pop.size();
       this.bestFitnessOfGeneration     = sortedFitness.get(0);
    
      
       pop.setBestIndividual(pop.get(this.bestFitnessIndividualsOfGeneration.get(0))); 
       //this contains records of all best individuals
       pop.setBestIndividuals(pop.get(this.bestFitnessIndividualsOfGeneration));
       
       this.setBestFitness(this.bestFitnessOfGeneration);
       this.setAverageFitness(this.averageFitnessPerGeneration);
       this.setStandardDeviationFitness(getStandardDeviationOfFitness(this.getGenerationFitness()));
     
       //print statistics
       if(statsFlag)
       {
       stats.printStatsReport(
    		   pop,
    		   p,
    		   //this.bestFitnessIndividualsForStatistics,
    		   this.bestFitnessIndividualsOfGeneration,
    		   layer,
    		   generation,
    		   run,
    		   this);
       }
       
    }
    
    
    /**
     * 
     * @return 
     */
    @Override
	public ArrayList<Integer> getBestIndividualsOfGeneration()
    {
       return this.bestFitnessIndividualsOfGeneration;
    }
    
    


	
}
