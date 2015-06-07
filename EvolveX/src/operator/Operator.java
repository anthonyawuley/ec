/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package operator;

import individuals.Chromosome;
import individuals.Gene;
import individuals.Individual;
import individuals.populations.Population;
import util.random.GenerateMask;
import util.random.RandomGenerator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author anthony
 */
public abstract class Operator {

	private static int[] twoRandomPoints = new int[2];
	public ArrayList<Integer>   indexes = new ArrayList<>();
	private Population offsprings;

	/**
	 * Get operation that operator performs
	 * @return operation
	 */
	public abstract String getOperation();
	/**
	 * @param arrayList
	 * @return true if there is duplicate
	 */
	public boolean chromosomeHasDuplicateGenes(ArrayList<Gene> arrayList)
	{ 
		Set<Gene> set = new HashSet<Gene>();
		for (Gene yourInt : arrayList)
			if (!set.add(yourInt))
				return true;

		return false ;
	}

	/**
	 * RANdomly select two parents to be used in crossover
	 * @param popSize
	 * @return 
	 */
	public static int[] selectTwoPointsRandomly(int chromSize)
	{
		twoRandomPoints[0] = RandomGenerator.getMultiThreadedRandNumber(0,chromSize);
		do
		{
			twoRandomPoints[1] = RandomGenerator.getMultiThreadedRandNumber(0,chromSize);
		}while(twoRandomPoints[0]==twoRandomPoints[1]);

		return twoRandomPoints;
	}

	/**
	 * Return indexes with 1/0 based on itemName specified
	 * @param mask
	 * @param itemName
	 * @return 
	 */
	public ArrayList<ArrayList<Integer>> getIndex(String mask, String itemName)
	{
		ArrayList<ArrayList<Integer>> indexes = new ArrayList<>();
		ArrayList<Integer> one = new ArrayList<>();
		ArrayList<Integer> zero = new ArrayList<>();
		String [] binaryMask =  mask.split("");

		for (int i = 1; i < binaryMask.length; i++) //skip index 0; its empty
			if (itemName.equals(binaryMask[i]))
				one.add(i-1); 
			else
				zero.add(i-1); 
		
		indexes.add(0,one); 
		indexes.add(1,zero);

		return indexes;
	}



	/**
	 * Example: Cycle crossover
	 * Step 1: identify cycle
	 * p1: 1 2 3 4 5 6 7 8 9
	 * p2: 9 3 7 8 2 6 5 1 4
	 * c1: 1 * * 4 * * * 8 9
	 * 
	 * @param p1
	 * @param p2
	 * @param id
	 * @return index 0,8,3,7 for the example above for c1
	 * 
	 * implementation was done using recursive definition of function
	 */
	public ArrayList<Integer> getCycleMask(Chromosome p1, Chromosome p2,int id)
	{
		this.indexes.add(id);
		id = GenerateMask.returnIndex(p1.getGenes(),p2.getGenes().get(id));
		if(!GenerateMask.isExistIndex(this.indexes,id+"") && id!=-1)
			getCycleMask(p1,p2,id); //recursive call
		
		return indexes;
	}


	/**
	 * 
	 * @param individuals
	 */
	public void setOffsprings(ArrayList<Individual> individuals) 
	{
		//this.offsprings.clear();
		this.offsprings = new Population();
		this.offsprings.clear();
		for(Individual i: individuals)
			this.offsprings.add(i);
	}

	/**
	 * 
	 * @return
	 */
	public Population getOffsprings() 
	{
		return this.offsprings;
	}



}
