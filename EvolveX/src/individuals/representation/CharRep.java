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
 * @deprecated
 */
public class CharRep extends Representation {
    
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

	public void sort() {
		// TODO Auto-generated method stub
		
	}

	public void addAll(ArrayList<Gene> immigrants) {
		// TODO Auto-generated method stub
		
	}

	
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

	
	public void generateChromosome(Chromosome ch,Properties prop) {
		// TODO Auto-generated method stub
		//return 0;
	}
    
	
}
