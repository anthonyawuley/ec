/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package operator.initialiser;

import individuals.Chromosome;
import individuals.Gene;
import individuals.Individual;
import individuals.fitnesspackage.BasicFitness;
import individuals.populations.Population;

import java.util.ArrayList;
import java.util.Properties;

import operator.InitialisationModule;

/**
 *
 * @author anthony
 */
public class Initialise implements InitialisationModule{
    
    
    public Initialise()
    {
    }
  
    
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
    @SuppressWarnings("unchecked")
	public  Population generateInitialPopulation(
			Gene gene, Properties prop, int populationSize, int chromosomeLength) 
    {
        
    	System.out.println(toString(populationSize));
    	this.toString(populationSize);
    	
        Population pop = new Population();
        //create initial population
        for(int i=0; i<populationSize;i++)
        {
            Chromosome ch = new Chromosome();
            ch.setChromosomeSize(chromosomeLength);
            Individual individual = new Individual();
            //set initial age to zero(0)
            individual.setAge(0);
            //set initial layer to zero(0)
            individual.setLayerId(0);
            //NB: clone used to make each individual independent
            individual.createChromosome(ch,gene,prop); //create individual
            //set fitness object
            individual.setFitness(new BasicFitness()); //create fitness object
            pop.add(individual.clone());     //add individual to population
            pop.get(i).setChromosome((ArrayList<Integer>) ch.getChromosome().clone()); //add chromosome to individual
        }
       
        return pop;
    }


	@SuppressWarnings("unchecked")
	@Override
	public Population generateInitialPopulation(Gene g, Properties prop,
			int populationSize, int chromosomeLength, double evaluations) 
	{
    	System.out.println(toString(populationSize));
    	this.toString(populationSize);
    	
        Population pop = new Population();
        //create initial population
        for(int i=0; i<populationSize;i++)
        {
            Chromosome ch = new Chromosome();
            ch.setChromosomeSize(chromosomeLength);
            Individual individual = new Individual();
            //set initial age to one(1.0)
            individual.setAge(0.0);
            //set initial layer to zero(0)
            individual.setLayerId(0);
            //NB: clone used to make each individual independent
            individual.createChromosome(ch,g,prop); //create individual
            /* set default number of evaluations for SS ALPS
             * add individual to population
             * individuals created at the same time are assumed 
             * to be at the same number of evaluations
             */
            individual.setBirthEvaluations(evaluations);
            //set fitness object
            individual.setFitness(new BasicFitness()); //create fitness object
            pop.add(individual.clone());     //add individual to population
            pop.get(i).setChromosome((ArrayList<Integer>) ch.getChromosome().clone()); //add chromosome to individual
        }
       
        return pop;
	}
    
    

}
