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

import individuals.fitnesspackage.PopulationFitness;
import individuals.populations.Population;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;

import algorithms.alps.layers.Layer;
import algorithms.alps.system.ALPSLayers;
import util.Constants;
import util.random.RandomGenerator;
import util.statistics.BasicStatistics;

/**
 *
 * @author anthony
 */
public  abstract class FitnessExtension extends PopulationFitness {
    
	private Properties prop;
	private double totalFitness;
	
	
	/**
	 * method must be implemented in specific fitness class
	 * @param pop
	 * @param run
	 * @param generation
	 */
	public abstract void calculateSimpleFitness(
			Population pop, 
			final int run, 
			final int generation,
			BasicStatistics stats,
			Properties p);

	/**
	 * 
	 * @param pop
	 * @param layer
	 * @param generation
	 * @param run
	 * @param stats
	 * @param p
	 */
	public abstract void calculateSimpleFitness(
			Population pop, 
			final Layer layer, 
			final int generation,
			final int run,
			BasicStatistics stats,
			Properties p,
			Boolean statsFlag);
	/**
	 * 
	 * @return
	 */
	public abstract ArrayList<Integer> getBestIndividualsOfGeneration();
	/**
	 * 
	 * @param tournamentIndividuals
	 * @return
	 */
	//public abstract ArrayList<Integer> selectIndividualsBasedOnFitness(ArrayList<Integer> tournamentIndividuals,double selectionPressure);
	
	/**
	 * implemented by highest class defining fitness
	 * @param pop
	 * @return
	 */
	public abstract ArrayList<Double> calcGenerationalFitness(Population pop,Properties p);
	
	/**
	 * 
	 * @return
	 */
	public void setTotalFitness(double t)
	{
		this.totalFitness = t;
	}
	
	/**
	 * 
	 * @return
	 */
	public double getTotalFitness()
	{
		return this.totalFitness;
	}
	
	/**
	 * 
	 * @param p
	 */
	public void setProperties(Properties p)
	{
		this.prop = p;
	}
	/**
	 * 
	 * @return
	 */
	public Properties getProperties()
	{
		return this.prop;
	}
	
	
	public long getCurrentTime()
	{
		return System.currentTimeMillis();
	}
	
	
	public boolean isTooLongEvalTime(long currentTime)
	{
		return (System.currentTimeMillis()-currentTime) > 
		Constants.MAXIMUM_EVALUATION_TIME;
	}
	
	/**
	 * 
	 * @param ranks
	 * @param popSize
	 */
    public void paretoCalculations(ArrayList<String> ranks, int popSize){}
    
    /**
     * 
     * @param ranks
     * @param popSize
     */
    public void sorCalculations(ArrayList<String> ranks, int popSize){}
    
    /**
     * 
     * @param genFit
     * @return
     */
    private double calculateMeanOfPopulationFitness(ArrayList<Double> genFit)
    {
    	 double sum=0;
    	 for( int i=0; i< genFit.size(); i++)
            sum+=genFit.get(i); //System.out.println("Individual#"+i+" "+this.getDouble());
         
    	return sum/genFit.size();
    }
    
    /**
     * 
     * @param genFit
     * @return
     */
    private double calculateVariance(ArrayList<Double> genFit)
    {
    	 double mean = calculateMeanOfPopulationFitness(genFit);
    	 double var=0;
    	 for( int i=0; i< genFit.size(); i++)
            var += (genFit.get(i)-mean) * (genFit.get(i)-mean);
         
    	return var/genFit.size();
    }
    
    /**
     * 
     * @param genFit
     * @return
     */
    public double getStandardDeviationOfFitness(ArrayList<Double> genFit)
    { 
    	return Math.sqrt(calculateVariance(genFit));
    }
    
    
    
