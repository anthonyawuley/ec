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


import ec.individuals.Individual;
import ec.individuals.populations.Population;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;

import ec.algorithms.alps.layers.Layer;
import ec.util.Constants;
import ec.util.statistics.BasicStatistics;

/**
 *
 * @author Anthony Awuley
 */
public class FitnessExperimental extends FitnessExtension{
    /** */
    private ArrayList<Integer> bestFitnessIndividualsOfGeneration;
    /** */
    @SuppressWarnings("unused")
	private ArrayList<Integer> bestFitnessIndividualsForStatistics;
    /** */
    private double averageFitnessPerGeneration, bestFitnessOfGeneration;
    /** */
	@SuppressWarnings("unused")
	private long startTime;
	/** */
	@SuppressWarnings("unused")
    private long endTime;
  
    /**
     * Evaluate individuals in a population - population of a generation
     * @param pop 
     */
    @Override
	@SuppressWarnings("unchecked")
	public void calculateSimpleFitness(
			Population pop, final int run, final int generation,BasicStatistics stats,Properties p)
    {
       //double averageFitness = 0;
       this.startTime = System.currentTimeMillis();
       this.endTime   = System.currentTimeMillis();
       
       calcGenerationalFitness(pop,p); //set this.generationFitness
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
       
       
       //select top individuals based on user set param
       /*
       this.bestFitnessIndividualsForStatistics =
    		   minimumFitness(this.generationFitness,
    				   sortedFitness,
    				   Integer.parseInt(this.getProperties().
    						   getProperty(Constants.NUMBER_INDIVIDUALS_PRINT)));
       */
       
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
 
    
    @Override
	@SuppressWarnings("unchecked")
	public void calculateSimpleFitness(
			Population pop, 
			final Layer layer, 
			final int generation,
			final int run,
			BasicStatistics stats,
			Properties p,
			Boolean statsFlag)
    {
       //double averageFitness = 0;
       this.startTime = System.currentTimeMillis();
       this.endTime   = System.currentTimeMillis();
       
       calcGenerationalFitness(pop,p); //set this.generationFitness
       
       ArrayList<Double> sortedFitness = (ArrayList<Double>) this.getGenerationFitness().clone();
       
       Collections.sort(sortedFitness);
       
       /*
        * select top individuals based on user set param
        * sort 25% of the population  (this is to prevent overhead involved in sorting all individuls in the population)
        */
       this.bestFitnessIndividualsOfGeneration = 
    		   minimumFitness(this.getGenerationFitness(),
    		                  sortedFitness,
    		                  (int) (Math.ceil(Integer.parseInt(p.getProperty(Constants.POPULATION_SIZE)))/4));
      
       
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
       
       //print statistics if flag is enabled
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
    
    /**
     * 
     * @return
     */
    @Override
	public ArrayList<Double> calcGenerationalFitness(Population pop,Properties p)
	{
		  double sum = 0;
		  this.getGenerationFitness().clear(); //added to control population increment
	       
	       for( int i=0; i< pop.size(); i++)
	       {
	          //this.setIndividual(pop.get(i));
	    	  sumChromosome(pop.get(i)); //set fitness of  individual
	          //this.setDouble(sumChromosome(this.getIndividual()));
	          this.getGenerationFitness().add(i, pop.get(i).getFitness().getDouble());
	          //this.generationFitness.add(i, this.getDouble());
	          sum += pop.get(i).getFitness().getDouble();
	          //System.out.println("Individual#"+i+" "+this.getDouble());
	       }
	       this.setTotalFitness(sum); //total fitness
	      return this.getGenerationFitness();
	}
    
    
    /**
     * 
     * @param pop
     * @return 
     * @deprecated
     */
    @Deprecated
	public double setAverageFitness(Population pop)
    {
       double averageFitness = 0;
       for( int i=0; i< pop.size(); i++)
       {
    	  sumChromosome(pop.get(i)); //calculates individuals fitness
          //averageFitness += sumChromosome(this.getIndividual());
    	 averageFitness += pop.get(i).getFitness().getDouble();
       }
       return averageFitness/pop.size();
    }
    
    
    /**
     * mock fitness - sum chromosomes
     * @param c
     * @return 
     */
    public void sumChromosome(Individual ind)
    {
        int sum = 0;
        for(int i=0; i<ind.getChromosome().getGenes().size();i++)
          sum += ind.getChromosome().getGenes().get(i).getId();
        
       ind.getFitness().setDouble(sum); //set fitness value of individual
    }

    
  
}
