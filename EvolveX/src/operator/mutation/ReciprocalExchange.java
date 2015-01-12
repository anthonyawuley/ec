/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
    @SuppressWarnings("unchecked")
	public ArrayList<Individual> performMutationOperation(Population p, Chromosome c1,int parentId)
    {
      Individual id1 = new Individual();
      ArrayList<Individual> child = new ArrayList<>();
        
      twoPointsOnChromosome = Operator.selectTwoPointsRandomly(p.get(0).getChromosome().size()-1); //replace value with pop size
      
      c1.setChromosome((ArrayList<Integer>) p.get(parentId).getChromosome().clone()); //clone individuals
      
      c1.getChromosome().set(twoPointsOnChromosome[0],p.get(parentId).getChromosome().get(twoPointsOnChromosome[1]));
      c1.getChromosome().set(twoPointsOnChromosome[1],p.get(parentId).getChromosome().get(twoPointsOnChromosome[0]));
  
       //set individual properties for chldren and add to new population
      id1.setChromosome(c1.getChromosome());
      id1.setFitness(new BasicFitness()); //set fitness object
      child.add(id1);
      setOffsprings(child);
      return child;
    }
    
    
    /**
     * ALPS: OVerloaded
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
        
      twoPointsOnChromosome = Operator.selectTwoPointsRandomly(p.get(0).getChromosome().size()-1); //replace value with pop size
      
      c1.setChromosome((ArrayList<Integer>) p.get(parentId).getChromosome().clone()); //clone individuals
      
      c1.getChromosome().set(twoPointsOnChromosome[0],p.get(parentId).getChromosome().get(twoPointsOnChromosome[1]));
      c1.getChromosome().set(twoPointsOnChromosome[1],p.get(parentId).getChromosome().get(twoPointsOnChromosome[0]));
    
       //set individual properties for chldren and add to new population
      id1.setChromosome(c1.getChromosome());
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
