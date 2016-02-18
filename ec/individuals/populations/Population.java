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
package ec.individuals.populations;

import ec.individuals.Individual;
import ec.individuals.fitnesspackage.PopulationFitness;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import ec.algorithms.alps.system.ALPSLayers;
import ec.util.DeepClone;

/**
 * IMplements the Population Interface and defines all properties of a population
 * 
 * @author Anthony Awuley
 */
public class Population implements PopulationInterface, Cloneable, Serializable {

    /** */
	private static final long serialVersionUID = 1L;
	/** */
	private ArrayList<Individual> population = new ArrayList<>();
	/** */
	private ArrayList<Individual> bestIndividuals = new ArrayList<>();
	/** */
    private int popSize;
    /** */
    ArrayList<Individual> individuals;
    /** */
    private PopulationFitness f;
    /** */
    private Individual bestIndividual;
    
    
    /**
     * 
     */
    public Population() 
    {
        individuals = new ArrayList<Individual>(0);
    }
    /**
     * 
     * @param size
     */
    public Population(int size) 
    {
        individuals = new ArrayList<Individual>(size);
    }
    /**
     * 
     * @param pop
     */
    public Population(ArrayList<Individual> pop) 
    {
        this.population = pop;
    }
    /**
     * 
     */
    @Override
	public Population clone() 
    {
    	return (Population) DeepClone.clone(this);
    }
   
    
    /**
     * calculate age of individuals within the population for 
     * SS replacement strategy in ALPS
     * @param currentEvaluationCount
     * @param popSize
     */
    public void calculateAge(int currentEvaluationCount,int popSize)
    {
    	for(Individual e: this.population)
    		e.setAge(1 + (currentEvaluationCount-e.getBirthEvaluations())/popSize);
    }
    
    /**
     * calculate age of individuals within the population for 
     * SS replacement strategy in ALPS
     * divide by total population size of all layers
     * 
     * @param alpsLayers
     * @param currentEvaluationCount
     * @param popSize
     */
    public void calculateAge(ALPSLayers alpsLayers, int currentEvaluationCount)
    {
    	int totalPop = 0;
    	
    	for(int i=0;i<alpsLayers.layers.size();i++)
    		totalPop += alpsLayers.layers.get(i).getEvolution().getCurrentPopulation().size();
    	
    	for(Individual e: this.population)
    		e.setAge(1 + (currentEvaluationCount-e.getBirthEvaluations())/totalPop);
    	
    }
    
    /**
     * 
     */
    @Override
	public void sort() 
    {
    }
    /**
     * 
     * @param size
     */
    public void setSize(int size)
    {
       popSize = size;
    }
    /**
     * select individual with best fitness
     * @return 
     */
    @Override
    public Individual getBestIndividual() 
    {
        return this.bestIndividual;
    }
    /**
     * 
     */
    @Override
    public void setBestIndividual(Individual i) 
    {
        this.bestIndividual = i;
    }
    /**
     * 
     * @return 
     */
    @Override
    public int size() 
    {
        return population.isEmpty()?
        		this.popSize:population.size();
    }
    /**
     * 
     */
    @Override
    public void addAll(ArrayList<Individual> immigrants) 
    {
        Iterator<Individual> indIt = immigrants.iterator();
        while (indIt.hasNext()) 
            this.add(indIt.next());
    }
    /**
     * 
     */
    @Override
    public void addAll(Population pop, ArrayList<Individual> immigrants) 
    {
        Iterator<Individual> indIt = immigrants.iterator();
        while (indIt.hasNext()) 
            pop.add(indIt.next());
    }
    
    /**
     * unset parent flag of all individuals in the population
     */
    public void unsetParent(){
    	for(Individual i: this.population)
    		i.parentFlag = Boolean.FALSE;
    }
    /**
     * 
     */
    @Override
    public void addAll(PopulationInterface pop) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    /**
     * 
     */
    @Override
    public ArrayList<Individual> getAll() 
    {
        return population;
    }
    /**
     * 
     */
    @Override
    public boolean contains(Individual individual) 
    {
        return this.population.contains(individual);
    }
    /**
     * 
     * @param i 
     */
    @Override
    public void add(Individual i) 
    {
        this.population.add(i);
    }
    
    /**
     * @param index
     * @param i 
     */
    @Override
    public void add(int index, Individual i) 
    {
        this.population.add(index,i);
    }
    
    /**
     * 
     * @param index
     * @param i
     */
    public void set(int index, Individual i) 
    {
        this.population.set(index, i);
    }
    /**
     * 
     */
    @Override
    public Individual get(int id) 
    {
        return this.population.get(id);
    }
    /**
     * 
     */
    @Override
    public ArrayList<Individual> get(ArrayList<Integer> ids) 
    {
    	for(int id:ids)
    		bestIndividuals.add(this.population.get(id));
    	
        return this.bestIndividuals;
    }
    /**
     * 
     */
    @Override
    public void clear() 
    {
        this.population.clear();
    }

    @Override
    public void remove(Individual ind) 
    {
        this.population.remove(ind);
    }

	@Override
	public void setBestIndividuals(ArrayList<Individual> inds) 
	{
		this.bestIndividuals = inds;
	}

	@Override
	public ArrayList<Individual> getBestIndividuals() 
	{
		return this.bestIndividuals;
	}

	@Override
	public PopulationFitness getFitness() 
	{
		return this.f;
	}

	@Override
	public void setFitness(PopulationFitness f) {
		 this.f = f;
	}

	    

}
