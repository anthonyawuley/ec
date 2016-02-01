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

import ec.individuals.Chromosome;
import ec.util.Constants;
import ec.util.random.GenerateMask;

import java.util.ArrayList;

/**
 * Defines basic properties and functions of PopulationFitness template
 * 
 * @author Anthony Awuley
 */
public class PopulationFitness implements PopFitnessInterface{

    /** */
    private static double Best_Fitness         = 0.0;
    /** */
    private static double Average_Fitness      = 0.0;
    /** */
    private static double Standard_Deviation   = 0.0;
    /** */
	private double value;
	/** */
    private ArrayList<Double> generationFitness = new ArrayList<>();
    /** */
    private static ArrayList<Double> multiObjectiveFitnes = new ArrayList<>();
   
    
    
    /** Creates a new instance of BasicFitness */
    public PopulationFitness() 
    {
        this.value = 0.0d;
    }

    
    /**
     * 
     * @param generation
     * @return minimum fitness in generation
     */
    public ArrayList<Integer> sortTournamentFitness(ArrayList<Double> generation,ArrayList<Integer> tournamentContainer, double min)
    {
       ArrayList<Integer> minimum = new ArrayList<>();
       for(int i=0;i<generation.size();i++)
       {
           if( (generation.get(i) == min) && GenerateMask.isExistIndex(tournamentContainer,i+"") )
           {
              minimum.add(i);
           }
       }
       return minimum;
     }

    
    /**
     * 
     * @param c
     * @return
     */
    public ArrayList<Double> getMultiObjectiveFitness(Chromosome c)
    {
        return PopulationFitness.multiObjectiveFitnes;
    }

    
    /**
     * select top individual
     * @param generation
     * @return minimum fitness in generation
     */
    public ArrayList<Integer> minimumFitness(ArrayList<Double> generation, double min)
    {
       ArrayList<Integer> minimum = new ArrayList<>();
       for(int i=0;i<generation.size();i++)
       {
           if(generation.get(i) == min)
           {
              minimum.add(i);
           }
       }
       return minimum;
    }
    
    /**
     * Select top individuals to print
     * @param generation
     * @param sorted
     * @param numbOfBestIndividuals  : number of individuals to print
     * @return
     */
    @SuppressWarnings("unchecked")
	public ArrayList<Integer> minimumFitness(ArrayList<Double> gen, ArrayList<Double> sorted,int size)
    {  
       //System.out.println("#SORTED SORTED SORTED \n"+generation+" \n"+sorted);
       ArrayList<Integer> minimum = new ArrayList<>();
       ArrayList<Double> generation = (ArrayList<Double>) gen.clone();
       //System.out.println("gen#"+gen.size()+" sorted#"+sorted.size()+" size#"+size);
       for(int i=0;i<size;i++)
       {
    	   try
    	   {
    		   minimum.add(i,GenerateMask.returnIndexD(generation, sorted.get(i)+""));
        	   //eliminate already sorted elements
        	   //this is to avoid multiple selection of same indexes with same fitness values. e.g. 0,0, or 2,2 etc
        	   generation.set(minimum.get(i),(double) Constants.DEFAULT_WORSE_FITNESS); 
    	   }
    	   catch(IndexOutOfBoundsException e)
    	   {
    		   System.err.println("\"number-of-individuals\" or \"elite-size\" parameter in parameter value "
    		   		+ "must be at most equall to \"population size\" "+ e.getMessage());
    	   } 
       }
       return minimum;
    }


	@Override
	public double getBestFitness() 
	{
		return PopulationFitness.Best_Fitness;
	}


	@Override
	public double getAverageFitness() 
	{
		return PopulationFitness.Average_Fitness;
	}


	@Override
	public double getStandardDeviationFitness() 
	{
		return PopulationFitness.Standard_Deviation;
	}


	@Override
	public void setBestFitness(double f) 
	{
		PopulationFitness.Best_Fitness = f;
	}


	@Override
	public void setAverageFitness(double f) 
	{
		PopulationFitness.Average_Fitness = f;
	}


	@Override
	public void setStandardDeviationFitness(double f) 
	{
		PopulationFitness.Standard_Deviation = f;
	}


	/**
	 * @return the generationFitness
	 */
	@Override
	public ArrayList<Double> getGenerationFitness() 
	{
		return generationFitness;
	}


	/**
	 * @param generationFitness the generationFitness to set
	 */
	@Override
	public void setGenerationFitness(ArrayList<Double> generationFitness) 
	{
		this.generationFitness = generationFitness;
	}


 
    
}
