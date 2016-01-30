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

import ec.individuals.IndividualInterface;
import java.util.List;

/**
 *
 * @author anthony
 */
public interface Operation {
    
    public void doOperation(List<IndividualInterface> operands);

    /**
     * Performs the operation on an operand
     * @param operand operand to perform operation on
     */
    public void doOperation(IndividualInterface operand);   
    
}
