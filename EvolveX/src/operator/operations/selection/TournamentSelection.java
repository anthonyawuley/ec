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
package operator.operations.selection;

import util.random.GenerateMask;
import util.random.MersenneTwisterFast;

import java.util.ArrayList;

import algorithms.alps.system.ALPSLayers;
import operator.operations.SelectionOperation;
import util.random.RandomGenerator;

/**
 *
 * @author anthony
 */
public class TournamentSelection implements SelectionOperation {
    
    private int tournamentSize=0;
    private ArrayList<Integer> tournamentSelect                = new ArrayList<>();
    private ArrayList<ArrayList<Integer>> alpsTournamentSelect = new ArrayList<>();
    private final String selectionOperation     = "Tournament selection";
    private final int loopCap                   = 30;
    

    /**
     * 
     */
 	public String toString() 
 	{
 		return this.selectionOperation;
 	}
    
    
 	/**
 	 * 
 	 * @return
 	 */
    public int getTournamentSize()
    {
        return this.tournamentSize;
    }
    
    /**
     * 
     * @param tSize
     */
    public void setTournamentSize(int tSize)
    {
        this.tournamentSize = tSize;
    }
    
    /**
     *
     * @param populationSize
     * @param tournamentSize
     * @return id's of selected individuals
     */
    public ArrayList<Integer> performTournamentSelection(int populationSize, int tournamentSize)
    {
    	
        int select; 
        this.tournamentSelect.clear();
        
        for(int i=0;i<tournamentSize;i++)
        { //replace with a more efficient implementation
          int count = 0;
          do
          {
             select = RandomGenerator.getRandomNumberBetween(0,populationSize-1); //replace value with pop size
             count++;
             if(count>loopCap)
            	 break;
          }
          while(GenerateMask.isExistIndex(this.tournamentSelect, select+""));
          this.tournamentSelect.add(select);
        }
        //System.out.println("#pop size "+populationSize + " tournament " + this.tournamentSelect);
       return this.tournamentSelect; 
    }
    
    
    /**
    *
    * @param populationSize
    * @param tournamentSize
    * @return id's of selected individuals
    */
   public ArrayList<Integer> performTournamentSelection(
		   ALPSLayers alpsLayers, int populationSize, int tournamentSize)
   {
        int select; 
       
        this.alpsTournamentSelect.clear();
       
        if(alpsLayers.layers.get(alpsLayers.index).getIsBottomLayer()
        		//|| alpsLayers.layers.get(alpsLayers.index).getId()==1
        		)
        {   //System.out.println("botom layer" + alpsLayers.layers.get(alpsLayers.index).getEvolution().getCurrentPopulation().size());
        	this.tournamentSelect.clear();
        	this.tournamentSelect = 
        	performTournamentSelection(
        			//populationSize,
        			alpsLayers.layers.get(alpsLayers.index).getEvolution().getCurrentPopulation().size(), //more dynamic
        			alpsLayers.layers.get(alpsLayers.index).getParameters().getTournamentSize());
        }
        else //higher layers 1...N
        {  
        	this.tournamentSelect.clear();
        	
        	int currentLayerPopSize = alpsLayers.layers.get(alpsLayers.index).getEvolution().getCurrentPopulation().size();
        	int lowerLayerPopSize   = alpsLayers.layers.get(alpsLayers.index-1).getEvolution().getCurrentPopulation().size();
        	//int limit = alpsLayers.layers.get(alpsLayers.index).getEvolution().getCurrentPopulation().size() - 
            //		alpsLayers.layers.get(alpsLayers.index-1).getEvolution().getCurrentPopulation().size();
        	
        	//System.out.println("INDEX::"+ alpsLayers.index+ " currentLayerPopSize:::"+currentLayerPopSize+" lowerLayerPopSize:::"+lowerLayerPopSize);
        	while(this.tournamentSelect.size()<tournamentSize) //for(int i=0;i<tournamentSize;i++) 
            { 
        		RandomGenerator randGen = new RandomGenerator(); 
                MersenneTwisterFast mtf = new MersenneTwisterFast();
                mtf.setSeed(alpsLayers.layers.get(alpsLayers.index).getParameters().getSeed()); //set seed
                //this gives actual current layer population size
                
                //System.out.println("TOTAL "+ currentLayerPopSize +" "+lowerLayerPopSize + " pop size#"+  populationSize); 
                
                /**
                 * selection pressure for first half (current layer):
                 * if overall size is less than populationSize, it means first half is empty or second half (lower layer) is empty
                 */
        		if(randGen.nextDouble() <= alpsLayers.layers.get(alpsLayers.index).getParameters().getLayerSelectionPressure() &&
        				currentLayerPopSize >0)
        		{ //n%
        			 int count = 0;
        			  do
        	          {  //System.out.println("loop1 "+ currentLayerPopSize+ " "+ lowerLayerPopSize + " Tournament: "+this.tournamentSelect.size());
        				 //select random number from first half 0 - n/2
        	             //select = RandomGenerator.getRandomNumberBetween(0,(int) Math.floor(populationSize/2)-1); //replace value with pop size
        	             select = RandomGenerator.getRandomNumberBetween(0,currentLayerPopSize-1); //replace value with pop size
        	             count++;
        	             if(count>loopCap)
        	            	break;
        	          }
        	          while(GenerateMask.isExistIndex(this.tournamentSelect, select+""));
        	          this.tournamentSelect.add(select);
        		}
        		else if(lowerLayerPopSize>0) //(100-n)%
        		{ //System.out.println("population size##"+ populationSize +" limit " + limit);
        			int count = 0;
        			  do
        	          {  //System.out.println("loop2 "+ currentLayerPopSize+ " "+ lowerLayerPopSize + " Tournament: "+this.tournamentSelect.size());
        				 //select random number from second half n/2 - n
        	             //select = RandomGenerator.getRandomNumberBetween((int) Math.ceil(populationSize/2),populationSize-1); //replace value with pop size
        	             select = RandomGenerator.getRandomNumberBetween(currentLayerPopSize,(lowerLayerPopSize+currentLayerPopSize)-1); //replace value with pop size
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
   public ArrayList<Integer> performTournamentSelectionWithLimits(
		   int tournamentSize,int min, int max)
   {
   	
       int select; 
       this.tournamentSelect.clear();
       
       for(int i=0;i<tournamentSize;i++)
       {  
    	   do
	        { 
	          select = RandomGenerator.getRandomNumberBetween(min,max); //replace value with pop size
	        }
	        while(GenerateMask.isExistIndex(this.tournamentSelect, select+""));
	        this.tournamentSelect.add(select);
       }
       //System.out.println("#pop size "+populationSize + " tournament " + this.tournamentSelect);
      return this.tournamentSelect; 
   }
    
   
   /**
    * 
    * @param populationSize
    * @param tournamentSize
    * @deprecated
   
    public void setTournamentSelection(int populationSize, int tournamentSize)
    {
       this.tournamentSelect = performTournamentSelection(populationSize, tournamentSize);
    }
     */
    
    /**
     *
     */
    private ArrayList<Integer> setTournamentSelection
    (ArrayList<Integer> tournamentSelect)
    {
       return this.tournamentSelect = tournamentSelect;
    }
    
    /**
     * 
     */
    public ArrayList<Integer> getTournamentSelection()
    {
       return this.tournamentSelect;
    }
  
    
}
