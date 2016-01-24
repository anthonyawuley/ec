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
import java.util.Collections;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;

import util.Constants;
import util.DeepClone;
import util.random.MersenneTwisterFast;

/**
 *
 * @author anthony
 */
public class Chromosome extends Representation implements Cloneable, Serializable {
    
  
    /** */
	private static final long serialVersionUID = 1L;

	//private ArrayList<Integer> chromosome = new ArrayList<>();
	/** */
	private ArrayList<Gene>   chromosome = new ArrayList<>();
    /** */
    private int chromeSize;
    
    /**
     * 
     * @param ch
     * @param p
     */
    public void createChromosome(Chromosome ch,Properties p, MersenneTwisterFast rng) 
	   {
    	
    	int start     = Integer.parseInt(p.getProperty(Constants.START_NODE));
        //int depotNode     = Integer.parseInt(p.getProperty(Constants.DEPOT_NODE));
    	
		  //limit repeated call to function, since its unecessary
		  if(chromosome.isEmpty())
			  create(start,ch.getChromosomeSize(),p);
		
		  /*
		   * shuffle content of chromosome
		   * once initial chromosome is created, shuffle for
		   * x number of calls to create an individual
		   */
		  Collections.shuffle(chromosome, new Random(rng.nextLong()));
	      ch.setGenes(chromosome);
	    }
    
    
    
      /**
       * TODO modify start based on problem type
       * @param size
       * @param p
       */
	   private void create(int start, int size,Properties p)
	   {
		   //int start = 1;
		   chromosome.clear();
		   //begin count from 1, since 0 is used as depot
		   for(int i=start;i<=size;i++)
	       {  
			   Gene g = null;
			   try
			   {
			      g = new Gene(i,p.getProperty(Constants.CO_ORDINATES+"."+i).split("\\s{1,}"));
			   }
			   catch(NullPointerException e){
				  g = new Gene(i,p.getProperty(""+i).split("\\s{1,}"));
			   }
			   
	    	   chromosome.add(g); 
	       }
	   }
    
    /**
     * 
     * @return
     */
	 public ArrayList<Integer> convertToInt()
	 {
		 ArrayList<Integer> toInt = new ArrayList<>();
		   
		 for(int i=0;i<this.chromosome.size();i++)
			 toInt.add(this.chromosome.get(i).getId());
		   
		 return toInt;
	 }
	   

    /**
     * -revert if new doesnt work
     * @throws CloneNotSupportedException 
     * 
     
    public Chromosome clone() throws CloneNotSupportedException
    {
        return (Chromosome) super.clone();
    }
    */
    
    public Chromosome clone()
    {
        return (Chromosome) DeepClone.clone(this);
    }
    
    /**
     * 
     * @param id 
     * @return finds a gene in chromosome with specified id, else returns an invalid Gene
     * check id of returned gene before performing operation
     */
    public Gene findGene(int id)
    {
    	for(Gene g: chromosome)
    		if(g.getId()==id)
    			return g;
    	
    	return new Gene(-1); //failed
    }
    
    /**
     * 
     * @param gene
     * @return
     */
    public Gene findGene(Gene gene)
    {
    	for(Gene g: chromosome)
    		if(g.getId()==gene.getId())
    			return g;
    	
    	return new Gene(-1); //failed
    }
    
    /**
     * 
     * @param chrome
     * @return
     */
    public boolean isFeasible(ArrayList<Gene> ch)
    {
      return this.chromosome.size()==ch.size();
    }
    
    /**
     * @param length
     * @return 
     * 
     * @deprecated
     */
    public void createChromosome(Chromosome ch,Gene g,Properties prop)
    {
       //this.chromosome.clear();
       
       //g.generateChromosome(ch,prop);
       
       /*
       for(int i=0;i<ch.getChromosomeSize();i++)
       {  //create chromosome using integer gene
    	  Gene g = new Gene();
          this.chromosome.add(g.integerGene(1,ch.getChromosomeSize()*14));//*14 to increase the range 
          System.out.print(this.chromosome.get(i)+" ");    
       } System.out.println();
       //set chromosome in base class
       ch.setChromosome(this.chromosome);
       
       */
    }
    
   
    /**
     * 
     * @param chrome 
     */
    public void setGenes(ArrayList<Gene> chrome)
    { 
        chromosome = chrome;
    }
    
    /**
     * 
     * @return 
     */
    public ArrayList<Gene> getGenes()
    {
        return chromosome;
    }
    
    /**
     * 
     * @param length
     */
    public void setChromosomeSize(int length)
    {
        this.chromeSize = length;
    }
    
    /**
     * 
     * @return 
     */
    public int getChromosomeSize()
    {
         //return this.chromosome.isEmpty()?
         //this.chromeSize:this.chromosome.size();
         return this.chromeSize;
    }
    
    /**
     * 
     * @return 
     */
    public ArrayList<Gene> getChromosomeConstraint()
    {
       return chromosome;
    }
    
    
    /**
     * Add an entire gene to the chromosome
     * @param genes Gene to add
     */
    public void addAll(ArrayList<Gene> genes)
    {
    	Iterator<Gene> indIt = genes.iterator();
        while (indIt.hasNext()) 
        	this.chromosome.add(indIt.next());
    }

    /**
     * Add an gene to the population
     * @param g Gene to add
     */
    public void add(Gene g)
    {
    	chromosome.add(g);
    }

    /**
     * Remove gene from chromosome
     * @param id of gene to remove
     */
    public void remove(int id)
    {
    	chromosome.remove(id);
    }
    
    /**
     * remove gene from chromosome
     * @param g
     */
    public void remove(Gene g)
    {
    	chromosome.remove(g);
    }
    
    /**
     * -1 is set on an invalid gene id
     */
    public boolean validateChromosome()
    {
      for(Gene g:chromosome)
    	  if (g.getId()==-1)
    		  return false;
      
      return true;
    }
    
    /**
     * 
     */
    public  void clear()
    {
    	chromosome = new ArrayList<>();
    }
    
}
