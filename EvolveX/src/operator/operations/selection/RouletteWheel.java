/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package operator.operations.selection;

import java.util.ArrayList;

import algorithms.alps.system.ALPSLayers;
import operator.operations.SelectionOperation;

/**
 *
 * @author anthony
 */
public class RouletteWheel implements SelectionOperation{
    

    private final String selectionOperation = "Roulette Wheel selection";
    

    
 	public String toString() 
 	{
 		return this.selectionOperation;
 	}
	
	public ArrayList<Integer> getTournamentSelection()
	{
		return new ArrayList<Integer>();
	}

	
	@Override
	public ArrayList<Integer> performTournamentSelection(int populationSize,
			int tournamentSize) 
	{
		return null;
	}

	@Override
	public ArrayList<Integer> performTournamentSelection(ALPSLayers alpsLayers,
			int populationSize, int tournamentSize) 
	{
		return null;
	}

	@Override
	public ArrayList<Integer> performTournamentSelectionWithLimits(
			int tournamentSize, int min, int max) {
		// TODO Auto-generated method stub
		return null;
	}
}
