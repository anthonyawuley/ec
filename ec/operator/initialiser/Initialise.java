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
package ec.operator.initialiser;

import ec.individuals.Chromosome;
import ec.individuals.Gene;
import ec.individuals.Individual;
import ec.individuals.fitnesspackage.BasicFitness;
import ec.individuals.populations.Population;

import ec.algorithms.ga.Evolve;
import ec.operator.InitialisationModule;
import ec.util.random.MersenneTwisterFast;

/**
 *
 * @author anthony
 */
public class Initialise implements InitialisationModule{
    
	private MersenneTwisterFast rng = new MersenneTwisterFast();
    
    public Initialise()
    {
    }
  
    
    @Override
	public String toString(int popSize)
    {
    	return "Default initialiser module started. "
    			+ "Creating population of "+ popSize
    			+"\n---------------------------------------";
    }
    
    /**
     * 
     * @param populationSize
     * @param chromosomeLength
     * @return
     */
	@Override
	public  Population generateInitialPopulation(
			Evolve e) 
    {
        
    	System.out.println(toString(e.populationSize));
    	this.toString(e.populationSize);
    	
        Population pop = new Population();
        //create initial population
        for(int i=0; i<e.populationSize;i++)
        {
            Chromosome ch = new Chromosome();
            ch.setChromosomeSize(e.chromosomeLength);
            Individual individual = new Individual();
            //set initial age to zero(0)
            individual.setAge(0);
            //set initial layer to zero(0)
            individual.setLayerId(0);
            //NB: clone used to make each individual independent
            //individual.createChromosome(ch,gene,prop); //create individual
            ch.createChromosome(ch,e.properties,e.random); //create individual
            individual.setChromosome(ch);
            //set fitness object
            individual.setFitness(new BasicFitness()); //create fitness object
            pop.add(individual);     //add individual to population
           // pop.get(i).setChromosome((ArrayList<Gene>) ch.getChromosome().clone()); //add chromosome to individual
        }
       
        return pop;
    }

    /**
     * 
     * @param populationSize
     * @param chromosomeLength
     * @return
     * @deprecated
     */
	@Deprecated
	@Override
	public  Population generateInitialPopulation(Evolve e,
			Gene gene) 
    {
        
    	System.out.println(toString(e.populationSize));
    	this.toString(e.populationSize);
    	
        Population pop = new Population();
        //create initial population
        for(int i=0; i<e.populationSize;i++)
        {
            Chromosome ch = new Chromosome();
            ch.setChromosomeSize(e.chromosomeLength);
            Individual individual = new Individual();
            //set initial age to zero(0)
            individual.setAge(0);
            //set initial layer to zero(0)
            individual.setLayerId(0);
            //NB: clone used to make each individual independent
            //ch.createChromosome(ch,gene,prop); //create individual
            ch.createChromosome(ch,e.properties,rng); //create individual
            individual.setChromosome(ch);
            //set fitness object
            individual.setFitness(new BasicFitness()); //create fitness object
            pop.add(individual.clone());     //add individual to population
            //pop.get(i).setChromosome((ArrayList<Gene>) ch.getChromosome().clone()); //add chromosome to individual
        }
       
        return pop;
    }
    

	
	/**
	 * @deprecated
	 */
	@Deprecated
	@Override
	public Population generateInitialPopulation(Evolve e,Gene g, double evaluations) 
	{
    	System.out.println(toString(e.populationSize));
    	this.toString(e.populationSize);
    	
        Population pop = new Population();
        //create initial population
        for(int i=0; i<e.populationSize;i++)
        {
            Chromosome ch = new Chromosome();
            ch.setChromosomeSize(e.chromosomeLength);
            Individual individual = new Individual();
            //set initial age to one(1.0)
            individual.setAge(0.0);
            //set initial layer to zero(0)
            individual.setLayerId(0);
            //NB: clone used to make each individual independent
            //ch.createChromosome(ch,g,prop); //create individual
            ch.createChromosome(ch,e.properties,rng); //create individual
            individual.setChromosome(ch);
            /* set default number of evaluations for SS ALPS
             * add individual to population
             * individuals created at the same time are assumed 
             * to be at the same number of evaluations
             */
            individual.setBirthEvaluations(evaluations);
            //set fitness object
            individual.setFitness(new BasicFitness()); //create fitness object
            pop.add(individual.clone());     //add individual to population
            //pop.get(i).setChromosome((ArrayList<Gene>) ch.getChromosome().clone()); //add chromosome to individual
        }
       
        return pop;
	}
    
	
	@Override
	public Population generateInitialPopulation(Evolve e, double evaluations) 
	{
    	System.out.println(toString(e.populationSize));
    	//this.toString(e.populationSize);
    	
        Population pop = new Population();
        //create initial population
        for(int i=0; i<e.populationSize;i++)
        {
            Chromosome ch = new Chromosome();
            ch.setChromosomeSize(e.chromosomeLength);
            Individual individual = new Individual();
            //set initial age to one(1.0)
            individual.setAge(0.0);
            //set initial layer to zero(0)
            individual.setLayerId(0);
            //NB: clone used to make each individual independent
            ch.createChromosome(ch,e.properties,rng); //create individual
            individual.setChromosome(ch);
            /* set default number of evaluations for SS ALPS
             * add individual to population
             * individuals created at the same time are assumed 
             * to be at the same number of evaluations
             */
            individual.setBirthEvaluations(evaluations);
            //set fitness object
            individual.setFitness(new BasicFitness()); //create fitness object
            pop.add(individual);     //add individual to population
            //pop.get(i).setChromosome((ArrayList<Gene>) ch.getChromosome().clone()); //add chromosome to individual
        }
       
        return pop;
	}
    

}
