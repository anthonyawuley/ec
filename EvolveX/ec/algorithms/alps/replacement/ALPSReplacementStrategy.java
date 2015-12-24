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
package algorithms.alps.replacement;

import java.util.ArrayList;

import individuals.Individual;
import individuals.populations.Population;
import algorithms.alps.layers.Layer;
import algorithms.alps.system.ALPSLayers;
import algorithms.alps.system.Engine;

public class ALPSReplacementStrategy {

	public Boolean victimFound = Boolean.FALSE;
	public int     victimLayer = 0;
	public int     victimID    = 0;
	
	public ALPSReplacementStrategy() 
	{
	}
	
	
	/**
	 * loop through highest layer to current layer
	 * attempt to move individuals from current layer that have age values within 
	 * higher layer.
	 * 
	 * @param layers
	 * @param to
	 * @return
	 */
	public Population completeLayerMigrations(ArrayList<Layer> layers,int to)
	{
		for(int i=(layers.size()-1);i>=to;i--)
		{
			//reference of pop for (to-1) is modified
			interLayerMigrations(layers,layers.get(to-1).getEvolution().getCurrentPopulation(),i);
			//layers.get(to-1).getEvolution().setCurrentPopulation(interLayerMigrations(
			//		layers,layers.get(to-1).getEvolution().getCurrentPopulation(),i));
			
			/*
			 * remember to remove this code  when performance deteriorates
			 * this attempts to move all individuals to the next immediate upper layer 
			 * even if they are younger
			 */
			if(i==to)
			{
				layers.get(to).getEvolution().getCurrentPopulation().addAll(
						layers.get(to-1).getEvolution().getCurrentPopulation().getAll());
				//to-1 could be cleared of all transfered individuals
				layers.get(to-1).getEvolution().getCurrentPopulation().clear();
			}  
			
		}
		return layers.get(to-1).getEvolution().getCurrentPopulation();
	}
	
	
	/**
	 * 
	 * @param layers
	 * @param move
	 * @param to
	 * @return
	 */
	public Population interLayerMigrations(ArrayList<Layer> layers,Population move,int to)
	{
		Population higherPop = null;
		Population deleteList = new Population();
		
		higherPop = (Population) layers.get(to).
				getEvolution().getCurrentPopulation().clone();
		//re-evaluate ages of population
		 if(layers.get(0).getParameters().getReplacementOperator().equals("SteadyState"))
		 {
			 move.calculateAge((int) Engine.completeEvaluationCount,layers.get(to).getParameters().getPopulationSize());
			 higherPop.calculateAge((int) Engine.completeEvaluationCount,layers.get(to).getParameters().getPopulationSize());	
		 }
		
		for(int i=0;i<move.size();i++)
		{
			if( (move.get(i).getAge() < layers.get(to).getMaxAge()) && 
					(move.get(i).getAge() >= layers.get(to-1).getMaxAge()))
			{   //fill higher layer with individuals that fall withing its age limit
				if(higherPop.size() < layers.get(to).getParameters().getPopulationSize())
				{
					layers.get(to).getEvolution().
					getCurrentPopulation().add(move.get(i));
				}
				else if(higherPop.size() > 0) //once higher layer is filled, do selective replacement based on new individuals that have higher age than in the individual in the  higher layer
				{   //find and replace lowest fitness individual in higher layer with higher fitness individual in lower layer
					for(int j=0; j<layers.get(to).getEvolution().getCurrentPopulation().size(); j++)
					{
						if(/* layers.get(to-1).getEvolution().getCurrentPopulation().get(i).getFitness().getDouble() > */
						   move.get(i).getFitness().getDouble() >
						   layers.get(to).getEvolution().getCurrentPopulation().get(j).getFitness().getDouble())
						{
							/*layers.get(to).getEvolution().getCurrentPopulation().
							   set(j,layers.get(to-1).getEvolution().getCurrentPopulation().get(i));
							  */
							layers.get(to).getEvolution().getCurrentPopulation().set(j,move.get(i));
							break; //get out of loop if one instance is replaced
						}
					}
				}
				deleteList.add(move.get(i));
				//deleteList.add(layers.get(to-1).getEvolution().getCurrentPopulation().get(i));
			}
			else //if individuals age is lower (decide to leave it in its current layer or delete it)
			{
				//deleteList.add(move.get(i));
			}
		}
		//remove all individuals older than current layer
		for(int id=0;id<deleteList.size();id++)
		{
			//layers.get(to-1).getEvolution().getCurrentPopulation().
			//remove(deleteList.get(id));
			move.remove(deleteList.get(id));
		}
		System.out.println("Attempted move of "+ deleteList.size()+ " overaged individuals to Layer#"+to+" "
						 + "current pop size is "+move.size()+ " Layer#"+to+ " pop size is "
				         +layers.get(to).getEvolution().getCurrentPopulation().size()); //System.exit(0);
		
		return move;
	}
	
	
	
