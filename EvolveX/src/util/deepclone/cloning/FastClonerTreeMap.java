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
package util.deepclone.cloning;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author kostantinos.kougios
 *
 * 21 May 2009
 */
public class FastClonerTreeMap implements IFastCloner
{
	@SuppressWarnings({ "unchecked", "rawtypes" })
    public Object clone(final Object t, final IDeepCloner cloner, final Map<Object, Object> clones) {
		final TreeMap<Object, Object> m = (TreeMap) t;
		final TreeMap result = new TreeMap(m.comparator());
		for (final Map.Entry e : m.entrySet())
		{
            final Object key = cloner.deepClone(e.getKey(), clones);
            final Object value = cloner.deepClone(e.getValue(), clones);
            result.put(key, value);
		}
		return result;
	}
}
