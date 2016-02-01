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
package ec.individuals;

import java.io.Serializable;
import ec.util.DeepClone;

/**
 * The gene class is represents the node in a vector species. It is able to assume multiple properties
 * of a node in a Genetic Algorithm. It is also the basic unit of a chromosome
 * @author Anthony Awuley
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
		alleles = strToDouble(allel);
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
	public double [] strToDouble(String [] a)
	{
		double [] alel = new double[a.length];
		for(int c=0;c<a.length;c++)
			alel[c] = Double.parseDouble(a[c]);

		return alel;
	}
	
	/**
	 * Convert double node values to string
	 * @param a
	 * @return
	 */
	public String [] doubleToString(double [] a)
	{
		String [] alel = new String[a.length];
		for(int c=0;c<a.length;c++)
			alel[c] = a[c]+"";

		return alel;
	}
	
	/**
	 * 
	 * @return alleles in a node
	 * this could be multiple properties read from the parameter file
	 */
	public double[] getAlleles()
	{
		return alleles;
	}

	/**
	 * 
	 * @return string alleles
	 */
	public String[] getStringAlleles()
	{
		return strAlleles;
	}

	/**
	 * 
	 * @return id of a gene
	 * every gene has a unique id which is read from the parameter file
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
	 * Extend size of alleles
	 * @param all
	 * 
	 */
	public  void addAll(String[] all)
	{
		String[] temp = new String[strAlleles.length + all.length];
		
		for(int i=0;i<strAlleles.length;i++)
			temp[i] = strAlleles[i];
		
		for(int i=strAlleles.length;i<temp.length;i++)
			temp[i] = all[i%strAlleles.length];
		
		strAlleles = temp;
		alleles = strToDouble(temp);
		
	}

	
	/**
	 * Extend size of alleles
	 * @param all
	 * 
	 */
	public  void addAll(double[] all)
	{
		double[] temp = new double[alleles.length + all.length];
		
		for(int i=0;i<alleles.length;i++)
			temp[i] = alleles[i];
		
		for(int i=alleles.length;i<temp.length;i++)
			temp[i] = all[i%alleles.length];
		
		alleles = temp;
		strAlleles = doubleToString(temp);
		
	}
	
	
	/** Deepclone a Gene */
	@Override
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
