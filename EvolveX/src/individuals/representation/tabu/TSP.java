/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package individuals.representation.tabu;

import individuals.Gene;
import individuals.Representation;

import java.util.ArrayList;



/**
 *
 * @author anthony
 */
public class TSP  extends Representation{
    
	   //private ArrayList<Integer> defaultChromeContainer = new ArrayList<>();
	 

	  public TSP()
	  {
		  
	  }
	
	  public static int[] startSolution(int length)
	  {
		 int [] startSolution = new int[length+1];
		 //begin count from 1, since 0 is used as depot
		 for(int i=0;i<length;i++)
	     {  
	       startSolution[i] = i; 
	     }
		 startSolution[length] = 0;
		 
		 return startSolution;
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
	 
}
