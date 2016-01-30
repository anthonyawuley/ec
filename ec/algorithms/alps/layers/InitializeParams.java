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
package ec.algorithms.alps.layers;

import java.util.ArrayList;

import ec.exceptions.InitializationException;

public class InitializeParams{

	public ArrayList<Integer> ageScheme = new ArrayList<>();

	private int number_of_experiments;
	private int max_age_in_layers;
	private int age_gap;
    private int generationsEvolved;
    private int chromosomeLength;
    private int generations;
    private int populationSize;
    private double crossoverRate;
    private double mutationRate;
    private int    tournamentSize;
    private boolean stopFlag;
    private int     elitismSize;
    private double  selectionPressure;
    private long   seed;
	private double layerSelectionPressure;
	private double ssSelectionPressure;
	private String replacementOperator;
	private double evaluations;
	

	/**
	 * @return the max_age_in_layers
	 */
	public int getMaxAges() 
	{
		return max_age_in_layers;
	}

	/**
	 * @param max_age_in_layers the max_age_in_layers to set
	 */
	public void setReplacementOperator(String replacementOperator) 
	{
		this.replacementOperator = replacementOperator;
	}
	
	/**
	 * @return replacementOperator:
	 * e.g. Generational for this -> operator.operations.replacement.Generational
	 */
	public String getReplacementOperator() 
	{
		String[] a = new String[3];
		a = this.replacementOperator.split("\\.");
		
		return a[3];
	}

	/**
	 * @param max_age_in_layers the max_age_in_layers to set
	 */
	public void setMaxAgeLayers(int max_age_in_layers) 
	{
		this.max_age_in_layers = max_age_in_layers;
	}

	/**
	 * @return the age_gap
	 */
	public int getAgeGap() 
	{
		return age_gap;
	}

	/**
	 * @param age_gap the age_gap to set
	 */
	public void setAgeGap(int age_gap) 
	{
		this.age_gap = age_gap;
	}


	/**
	 * @return the generationsEvolved
	 */
	public int getGenerationsEvolved() 
	{
		return generationsEvolved;
	}


	/**
	 * @param generationsEvolved the generationsEvolved to set
	 */
	public void setGenerationsEvolved(int generationsEvolved) 
	{
		this.generationsEvolved = generationsEvolved;
	}


	/**
	 * @return the chromosomeLength
	 */
	public int getChromosomeLength() 
	{
		return chromosomeLength;
	}


	/**
	 * @param chromosomeLength the chromosomeLength to set
	 */
	public void setChromosomeLength(int chromosomeLength) 
	{
		this.chromosomeLength = chromosomeLength;
	}


	/**
	 * @return the generations
	 */
	public int getGenerations() {
		return generations;
	}


	/**
	 * @param generations the generations to set
	 */
	public void setGenerations(int generations) 
	{
		this.generations = generations;
	}


	/**
	 * @return the populationSize
	 */
	public int getPopulationSize() 
	{
		return populationSize;
	}


	/**
	 * @param populationSize the populationSize to set
	 * @throws InitializationException 
	 */
	public void setPopulationSize(int populationSize) throws InitializationException 
	{
		 if (this.crossoverRate < 0 || this.crossoverRate > 1) 
	     {
	            throw new InitializationException(this.crossoverRate, 0, 1);
	     }
		this.populationSize = populationSize;
	}


	/**
	 * @return the crossoverRate
	 */
	public double getCrossoverRate() 
	{
		return crossoverRate;
	}


	/**
	 * @param crossoverRate the crossoverRate to set
	 */
	public void setCrossoverRate(double crossoverRate) 
	{
		this.crossoverRate = crossoverRate;
	}


	/**
	 * @return the mutationRate
	 */
	public double getMutationRate() 
	{
		return mutationRate;
	}


	/**
	 * @param mutationRate the mutationRate to set
	 * @throws InitializationException 
	 */
	public void setMutationRate(double mutationRate) throws InitializationException 
	{
        if (this.mutationRate < 0 || this.mutationRate > 1) 
        {
            throw new InitializationException(this.mutationRate, 0, 1);
        }
		this.mutationRate = mutationRate;
	}


	/**
	 * @return the tournamentSize
	 */
	public int getTournamentSize() 
	{
		return tournamentSize;
	}


	/**
	 * @param tournamentSize the tournamentSize to set
	 * @throws InitializationException 
	 */
	public void setTournamentSize(int size) throws InitializationException 
	{
        if (size == 0 || size > 10) 
        { //System.out.println("this is lion "+size);
            throw new InitializationException(this.mutationRate, 1, 10);
        }
		this.tournamentSize = size;
	}


	/**
	 * @return the stopFlag
	 */
	public boolean isStopFlag() 
	{
		return stopFlag;
	}


	/**
	 * @param stopFlag the stopFlag to set
	 */
	public void setStopFlag(boolean stopFlag) 
	{
		this.stopFlag = stopFlag;
	}


	/**
	 * @return the elitismSize
	 */
	public int getElitismSize() 
	{
		return elitismSize;
	}


	/**
	 * @param elitismSize the elitismSize to set
	 */
	public void setElitismSize(int elitismSize) 
	{
		this.elitismSize = elitismSize;
	}


	/**
	 * @return the selectionPressure
	 */
	public double getSelectionPressure() 
	{
		return selectionPressure;
	}


	/**
	 * @param selectionPressure the selectionPressure to set
	 * @throws InitializationException 
	 */
	public void setSelectionPressure(double selectionPressure) throws InitializationException 
	{
		if (this.selectionPressure  < 0 || this.selectionPressure  > 1) 
        {
            throw new InitializationException(this.selectionPressure, 0, 1);
        }
		this.selectionPressure = selectionPressure;
	}


	/**
	 * @return the number_of_experiments
	 */
	public int getNumberOfExperiments() 
	{
		return number_of_experiments;
	}


	/**
	 * @param number_of_experiments the number_of_experiments to set
	 */
	public void setNumberOfExperiments(int number_of_experiments) 
	{
		this.number_of_experiments = number_of_experiments;
	}
	
	/**
	 * 
	 * @param rndSeed
	 */
	public void setSeed(long rndSeed) 
	{
		this.seed = rndSeed;
	}

	/**
	 * 
	 * @return
	 */
	public long getSeed() 
	{
		return this.seed;
	}


	public void setLayerSelectionPressure(double sp) 
	{
		this.layerSelectionPressure = sp;
	}
	
	public double getLayerSelectionPressure()
	{
		return this.layerSelectionPressure;
	}
	
	public void setSSSelectionPressure(double ssSelectionPressure ) 
	{
		this.ssSelectionPressure = ssSelectionPressure;
	}
	
	public double getSSSelectionPressure()
	{
		return this.ssSelectionPressure;
	}

	/**
	 * @return the evaluations
	 */
	public double getEvaluations() 
	{
		return evaluations;
	}


	/**
	 * @param evaluations the evaluations to set
	 */
	public void setEvaluations(double evaluations) 
	{
		this.evaluations = evaluations;
	}


}
