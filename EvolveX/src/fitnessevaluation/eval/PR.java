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
package fitnessevaluation.eval;

import java.util.ArrayList;
import java.util.Properties;

import fitnessevaluation.multiobjective.ParetoRanking;
import individuals.populations.Population;

public class PR extends WS{

	public PR() {
		// TODO paretho ranking
	}
	
    /**
     * activate later
     * @param pop
     * @return 
     * @deprecated
     */
    public double setAverageFitness(Population pop)
    {
       double averageFitness = 0;
       for( int i=0; i< pop.size(); i++)
       {
          averageFitness += sumDistanceVRP(pop.get(i).getChromosome(),this.getProperties());
       }
       return averageFitness/pop.size();
    }
   


    /**
     * 
     * @return
     * overloaded from parent classs with last parameter
     * boolean fitnessOrdering
     */
    public ArrayList<Double> calcGenerationalFitness(Population pop,Properties p)
   	{
 	      ParetoRanking pr = new ParetoRanking();
 	      
   		  double sum = 0;
   		  
   		  //get elements ranked using SOR
   		  this.setGenerationFitness(pr.paretoCalculations(changePopFormatForMOP(pop,p,","),pop.size()));
   		  //add total fitnes of rank
   		  for(double d: this.getGenerationFitness())
   		  {
   			  sum+=d;
   		  }
   		this.setTotalFitness(sum); //total fitness
   		 
   	     return this.getGenerationFitness();
   	}
	

}
