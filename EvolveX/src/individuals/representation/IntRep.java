/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package individuals.representation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;
import java.util.Random;

import individuals.Chromosome;
import individuals.Gene;
import individuals.Representation;
import util.random.MersenneTwisterFast;
import util.random.RandomGenerator;


/**
 *
 * @author anthony
 */
public class IntRep  extends Representation implements Gene{
   
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
		public void generateChromosome(Chromosome ch,Properties prop)
		{
		   this.chromosome.clear();    
		   for(int i=0;i<ch.getChromosomeSize();i++)
		   {  
			 this.chromosome.add(geneRepresentation(1,ch.getChromosomeSize()*14));//*14 to increase the range 
			 System.out.print(this.chromosome.get(i)+" ");    
		   } System.out.println();
			//set chromosome in base class
		    //added
		    this.rng = new MersenneTwisterFast();
			Collections.shuffle(this.chromosome, new Random(this.rng.nextLong()));
			
			ch.setChromosome(this.chromosome);
	     }
    
 }