	/**
	 * BEGIN TEST CODE 15 --- calculate age before calling method
	 * @param alpsLayers
	 * @param layerID
	 * @param pop
	 */
	public void layerMovements(ALPSLayers alpsLayers,int layerID,Population pop)
	{
		//re-evaluate ages of population
		for(Individual ind: pop.getAll())
		{
			individualLayerMovements(alpsLayers,layerID,ind);
		}
	}
	
	
	
	/**
	 * 
	 * @param alpsLayers
	 * @param to
	 * @param ind
	 */
	@SuppressWarnings({ "unused"})
	public void individualLayerMovements(ALPSLayers alpsLayers,int to,Individual ind)
	{
		if( to+1 < alpsLayers.layers.size() ) //not last layer
		{
		  for(int i=to+1;i<alpsLayers.layers.size();i++) //loop all higher layers
		  {  //check if individual is within the age limit of a layer. individual must be older than previous layer
			 if(victimLayer(alpsLayers,i)) 
			 { 
				if(victimIndividual(//find victim individual in higher population
						 alpsLayers.layers.get(this.victimLayer).getEvolution().getCurrentPopulation(),ind))
				{
					Individual individualReplaced = alpsLayers.layers.get(this.victimLayer).getEvolution().
							                        getCurrentPopulation().get(this.victimID);
					tryMoveUp(alpsLayers.layers.get(this.victimLayer).getEvolution().getCurrentPopulation(),ind,this.victimID);
					//remove individual from previous layer
					alpsLayers.layers.get(alpsLayers.index).getEvolution().getCurrentPopulation().remove(ind);
					//recurssive call to repeat process till all successive replaceable individual are attemted to be moved
					//individualLayerMovements(alpsLayers,this.victimLayer,individualReplaced); 
				}
			 } 
		  }
	   }
	}
	
	
	/**
	 * 
	 * @param alpsLayers
	 * @param layerID
	 * @return
	 */
	public boolean victimLayer(ALPSLayers alpsLayers, int layerID)
	{
		for(Individual ind : alpsLayers.layers.get(layerID).getEvolution().getCurrentPopulation().getAll())
		{ 
		  for(int i= alpsLayers.layers.size()-1;i<=layerID;i--) //loop all higher layers
		  {  //check if individual is within the age limit of a layer. individual must be older than previous layer
			 if(i>0)
			 {
			   if((ind.getAge() < alpsLayers.layers.get(i).getMaxAge()) &&
					 (ind.getAge() >= alpsLayers.layers.get(i-1).getMaxAge())) 
			   { 
			      this.victimLayer = i;
			      return Boolean.TRUE;
			   } 
			 }
		  }
		}
		return Boolean.FALSE;
	}
	
	
	/**
	 * 
	 * @param pop
	 * @param individual
	 * @return boolean indicating victim found with worse fitness
	 */
	public boolean victimIndividual(Population pop, Individual ind)
	{
		for(int i=0;i<pop.size();i++)
		{ 
		     if(ind.getFitness().getDouble() >= pop.get(i).getFitness().getDouble()) 
		     { 
			    this.victimFound = Boolean.TRUE;
			    this.victimID = i;
			    return Boolean.TRUE;
		     } 
		}
		return Boolean.FALSE;
	}
	
	/**
	 * 
	 * @param to
	 * @param ind
	 * @param toID
	 */
	public void tryMoveUp(Population to, Individual ind, int toID)
	{
		to.set(toID,ind);
	}
	

}
