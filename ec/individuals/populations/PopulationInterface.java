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

import java.util.ArrayList;

/**
 * Provides basic  template for the population Class
 * 
 * @author Anthony Awuley
 */
public interface PopulationInterface {
    
    /**
     * Sort the individuals in the population
     */
    @SuppressWarnings({})
    public void sort();
    /**
     * set best individual of population
     * @param i
     */
    public void setBestIndividual(Individual i); //in future deprecate
    /**
     * 
     * @return best individual of population
     */
    public Individual getBestIndividual();       //in future deprecate
    /**
     * set best individulas of population
     * @param inds
     */
    public void setBestIndividuals(ArrayList<Individual> inds);
    /**
     * 
     * @return
     */
    public ArrayList<Individual> getBestIndividuals();
 
    /**
     * Get fitness of individuals
     * @return individual fitness
     */
    public PopulationFitness getFitness();
    /**
     * Set fitness
     * @param f fitness
     */
    void setFitness(PopulationFitness f);
    /**
     * The number of individuals in the population
     * @return number of individuals
     */
    public int size();

    /**
     * Add a collection of individuals to the population
     * @param immigrants collection of individuals
     */
    public void addAll(Population pop, ArrayList<Individual> immigrants);
    /**
     * Add a collection of individuals to the population
     * @param immigrants collection of individuals
     */
    public void addAll(ArrayList<Individual> immigrants);
    /**
     * Add an entire population to the population
     * @param pop population to add
     */
    public void addAll(PopulationInterface pop);
    /**
     * Get a list of all the individuals
     * @return list view of the population
     */
    public ArrayList<Individual> getAll();
    /**
     * Check if the individual is contained in the population
     * @param individual individual to compare
     * @return boolean value if the individual exists in the population
     */
    public boolean contains(Individual individual);
    /**
     * Add an individual to the population
     * @param i individual to add
     */
    public void add(Individual i);
    /**
     * Add an individual to a specific position
     * @param index
     * @param i
     */
    public void add(int index, Individual i) ;
    /**
     * Get an individual from the specified index
     * @param index which individual to return
     * @return individual at index
     */
    public Individual get(int index);
    /**
     * Clear the population of all individuals
     */
    public void clear();
    /**
     * Remove individual from population
     * @param ind individual to remove
     */
    public void remove(Individual ind);
    /**
     * 
     * @param ids
     * @return
     */
	public ArrayList<Individual> get(ArrayList<Integer> ids);
    
    
}
