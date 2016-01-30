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
package ec.fitnessevaluation.eval;

import java.util.ArrayList;
import java.util.Properties;

import ec.fitnessevaluation.multiobjective.SumOfRanks;
import ec.individuals.populations.Population;

public class SOR extends WS{

	  public SOR() 
	  {
		// TODO sum of runks
	  }
	
    
    
       /**
        * activate later
        * @param pop
        * @return 
        * @deprecated
        */
       @Deprecated
		@Override
	public double setAverageFitness(Population pop)
       {
          double averageFitness = 0;
          for( int i=0; i< pop.size(); i++)
             averageFitness += sumDistanceVRP(pop.get(i).getChromosome(),this.getProperties());
          
          return averageFitness/pop.size();
       }

       
    
       /**
        * 
        * @return
        * overloaded from parent class
        */
       @Override
	public ArrayList<Double> calcGenerationalFitness(Population pop,Properties p)
      	{
    	      SumOfRanks sor = new SumOfRanks();
    	      
      		  double sum = 0.0d;
      		  
      		  //get elements ranked using SOR
      		  this.setGenerationFitness(sor.sorCalculations(changePopFormatForMOP(pop,p,","),pop.size()));
      		  //add total fitnes of rank
      		  for(double d: getGenerationFitness())
      			  sum+=d;
      	     
      	     this.setTotalFitness(sum); //total fitness
      	  return this.getGenerationFitness();
      	}



}
