/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package individuals;

import java.util.ArrayList;

import util.random.MersenneTwisterFast;
import individuals.representation.*;


/**
 *
 * @author anthony
 */
public abstract class Representation{
    
	 protected ArrayList<Gene> chromosome = new ArrayList<>();
	 protected MersenneTwisterFast rng;
	
	/**
     * 
     * @param min
     * @param max
     * @return 
     */
    public int integerGene (int min, int max)
    {
    	return IntRep.geneRepresentation(min, max);
    }
    
    /**
     * 
     * @param min
     * @param max
     * @return 
     */
    public int characterGene (int min, int max)
    {
    	return CharRep.geneRepresentation(min, max);
    }
    
    /**
     * 
     * @param min
     * @param max
     * @return 
     */
    public int vrptwGene (int min, int max)
    {
    	return VRPTW.geneRepresentation(min, max);
    }
    
    


	  /**
	   * ant and tabu
	   * @param trim
	   * @return
	   */
	  protected final String[] sweepNumbers(String trim) 
	  {
	    String[] arr = new String[3];
	    int currentIndex = 0;
	    
	    for (int i = 0; i < trim.length(); i++) 
	    {
	      final char c = trim.charAt(i);
	      if ((c) != 32) 
	      {
	        for (int f = i + 1; f < trim.length(); f++) 
	        {
	          final char x = trim.charAt(f);
	          if ((x) == 32) 
	          {
	            arr[currentIndex] = trim.substring(i, f);
	            currentIndex++;
	            break;
	          } 
	          else if (f == trim.length() - 1) 
	          {
	            arr[currentIndex] = trim.substring(i, trim.length());
	            break;
	          }
	        }
	        i = i + arr[currentIndex - 1].length();
	      }
	    }
	    return arr;
	  }
    
}
