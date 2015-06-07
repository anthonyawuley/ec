/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package operator.crossover;

import individuals.Chromosome;
import individuals.Gene;
import individuals.Individual;
import individuals.fitnesspackage.BasicFitness;
import individuals.populations.Population;
import operator.CrossoverModule;
import util.random.GenerateMask;

import java.util.ArrayList;

/**
 *
 * @author anthony
 */
public class UniformOrderCrossover extends CrossoverModule {
    
    private String crossoverMask;
    private Population  offsprings;
    
    public UniformOrderCrossover()
    {
      super("uox");
    }
    
    /**
     * 
     * @param mask 
     */
    public void setCrossoverMask(String mask)
    {
         this.crossoverMask = mask;
    }
    
    /**
     * 
     * @return 
     */
    public String getCrossOverMask()
    {
         return this.crossoverMask;
    }
    
    /**
     * 
     * @param p : population
     * @param c1 : first chromosome 
     * @param c2 : second chromosome 
     * @param parentsId : array id's of parents with above chromosome
     * @param numberOfChildrenToAdd : determine number of offspring(2 or 1) to add to new population 
     * @return 
     */
    @Override
    public ArrayList<Individual> performCrossoverOperation(
    		Population p,
    		Chromosome c1, 
    		Chromosome c2,
    		ArrayList<Integer> tournamentIndividuals, 
    		int numberOfChildrenToAdd)
    {
      Individual id1 = new Individual(); 
      Individual id2 = new Individual();
      ArrayList<Individual> children = new ArrayList<>();
      ArrayList<ArrayList<Integer>> mask = new ArrayList<>();
      
      crossoverMask = GenerateMask.getMask(p.get(0).getChromosome().getGenes().size());
      mask = this.getIndex(crossoverMask,"1"); //return index position of 1's in mask
      
      /*
       * complete replacement of other bit positions without mask flag of "1"
       * set -1 for values that must be replaced
       * individuals that have -1 after operation are invalid
       */
      
       for(int j=0; j<c1.getGenes().size();j++) //take size of any of the chromosomes
       {  
          if(!GenerateMask.isExistIndex(mask.get(0),j+""))
          {
             c1.getGenes().set(j,new Gene(-1)); 
             c2.getGenes().set(j,new Gene(-1));
          }
       }
        
       for(int i=0;i<mask.get(1).size();i++){ //loop for index positions to be replaced by alternativive parent
          for(int j=0; j<c1.getGenes().size();j++) //take size of any of the chromosomes
          {  
            if((this.returnAvailableIndex(c1,p.get(tournamentIndividuals.get(1)).getChromosome().getGenes().get(j)) == -1) /*&& (c1.getChromosome().get(mask.get(1).get(i))==-1)*/ )
            {  
               c1.getGenes().set(mask.get(1).get(i), p.get(tournamentIndividuals.get(1)).getChromosome().getGenes().get(j)); //swap remaining chromosomes
               //c2.getChromosome().set(j, p.get(parentsId[0]).getChromosome().get(j));
               break; //to avoid exhaustive but not-required search
            }
          }
          for(int j=0; j<c2.getGenes().size();j++) //take size of any of the chromosomes
          {  
            if((this.returnAvailableIndex(c2,p.get(tournamentIndividuals.get(0)).getChromosome().getGenes().get(j)) == -1) /*&& (c2.getChromosome().get(mask.get(1).get(i))==-1)*/ )
            {  
               c2.getGenes().set(mask.get(1).get(i), p.get(tournamentIndividuals.get(0)).getChromosome().getGenes().get(j)); //swap remaining chromosomes
               //System.out.println("\nRR "+c2.getChromosome().get(mask.get(1).get(i)));
               break;
            }
          }
       }
        
        
        /*
         * complete replacement of other bit positions without mask flag of "1"
         * int j=0; j<mask.get(1).size()-1; j++
         
       
          System.out.println("\nP1:" + tournamentIndividuals.get(0) + " P2:"+tournamentIndividuals.get(1)+" MASK:"+crossoverMask  );
          //System.out.println(tournamentIndividuals);
          for(int j=0; j<p.get(tournamentIndividuals.get(1)).getChromosome().size(); j++)
          { 
              System.out.print(c1.getChromosome().get(j)+" ");
              //System.out.println("its a gradual process!" + crossoverMask + ":"+mask.size() );
              //System.out.print(p.get(2).getChromosome().get(2)+" ");
           
          }System.out.println();
          for(int j=0; j<p.get(tournamentIndividuals.get(1)).getChromosome().size(); j++)
          {
              System.out.print(c2.getChromosome().get(j)+" ");
              //System.out.println("its a gradual process!" + crossoverMask + ":"+mask.size() );
              //System.out.print(p.get(2).getChromosome().get(2)+" ");
          } 
         */
   
        //set individual properties for chldren and add to new population
     
        id1.setChromosome(c1);
        id1.setFitness(new BasicFitness()); //set fitness object
        
        id2.setChromosome(c2);
        id2.setFitness(new BasicFitness()); //set fitness object
        //add children if they have not duplicates 
        switch(numberOfChildrenToAdd)
        {
           case 1: //add one offspring
        	   if(!chromosomeHasDuplicateGenes(c1.getGenes()))
        		   children.add(id1);
               break;
           default: //add two offsprings
        	   if(!chromosomeHasDuplicateGenes(c1.getGenes()) && !chromosomeHasDuplicateGenes(c2.getGenes()))
        	   {
                 children.add(id1);
                 children.add(id2);
        	   }
               break;
        }
       
        this.setChildrenAdded(children.size()); //set number of children added
      //System.out.println("This is my crossover mask: "+ crossoverMask + " " + comsk.length +" 0,1: "+ twoParents[0] +"-"+ twoParents[1]);
        setOffsprings(children);
      return children;
      
    }

    
    