   /**
    * 
    * @param alpsLayers
    * @param tournamentIndividuals
    * @param selectionPressure
    * @param isUpperLayer : true or false
    * @return
    */
    public ArrayList<Integer> selectIndividualsBasedOnFitness(
    		ALPSLayers alpsLayers,ArrayList<Integer> tournamentIndividuals,
    		double selectionPressure, boolean isUpperLayer)
    {
    	ArrayList<Integer> selected = new ArrayList<>();
        ArrayList<Double>  sorted = new ArrayList<>();
        ArrayList<Double> generationFitnessCopy = new ArrayList<>(); //done to prevent modification of original
        
        if(isUpperLayer)
            generationFitnessCopy = this.getGenerationFitness();
        else //get lower layer
        	generationFitnessCopy = alpsLayers.layers.get(alpsLayers.index-1).getEvolution().
        			                  getCurrentPopulation().getFitness().getGenerationFitness();
        
        
        RandomGenerator randGen = new RandomGenerator(); 
        double rand = randGen.nextDouble();
        
        if(rand<=selectionPressure)
        {
          //System.out.println("#TournamentIndividuals "+ tournamentIndividuals);
           for(int i=0; i < tournamentIndividuals.size(); i++)
               sorted.add(generationFitnessCopy.get(tournamentIndividuals.get(i)));
         
           Collections.sort(sorted);
        
           selected.add(sortTournamentFitness(generationFitnessCopy,tournamentIndividuals,sorted.get(0)).get(0));
           selected.add(sortTournamentFitness(generationFitnessCopy,tournamentIndividuals,sorted.get(1)).get(0));
           //System.out.println("#selected :"+selected +"\nFITNESS "+this.generationFitness+"\nSELECTED"+selected.get(0));      
        }
        else
        {  
     	   Collections.shuffle(tournamentIndividuals); //shuffle
     	   int firstIndividualId = RandomGenerator.getRandomFromArray(tournamentIndividuals); //select individual randomly
     	   selected.add(firstIndividualId); //add individual to selected
     	   
     	   tournamentIndividuals.remove((Object)firstIndividualId); //remove selected individual from array
           Collections.shuffle(tournamentIndividuals); //shuffle remaining array
           selected.add(RandomGenerator.getRandomFromArray(tournamentIndividuals)); //add second individual
        }
        return selected; 
    }
    
    
    
    /**
     * 
     * @param tournamentIndividuals
     * @param individualsReturned  -- this number is based on the the operation (e.g. crossover requires 2 and mutation requires 1)
     * @param selectionPressure
     * @return 
     */
    public ArrayList<Integer> selectIndividualsBasedOnFitness(
    		ArrayList<Integer> tournamentIndividuals,double selectionPressure)
    {
       ArrayList<Integer> selected = new ArrayList<>();
       ArrayList<Double>  sorted = new ArrayList<>();
       
       RandomGenerator randGen = new RandomGenerator(); 
       double rand = randGen.nextDouble();
       
       if(rand<=selectionPressure) //selection based on fitness
       {
         //System.out.println("#TournamentIndividuals "+ tournamentIndividuals);
          for(int i=0; i < tournamentIndividuals.size(); i++){
           //System.out.println("TournamentSize#"+tournamentIndividuals.get(i));
              sorted.add(this.getGenerationFitness().get(tournamentIndividuals.get(i)));
          }
          Collections.sort(sorted);
          //System.out.println("tournament "+ tournamentIndividuals +" sorted "+sorted);
          selected.add(sortTournamentFitness(
        		  this.getGenerationFitness(),tournamentIndividuals,sorted.get(0)).get(0));
          if(sorted.size()>1)
              selected.add(sortTournamentFitness(
            		  this.getGenerationFitness(),tournamentIndividuals,sorted.get(1)).get(0));
          
          //System.out.println("#selected :"+selected +"\nFITNESS "+this.generationFitness+"\nSELECTED"+selected.get(0));      
       }
       else //random selection
       {  
    	   Collections.shuffle(tournamentIndividuals); //shuffle
    	   int firstIndividualId = RandomGenerator.getRandomFromArray(tournamentIndividuals); //select individual randomly
    	   selected.add(firstIndividualId); //add individual to selected
    	   
    	   tournamentIndividuals.remove((Object)firstIndividualId); //remove selected individual from array
    	   
    	   if(tournamentIndividuals.size()>0)
    	   {
             Collections.shuffle(tournamentIndividuals); //shuffle remaining array
             selected.add(RandomGenerator.getRandomFromArray(tournamentIndividuals)); //add second individual
    	     //tournamentIndividuals.re
    	   }
       }
       
       return selected;  
       
    }
    
    
    
    
}
