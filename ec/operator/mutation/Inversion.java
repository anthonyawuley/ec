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
 * 
 * This is a form of mutation with several categories such as
 * Insertion, Displacement, Reciprocal exchange, Scranble mutation etc.
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
public class Inversion extends MutationModule {
    
    
    private int[] twoPointsOnChromosome = new int[2];
    
    public Inversion()
    {
      super("Inversion");
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
      int count=0;
      int min, max; //hold smallest of the bit positions to invert
        
      twoPointsOnChromosome = Operator.selectTwoPointsRandomly(p.get(0).getChromosome().getGenes().size()); //replace value with pop size
       
      min = twoPointsOnChromosome[0]<twoPointsOnChromosome[1]?twoPointsOnChromosome[0]:twoPointsOnChromosome[1];
      max = twoPointsOnChromosome[0]>twoPointsOnChromosome[1]?twoPointsOnChromosome[0]:twoPointsOnChromosome[1];
      //System.out.println("min#"+min+" max#"+max+" size#"+p.get(0).getChromosome().size() + "Before#"+c1.getGenes());
      c1.setGenes((ArrayList<Gene>) p.get(parentId).getChromosome().getGenes().clone()); //clone individuals
      
      for(int i = min;i<=max;i++)
      {
         //c1.getGenes().set(i,p.get(parentId).getChromosome().get(max-i+1));
         c1.getGenes().set(i,p.get(parentId).getChromosome().getGenes().get(max-count));
         count++;
      }
      //set individual properties for chldren and add to new population
      
      if(!chromosomeHasDuplicateGenes(c1.getGenes()))
      {
    	  id1.setChromosome(c1);
    	  id1.setFitness(new BasicFitness()); //set fitness object
          child.add(id1);
      }
      setOffsprings(child);
      return child;
    }
    
    
    /**
     * OVERLOADED: ALPS
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
      int count=0;
      int min, max; //hold smallest of the bit positions to invert
        
      twoPointsOnChromosome = Operator.selectTwoPointsRandomly(p.get(0).getChromosome().getGenes().size()); //replace value with pop size
       
      min = twoPointsOnChromosome[0]<twoPointsOnChromosome[1]?twoPointsOnChromosome[0]:twoPointsOnChromosome[1];
      max = twoPointsOnChromosome[0]>twoPointsOnChromosome[1]?twoPointsOnChromosome[0]:twoPointsOnChromosome[1];
      //System.out.println("min#"+min+" max#"+max+" size#"+p.get(0).getChromosome().size() + "Before#"+c1.getGenes());
      c1.setGenes((ArrayList<Gene>) p.get(parentId).getChromosome().getGenes().clone()); //clone individuals
      
      for(int i = min;i<=max;i++)
      {
         //c1.getGenes().set(i,p.get(parentId).getChromosome().get(max-i+1));
         c1.getGenes().set(i,p.get(parentId).getChromosome().getGenes().get(max-count));
         count++;
      }
      //set individual properties for chldren and add to new population
      
      if(!chromosomeHasDuplicateGenes(c1.getGenes()))
      {
    	  id1.setChromosome(c1);
    	  id1.setFitness(new BasicFitness()); //set fitness object
    	  
    	  if(replacementType.equals("SteadyState")) //asssign parent with lowest evluation value
            id1.setBirthEvaluations(ages.get(0));
          else if(replacementType.equals("Generational"))
          	id1.setAge(ages.get(0)); //add age
    	  
          child.add(id1);
      }
      setOffsprings(child);
      
      return child;
    }
  
}
