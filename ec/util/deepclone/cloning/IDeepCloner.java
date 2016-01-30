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
 * used by fast cloners to deep clone objects
 *
 * @author kostas.kougios Date 24/06/14
 */
public interface IDeepCloner {
    /**
     * deep clones o
     *
     * @param o      the object to be deep cloned
     * @param clones pass on the same map from IFastCloner
     * @param <T>    the type of o
     * @return a clone of o
     */
    <T> T deepClone(final T o, final Map<Object, Object> clones);
}
