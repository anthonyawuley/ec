/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package individuals;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

import util.DeepClone;

/**
 *
 * @author anthony
 */
public class Chromosome implements Cloneable, Serializable {
    
  
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList<Integer> chromosome = new ArrayList<>();
    
    private int chromeSize;


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
     * @param chrome
     * @return
     */
    public boolean isFeasible(ArrayList<Integer> chrome)
    {
      return this.chromosome.size()==chrome.size();
    }
    
    /**
     * @param length
     * @return 
     */
    public void createChromosome(Chromosome ch,Gene g,Properties prop)
    {
       //this.chromosome.clear();
       
       g.generateChromosome(ch,prop);
       
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
    public void setChromosome(ArrayList<Integer> chrome)
    { 
        this.chromosome = chrome;
    }
    
    /**
     * 
     * @return 
     */
    public ArrayList<Integer> getChromosome()
    {
        return this.chromosome;
    }
    
    
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
    public ArrayList<Integer> getChromosomeConstraint()
    {
       return this.chromosome;
    }
    
    
    /**
     * Add an entire gene to the chromosome
     * @param genes Gene to add
     */
    public void addAll(ArrayList<Integer> genes)
    {
    	Iterator<Integer> indIt = genes.iterator();
        while (indIt.hasNext()) 
        { 
        	this.chromosome.add(indIt.next());
        }
    }

    /**
     * Add an gene to the population
     * @param g Gene to add
     */
    public void add(int g)
    {
    	this.chromosome.add(g);
    }

    /**
     * Remove gene from chromosome
     * @param id of gene to remove
     */
    public void remove(int id)
    {
    	this.chromosome.remove(id);
    }
    
    /**
     * 
     */
    public void validateChromosome()
    {
    /*
     * first = portList.indexOf(someIntValue);
       int last  = portList.lastIndexOf(someIntValue);
       if (first != -1 && first != last) {
     */
    }
    
}
