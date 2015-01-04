/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package operator.mutation;

import individuals.Chromosome;
import individuals.Individual;
import individuals.populations.Population;

import java.util.ArrayList;

import operator.MutationModule;

/**
 *
 * @author anthony
 */
public class Flip extends MutationModule{

	public Flip(String type) {
		super(type);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<Individual> performMutationOperation(Population p,
			Chromosome c1, int parentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Individual> performMutationOperation(Population p,
			Chromosome c1, int parentId, ArrayList<Double> ages,String replacementType) {
		// TODO Auto-generated method stub
		return null;
	}
 
}
