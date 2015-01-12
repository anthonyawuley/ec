/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * 
 * This is a form of mutation with several categories such as
 * Insertion, Displacement, Reciprocal exchange, Scranble mutation etc.
 */
package operator.mutation;

import individuals.Chromosome;
import individuals.Individual;
import individuals.fitnesspackage.BasicFitness;
import individuals.populations.Population;
import operator.MutationModule;
import operator.Operator;

import java.util.ArrayList;

/**
 *
 * @author anthony
 */
public class Inversion extends MutationModule {
    
    
    private int[] twoPointsOnChromosome = new int[2];
    private Population offsprings;
    
    public Inversion()
    {
      super("Inversion");
    }
    
    /**
     * in future pass child to be mutated
     */
    @SuppressWarnings("unchecked")
	public ArrayList<Individual> performMutationOperation(Population p, Chromosome c1,int parentId)
    {
      Individual id1 = new Individual();
      ArrayList<Individual> child = new ArrayList<>();
      int count=0;
      int min, max; //hold smallest of the bit positions to invert
        
      twoPointsOnChromosome = Operator.selectTwoPointsRandomly(p.get(0).getChromosome().size()); //replace value with pop size
       
      min = twoPointsOnChromosome[0]<twoPointsOnChromosome[1]?twoPointsOnChromosome[0]:twoPointsOnChromosome[1];
      max = twoPointsOnChromosome[0]>twoPointsOnChromosome[1]?twoPointsOnChromosome[0]:twoPointsOnChromosome[1];
      //System.out.println("min#"+min+" max#"+max+" size#"+p.get(0).getChromosome().size() + "Before#"+c1.getChromosome());
      c1.setChromosome((ArrayList<Integer>) p.get(parentId).getChromosome().clone()); //clone individuals
      
      for(int i = min;i<=max;i++)
      {
         //c1.getChromosome().set(i,p.get(parentId).getChromosome().get(max-i+1));
         c1.getChromosome().set(i,p.get(parentId).getChromosome().get(max-count));
         count++;
      }
      //set individual properties for chldren and add to new population
      
      if(!chromosomeHasDuplicateGenes(c1.getChromosome()))
      {
    	  id1.setChromosome(c1.getChromosome());
    	  id1.setFitness(new BasicFitness()); //set fitness object
          child.add(id1);
      }
      setOffsprings(child);
      return child;
    }
    
    
    /**
     * OVERLOADED: ALPS
     */
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
        
      twoPointsOnChromosome = Operator.selectTwoPointsRandomly(p.get(0).getChromosome().size()); //replace value with pop size
       
      min = twoPointsOnChromosome[0]<twoPointsOnChromosome[1]?twoPointsOnChromosome[0]:twoPointsOnChromosome[1];
      max = twoPointsOnChromosome[0]>twoPointsOnChromosome[1]?twoPointsOnChromosome[0]:twoPointsOnChromosome[1];
      //System.out.println("min#"+min+" max#"+max+" size#"+p.get(0).getChromosome().size() + "Before#"+c1.getChromosome());
      c1.setChromosome((ArrayList<Integer>) p.get(parentId).getChromosome().clone()); //clone individuals
      
      for(int i = min;i<=max;i++)
      {
         //c1.getChromosome().set(i,p.get(parentId).getChromosome().get(max-i+1));
         c1.getChromosome().set(i,p.get(parentId).getChromosome().get(max-count));
         count++;
      }
      //set individual properties for chldren and add to new population
      
      if(!chromosomeHasDuplicateGenes(c1.getChromosome()))
      {
    	  id1.setChromosome(c1.getChromosome());
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
