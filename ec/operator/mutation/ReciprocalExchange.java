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
package ec.operator.mutation;

import ec.individuals.Chromosome;
import ec.individuals.Gene;
import ec.individuals.Individual;
import ec.individuals.fitnesspackage.BasicFitness;
import ec.individuals.populations.Population;
import ec.operator.MutationModule;
import ec.operator.Operator;

import java.util.ArrayList;

/**
 *
 * @author anthony
 */
public class ReciprocalExchange extends MutationModule {

    private int[] twoPointsOnChromosome = new int[2];
    private Population offsprings;
    
    
    public ReciprocalExchange()
    {
      super("Reciprocal Exchange");
    }
    
    /**
     * in future pass child to be mutated
     */
    @Override
	@SuppressWarnings("unchecked")
	public ArrayList<Individual> performMutationOperation(Population p, Chromosome c1,int parentId)
    {
      Individual id1 = new Individual();
      ArrayList<Individual> child = new ArrayList<>();
        
      twoPointsOnChromosome = Operator.selectTwoPointsRandomly(p.get(0).getChromosome().getGenes().size()-1); //replace value with pop size
      
      c1.setGenes((ArrayList<Gene>) p.get(parentId).getChromosome().getGenes().clone()); //clone individuals
      
      c1.getGenes().set(twoPointsOnChromosome[0],p.get(parentId).getChromosome().getGenes().get(twoPointsOnChromosome[1]));
      c1.getGenes().set(twoPointsOnChromosome[1],p.get(parentId).getChromosome().getGenes().get(twoPointsOnChromosome[0]));
  
       //set individual properties for chldren and add to new population
      id1.setChromosome(c1);
      id1.setFitness(new BasicFitness()); //set fitness object
      child.add(id1);
      setOffsprings(child);
      return child;
    }
    
    
    /**
     * ALPS: OVerloaded
     */
    @Override
	@SuppressWarnings("unchecked")
	public ArrayList<Individual> performMutationOperation(
			Population p, 
			Chromosome c1,
			int parentId,
			ArrayList<Double> ages,
			String replacementType)
    {
      Individual id1 = new Individual();
      ArrayList<Individual> child = new ArrayList<>();
        
      twoPointsOnChromosome = Operator.selectTwoPointsRandomly(p.get(0).getChromosome().getGenes().size()-1); //replace value with pop size
      
      c1.setGenes((ArrayList<Gene>) p.get(parentId).getChromosome().getGenes().clone()); //clone individuals
      
      c1.getGenes().set(twoPointsOnChromosome[0],p.get(parentId).getChromosome().getGenes().get(twoPointsOnChromosome[1]));
      c1.getGenes().set(twoPointsOnChromosome[1],p.get(parentId).getChromosome().getGenes().get(twoPointsOnChromosome[0]));
    
       //set individual properties for chldren and add to new population
      id1.setChromosome(c1);
      id1.setFitness(new BasicFitness()); //set fitness object
      
      if(replacementType.equals("SteadyState"))//asssign parent with lowest evluation value
        id1.setBirthEvaluations(ages.get(0));
      else if(replacementType.equals("Generational"))
      	id1.setAge(ages.get(0)); //add age
      
      child.add(id1);
      setOffsprings(child);
      return child;
    }
 
}
