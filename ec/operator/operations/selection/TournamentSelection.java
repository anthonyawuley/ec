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
package ec.operator.operations.selection;

import ec.util.random.GenerateMask;
import java.util.ArrayList;

import ec.algorithms.alps.system.ALPSLayers;
import ec.algorithms.ga.Evolve;
import ec.operator.operations.SelectionOperation;
import ec.util.random.RandomGenerator;

/**
 * Tournament selection. select n individuals from the population and select the best/weakest 
 * 
 * @author  Anthony Awuley
 */
public class TournamentSelection implements SelectionOperation {
    /** */
    private ArrayList<Integer> tournamentSelect                = new ArrayList<>();
    /** */
    private ArrayList<ArrayList<Integer>> alpsTournamentSelect = new ArrayList<>();
    /** */
    private final String selectionOperation     = "Tournament selection";
    /** */
    private final int loopCap                   = 30;
    

 	@Override
	public String toString() 
 	{
 		return this.selectionOperation;
 	}
    
    
    /**
     *
     * @param populationSize
     * @param tournamentSize
     * @return id's of selected individuals
     */
    @Override
	public ArrayList<Integer> performTournamentSelection(Evolve e, int popSize)
    {
    	
        int select; 
        this.tournamentSelect.clear();
        
        for(int i=0;i<e.tournamentSize;i++)
        { //replace with a more efficient implementation
          int count = 0;
          do
          {
             select = RandomGenerator.getRandomNumberBetween(e,0,popSize-1); //replace value with pop size
             count++;
             if(count>loopCap)
            	 break;
          }
          while(GenerateMask.isExistIndex(this.tournamentSelect, select+""));
          this.tournamentSelect.add(select);
        }
       
       return this.tournamentSelect; 
    }
    
    
    /**
    *
    * @param populationSize
    * @param tournamentSize
    * @return id's of selected individuals
    */
   @Override
   public ArrayList<Integer> performTournamentSelection(Evolve e,
		   ALPSLayers alpsLayers)
   {
        int select; 
       
        this.alpsTournamentSelect.clear();
       
        if(alpsLayers.layers.get(alpsLayers.index).getIsBottomLayer())
        {   
        	this.tournamentSelect.clear();
        	this.tournamentSelect = 
        	performTournamentSelection(
        			e,
        			alpsLayers.layers.get(alpsLayers.index).getEvolution().getCurrentPopulation().size() //more dynamic
        			);
        }
        else //higher layers 1...N
        {  
        	this.tournamentSelect.clear();
        	
        	int currentLayerPopSize = alpsLayers.layers.get(alpsLayers.index).getEvolution().getCurrentPopulation().size();
        	int lowerLayerPopSize   = alpsLayers.layers.get(alpsLayers.index-1).getEvolution().getCurrentPopulation().size();
            
        	while(this.tournamentSelect.size()<e.tournamentSize) //for(int i=0;i<tournamentSize;i++) 
            { 
                /* 
                 * selection pressure for first half (current layer):
                 * if overall size is less than populationSize, it means first half is empty or second half (lower layer) is empty
                 */
        		if(e.random.nextDouble() <= alpsLayers.layers.get(alpsLayers.index).getParameters().getLayerSelectionPressure() &&
        				currentLayerPopSize >0)
        		{ //n%
        			 int count = 0;
        			  do
        	          {  //select random number from first half 0 - n/2
        	             //select = RandomGenerator.getRandomNumberBetween(0,(int) Math.floor(populationSize/2)-1); //replace value with pop size
        	             select = RandomGenerator.getRandomNumberBetween(e,0,currentLayerPopSize-1); //replace value with pop size
        	             count++;
        	             if(count>loopCap)
        	            	break;
        	          }
        	          while(GenerateMask.isExistIndex(this.tournamentSelect, select+""));
        	          this.tournamentSelect.add(select);
        		}
        		else if(lowerLayerPopSize>0) //(100-n)%
        		{ 
        			int count = 0;
        			  do
        	          {  //select random number from second half n/2 - n
        	             //select = RandomGenerator.getRandomNumberBetween((int) Math.ceil(populationSize/2),populationSize-1); //replace value with pop size
        	             select = RandomGenerator.getRandomNumberBetween(e,currentLayerPopSize,(lowerLayerPopSize+currentLayerPopSize)-1); //replace value with pop size
        	             count++;
        	             if(count>loopCap)
        	            	break;
        	          }
        	          while(GenerateMask.isExistIndex(this.tournamentSelect, select+""));
        	          this.tournamentSelect.add(select);
        		}
            }
        }
        
        setTournamentSelection(this.tournamentSelect);
       //System.out.println("#pop size "+populationSize + " tournament " + this.tournamentSelect);
      return tournamentSelect; 
   }
    
   
   /**
    * 
    * @param tournamentSize
    * @param min
    * @param max
    * @return
    */
   @Override
   public ArrayList<Integer> performTournamentSelectionWithLimits(
		   Evolve e,int min, int max)
   {
   	
       int select; 
       this.tournamentSelect.clear();
       
       for(int i=0;i<e.tournamentSize;i++)
       {  
    	   do
	        { 
	          select = RandomGenerator.getRandomNumberBetween(e,min,max); //replace value with pop size
	        }
	        while(GenerateMask.isExistIndex(this.tournamentSelect, select+""));
	        this.tournamentSelect.add(select);
       }
      
      return this.tournamentSelect; 
   }
   /**
    * set tournament individuals
    * @param tournamentSelect
    * @return
    */
    private ArrayList<Integer> setTournamentSelection(ArrayList<Integer> tournamentSelect)
    {
       return this.tournamentSelect = tournamentSelect;
    }
    
    /**
     * 
     */
    @Override
	public ArrayList<Integer> getTournamentSelection()
    {
       return this.tournamentSelect;
    }
  
    
}
