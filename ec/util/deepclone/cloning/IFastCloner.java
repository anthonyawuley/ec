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
package ec.util.deepclone.cloning;

import java.util.Map;

/**
 * allows a custom cloner to be created for a specific class.
 * (it has to be registered with Cloner)
 *
 * @author kostantinos.kougios
 * 21 May 2009
 */
public interface IFastCloner {
    public Object clone(Object t, IDeepCloner cloner, Map<Object, Object> clones);
}
