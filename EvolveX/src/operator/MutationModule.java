/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package operator;

import individuals.Chromosome;
import individuals.Individual;
import individuals.populations.Population;

import java.util.ArrayList;

/**
 *
 * @author anthony
 */
public abstract class MutationModule extends Operator {
    
    private String mutationType;
    private final String mutationOperation ="mutation operation";
    private double mutationRate;
    
    
    public  MutationModule (String type)
    {
       this.mutationType = type;
    }
   
    /**
     * Returns the mutation policy.
     * @return mutation policy
     */
    public String getMutationPolicy() 
    {
        return mutationOperation;
    }

    /**
     * Returns the mutation rate.
     * @return mutation rate
     */
    public double getMutationRate() 
    {
        return this.mutationRate;
    }
    
    /**
     * 
     * @param rate 
     */
    public  void getMutationRate(double rate) 
    {
        this.mutationRate = rate;
    }
    
    
    /**
     * 
     * @param p
     * @param c1
     * @param parentId
     * @return
     */
    public abstract ArrayList<Individual> performMutationOperation(
    		Population p, 
    		Chromosome c1,
    		int parentId);
    
    /**
     * 
     * @param p
     * @param c1
     * @param parentId
     * @param ages
     * @return
     */
    public abstract ArrayList<Individual> performMutationOperation(
    		Population p, 
    		Chromosome c1,
    		int parentId,
    		ArrayList<Double> ages,
    		String replacementType);
    
    public String getSpecificModuleType()
    {
       return this.mutationType;
    }

     /**
     * Get operation that operator performs
     * @return operation
     */
    public String getOperation()
    {
       return this.mutationOperation;
    }
    
    
}
