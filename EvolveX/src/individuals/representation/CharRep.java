/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package individuals.representation;

import java.util.ArrayList;
import java.util.Properties;

import individuals.Chromosome;
import individuals.Gene;
import individuals.Representation;
import util.random.RandomGenerator;



/**
 *
 * @author anthony
 */
public class CharRep extends Representation implements Gene{
    
   /**
    * 
    * @param min
    * @param max
	* @return
	*/
	public static int geneRepresentation(int min, int max)
	{
	    return RandomGenerator.getMultiThreadedRandNumber(min,max);
	}

	@Override
	public void sort() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addAll(ArrayList<Gene> immigrants) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}
	
	 /**
     * @throws CloneNotSupportedException 
     * 
     */
    public Gene clone() throws CloneNotSupportedException
    {
    	return (Gene) super.clone();
    }

	@Override
	public void generateChromosome(Chromosome ch,Properties prop) {
		// TODO Auto-generated method stub
		//return 0;
	}
    
	
}
