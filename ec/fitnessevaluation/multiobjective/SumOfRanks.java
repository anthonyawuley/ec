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
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.fitnessevaluation.multiobjective;

import ec.util.random.GenerateMask;
import ec.individuals.populations.Population;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author anthony
 */
public class SumOfRanks extends MultiObjective{
    
    
    /**
     * 
     * @param ranks
     * @param popSize 
     * 
     * #SORTED
     * ranks  obj1 obj2 normalized sum-of-ranks
     * 2,1000 1 5 1.2
     * 4,600  2 3 1.0
     * 7,800  3 4 1.4
     * 8,400  4 1 1.0
     * 9,500  5 2 1.4 
     */
    @SuppressWarnings("unchecked")
	public ArrayList<Double> sorCalculations(ArrayList<String> ranks, int popSize)
    {
        ArrayList<Double> r1 = new ArrayList<>();
        ArrayList<Double> selectionRank = new ArrayList<>();
        ArrayList<String> rankCopy = (ArrayList<String>) ranks.clone();
        
        Collections.sort(ranks);
        //System.out.println("#SORTED");
       
         for(int j=0; j<ranks.size();j++)
         { 
             String[] sp = ranks.get(j).split(","); // ranks.add("2,1000")
             r1.add(Double.parseDouble(sp[1]));
             
             //pad with dummy values to enable set of content in later code
             //remove if problems are reported
             selectionRank.add(1.00); 
         }
         //sort both values
         Collections.sort(r1);
       
         for(int j=1; j<=ranks.size();j++)
         { 
             String[] sp = ranks.get(j-1).split(","); // ranks.add("2,1000")
             
             //selectionRank.add(j-1,((j+( GenerateMask.returnIndexD(r1,Double.parseDouble(sp[1])+"") +1))/(double)ranks.size()));
             
             //set appropriate index with sum of rank value
             selectionRank.set(GenerateMask.returnIndexS(rankCopy,ranks.get(j-1)),((j+( GenerateMask.returnIndexD(r1,Double.parseDouble(sp[1])+"") +1))/(double)ranks.size()));
             //System.out.println(ranks.get(j-1) +":"+ GenerateMask.returnIndexS(rankCopy,ranks.get(j-1))+" " + j + " "+ ( GenerateMask.returnIndexD(r1,Double.parseDouble(sp[1])+"") +1) + " " + selectionRank.get(j-1));
             //System.out.println(selectionRank.get(GenerateMask.returnIndexS(rankCopy,ranks.get(j-1))) +":"+ ranks.get(j-1) +" "+ rankCopy.get(j-1));

         }
         
         return selectionRank;
         
    }

	/**
	 * 
	 * @param pop
	 * @param run
	 * @param generation
	 */
	public void calculateSimpleFitness(Population pop, int run, int generation) 
	{
		// TODO Auto-generated method stub
		
	}
    
}
