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
package fitnessevaluation;

import individuals.populations.Population;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;

import algorithms.alps.layers.Layer;
import util.Constants;
import util.statistics.BasicStatistics;

/**
 *
 * @author anthony
 */
public abstract class WeightedSum extends FitnessExtension{
    

    private ArrayList<Integer> bestFitnessIndividualsOfGeneration;
    @SuppressWarnings("unused")
	private ArrayList<Integer> bestFitnessIndividualsForStatistics;
    private double averageFitnessPerGeneration, bestFitnessOfGeneration;

    //private ArrayList<Double> sortedFitness;
    
    /**
     * Evaluate individuals in a population - population of a generation
     * @param pop 
     */
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
       //select top individuals based on user set param
       this.bestFitnessIndividualsOfGeneration = 
    		   minimumFitness(this.generationFitness,
    		                  sortedFitness,
    		                  Integer.parseInt(this.getProperties().getProperty(Constants.ELITE_SIZE)));
      System.out.println(sortedFitness);
       //select top individuals based on user set param
       this.bestFitnessIndividualsForStatistics = minimumFitness(
    		   this.generationFitness,
    		   sortedFitness,
    		   Integer.parseInt(this.getProperties().getProperty(Constants.NUMBER_INDIVIDUALS_PRINT)));
       */
          
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
       
       //select top individuals based on user set param
       /*
       this.bestFitnessIndividualsForStatistics = minimumFitness(
    		   this.generationFitness,
    		   sortedFitness,
    		   Integer.parseInt(p.getProperty(Constants.NUMBER_INDIVIDUALS_PRINT)));
        */
       //System.out.println(sortedFitness);
       
       //this.bestFitnessIndividualsOfGeneration = minimumFitness(this.generationFitness,sortedFitness.get(0));
       this.averageFitnessPerGeneration = this.getTotalFitness()/pop.size();
       this.bestFitnessOfGeneration     = sortedFitness.get(0);
             
       /* erro when elite-size = 0
        * pop.setBestIndividual(pop.get(this.bestFitnessIndividualsOfGeneration.get(0)));
        * System.out.println("i am the best"+pop.get(this.bestFitnessIndividualsForStatistics.get(0)).getChromosome());
        */   
        //pop.setBestIndividual(pop.get(this.bestFitnessIndividualsForStatistics.get(0)));
       
       //this could be deprecated
      
       pop.setBestIndividual(pop.get(this.bestFitnessIndividualsOfGeneration.get(0))); 
       //this contains records of all best individuals
       pop.setBestIndividuals(pop.get(this.bestFitnessIndividualsOfGeneration));
       
       this.setBestFitness(this.bestFitnessOfGeneration);
       this.setAverageFitness(this.averageFitnessPerGeneration);
       this.setStandardDeviationFitness(getStandardDeviationOfFitness(this.getGenerationFitness()));
       
       //System.out.println("#POPULATION SIZE##"+ sortedFitness.get(0));
       
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
    		                  (int) ((int) pop.size()<10?pop.size():(Math.ceil(Integer.parseInt(p.getProperty(Constants.POPULATION_SIZE)))/4)));
       
       
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
    public ArrayList<Integer> getBestIndividualsOfGeneration()
    {
       return this.bestFitnessIndividualsOfGeneration;
    }
    
    


	
}