    /**
     * over loaded : alps
     */
    @Override
    public ArrayList<Individual> performCrossoverOperation(
    		Population p,
    		Chromosome c1, 
    		Chromosome c2,
    		ArrayList<Integer> tournamentIndividuals, 
    		int numberOfChildrenToAdd,
    		ArrayList<Double> ages,
    		String replacementType)
    {
      Individual id1 = new Individual(); 
      Individual id2 = new Individual();
      ArrayList<Individual> children     = new ArrayList<>();
      ArrayList<ArrayList<Integer>> mask = new ArrayList<>();
      
      crossoverMask = GenerateMask.getMask(p.get(0).getChromosome().getGenes().size());
      mask = this.getIndex(crossoverMask,"1"); //return index position of 1's in mask
      
      /*
       * complete replacement of other bit positions without mask flag of "1"
       * set -1 for values that must be replaced
       * individuals that have -1 after operation are invalid
       */
      
       for(int j=0; j<c1.getGenes().size();j++) //take size of any of the chromosomes
       {  
          if(!GenerateMask.isExistIndex(mask.get(0),j+""))
          {
             c1.getGenes().set(j,new Gene(-1)); 
             c2.getGenes().set(j,new Gene(-1));
          }
       }
        
       for(int i=0;i<mask.get(1).size();i++){ //loop for index positions to be replaced by alternativive parent
          for(int j=0; j<c1.getGenes().size();j++) //take size of any of the chromosomes
          {  
            if((this.returnAvailableIndex(c1,p.get(tournamentIndividuals.get(1)).getChromosome().getGenes().get(j)) == -1) /*&& (c1.getChromosome().get(mask.get(1).get(i))==-1)*/ )
            {  
               c1.getGenes().set(mask.get(1).get(i), p.get(tournamentIndividuals.get(1)).getChromosome().getGenes().get(j)); //swap remaining chromosomes
               //c2.getChromosome().set(j, p.get(parentsId[0]).getChromosome().get(j));
               break; //to avoid exhaustive but not-required search
            }
          }
          for(int j=0; j<c2.getGenes().size();j++) //take size of any of the chromosomes
          {  
            if((this.returnAvailableIndex(c2,p.get(tournamentIndividuals.get(0)).getChromosome().getGenes().get(j)) == -1) /*&& (c2.getChromosome().get(mask.get(1).get(i))==-1)*/ )
            {  
               c2.getGenes().set(mask.get(1).get(i), p.get(tournamentIndividuals.get(0)).getChromosome().getGenes().get(j)); //swap remaining chromosomes
               //System.out.println("\nRR "+c2.getChromosome().get(mask.get(1).get(i)));
               break;
            }
          }
       }
        
        id1.setChromosome(c1);
        id1.setFitness(new BasicFitness()); //set fitness object
        id2.setChromosome(c2);
        id2.setFitness(new BasicFitness()); //set fitness object
        
        if(replacementType.equals("SteadyState"))
        {   //asssign parent with lowest evluation value
        	/**
        	 * Randomly generated individuals store the number of evaluations that have been 
        	 * performed so far, and individuals created through mutation and recombination store
        	 *  the smallest (which is equivalent to oldest) value of their parents
        	 */
            double eval = ages.get(0)<ages.get(1)?ages.get(0):ages.get(1);
            id1.setBirthEvaluations(eval);
            id2.setBirthEvaluations(eval);
        }
        else if(replacementType.equals("Generational"))
        {
        	double age = ages.get(0)>ages.get(1)?ages.get(0):ages.get(1);
        	id1.setAge(age); //add age
            id2.setAge(age); //add age
        }
       
        
        //add children if they have not duplicates 
        switch(numberOfChildrenToAdd)
        {
           case 1: //add one offspring
        	   if(!chromosomeHasDuplicateGenes(c1.getGenes()))
        		   children.add(id1);
               break;
           default: //add two offsprings
        	   if(!chromosomeHasDuplicateGenes(c1.getGenes()) && !chromosomeHasDuplicateGenes(c2.getGenes()))
        	   {
                 children.add(id1);
                 children.add(id2);
        	   }
               break;
        }
       
        this.setChildrenAdded(children.size()); //set number of children added
        //convert offspring to population.
        setOffsprings(children);
        
      return children;
      
    }


    
}
