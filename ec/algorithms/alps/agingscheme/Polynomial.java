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
package ec.algorithms.alps.agingscheme;

import java.util.ArrayList;

import ec.algorithms.alps.AgingScheme;
import ec.algorithms.alps.layers.Layer;

/**
 * 
 * @author Anthony Awuley
 *
 */
public class Polynomial  implements AgingScheme {

	
	public Polynomial() 
	{
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString(String str)
	{
		return "polynomial aging scheme selected " + str;
	}
	
	/**
	 * @return n^2
	 */
	@Override
	public ArrayList<Layer> agingScheme(int ageGap,int ageLayers)
	{
		this.toString(" with an age gap of "+ ageGap +" and "+ ageLayers +" layers");
		ArrayList<Layer> layers  = new ArrayList<>();
		
		for(int i=0; i<ageLayers;i++)
		{
			Layer layer = new Layer();
			layer.setIsActive(Boolean.FALSE);
			layer.setGenerationalCount(0); //initialize generational count
			layer.setId(i);
			
			if(i==0)
			{
				layer.setMaxAgeLayer(1*ageGap);//0:1
				layer.setIsBottomLayer(Boolean.TRUE);
				layer.setGenerations(layer.getMaxAge());
			}
			else
			{
				if(i==1) 
				{
					layer.setMaxAgeLayer(2*ageGap); // 1:2
				}
				else
				{
					layer.setMaxAgeLayer((int) Math.pow(i,2)*ageGap);	
				}
				layer.setIsBottomLayer(Boolean.FALSE);
				layer.setGenerations( //get the difference between maximum of this layer and previous layer
				           layer.getMaxAge() - layers.get(i-1).getMaxAge());
			}
			layers.add(layer);
			//layers.set(i, layer);
		}
	
		return layers;
	}

}
