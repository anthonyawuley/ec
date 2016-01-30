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
package ec.operator.operations;

import ec.individuals.populations.Population;

/**
 *
 * @author anthony
 */
public class StoppingCondition {
    
	private boolean stopWhenSolved;
    private boolean idealIndividualFound;
    
    
    public StoppingCondition(boolean stopFlagSet)
    {
    }
    
    
    public boolean isSatisfied(Population current) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
    /**
     * 
     * @param currentCount
     * @param totalCount
     * @return 
     */
    public boolean generationCount(int currentCount,int totalCount)
    {
       return currentCount==totalCount;
    }
    
    public void setIdealIndividualFound(boolean flag)
    {
       this.idealIndividualFound = flag;
    }
    
    
    public boolean getIdealIndividualFound()
    {
       return this.idealIndividualFound;
    }
    
    public void setStopWhenSolved(boolean flag)
    {
       this.stopWhenSolved = flag;
    }
    
    
    public boolean getStopWhenSolved()
    {
       return this.stopWhenSolved;
    }
    
}
