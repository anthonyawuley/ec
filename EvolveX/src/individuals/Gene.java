/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package individuals;

import java.util.ArrayList;
import java.util.Properties;

/**
 *
 * @author anthony
 */
public interface Gene{
    
    
    /**
     * Sort the individuals in the population
     */
    public void sort();
    
    /**
     * 
     * @param ch
     * @param prop
     */
    public void generateChromosome(Chromosome ch,Properties prop);
   
    /**
     * Add a collection of individuals to the population
     * @param immigrants collection of individuals
     */
    public void addAll(ArrayList<Gene> immigrants);

    /**
     * @throws CloneNotSupportedException 
     * 
     */
    public Gene clone() throws CloneNotSupportedException;
    

    /**
     * Clear the population of all individuals
     */
    public void clear();

}
