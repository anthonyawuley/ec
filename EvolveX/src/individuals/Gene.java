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
package individuals;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Properties;

import util.DeepClone;

/**
 *
 * @author anthony
 */
public class Gene implements Cloneable, Serializable {
    
	/** */
	private static final long serialVersionUID = 1L;
	/** */
	private double[] alleles;
	/** */
	private String[] strAlleles;
	/** */
	private int id;
	/** */
	public Gene(){}
	
	/**
	 * set node id
	 * @param identity
	 */
	public Gene(int identity)
	{ 
		id = identity; 
	}
	
	/**
	 * 
	 * @param identity
	 * @param allel
	 * an chromosome as in this example is made of of id = customer number <br>
	 * and alleles of x-cord, y-cord, demand, ready time, due date and service time <br>
	 * e.g of alleles could be or simple city x y cordinates for a TSP problem <br>
	 * in this example, each node represents a gene  <br>
	 *  #CUST NO.  XCOORD.    YCOORD.    DEMAND    READY TIME  DUE DATE   SERVICE TIME <br>
     *  node.1      40.00      50.00       0.00       0.00    1236.00       0.00 <br> 
     *  node.2      45.00      68.00      10.00       0.00    1127.00      90.00 <br> 
     *  node.3      45.00      70.00      30.00       0.00    1125.00      90.00 <br> 
     *  ...
	 */
	public Gene(int identity, String[] allel)
	{
		id = identity;
		alleles = setAleles(allel);
		strAlleles = allel;
	}
	
	/**
	 * 
	 * @param a
	 * @return
	 * e.g of alleles could be or simple city x y cordinates for a TSP problem
	 *  #CUST NO.  XCOORD.    YCOORD.    DEMAND    READY TIME  DUE DATE   SERVICE TIME 
     *  node.1      40.00      50.00       0.00       0.00    1236.00       0.00 
     *  node.2      45.00      68.00      10.00       0.00    1127.00      90.00 
     *  node.3      45.00      70.00      30.00       0.00    1125.00      90.00 
	 */
	public double [] setAleles(String [] a)
	{
		double [] alel = new double[a.length];
		for(int c=0;c<a.length;c++)
			alel[c] = Double.parseDouble(a[c]);
		
		return alel;
	}
	/**
	 * 
	 * @return
	 */
	public double[] getAlleles()
	{
		return alleles;
	}
    
	/**
	 * 
	 * @return
	 */
	public String[] getStringAlleles()
	{
		return strAlleles;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getId()
	{
		return id;
	}
    /**
     * Sort the individuals in the population
     */
    public  void sort(){};
    
    /**
     * 
     * @param ch
     * @param prop
     * @deprecated
     */
    public  void generateChromosome(Chromosome ch,Properties prop){};
   
    /**
     * Add a collection of individuals to the population
     * @param immigrants collection of individuals
     */
    public  void addAll(ArrayList<Gene> immigrants){};

    /**
     * @throws CloneNotSupportedException 
     * 
    
    public  Gene clone() throws CloneNotSupportedException
    {
		return null;
	}
    */
    /** */
    public Gene clone()
    {
        return (Gene) DeepClone.clone(this);
    }
    
	/**
	 * when id == -1, it means the gene is considered invalid
	 * @return true if valid else false
	 */
	public boolean validateGene()
	{
		return id !=-1;
	}
	
    /**
     * Clear the population of all individuals
     */
    public  void clear()
    {
    	alleles = null;
    	id = -1;
    }

}
