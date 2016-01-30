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
package ec.island;

import java.io.Serializable;

import ec.individuals.populations.Population;

public class Packet implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Population population;
	private int destinationPort;
	private int orginationPort;
	private boolean sendFlag = Boolean.FALSE;
	private boolean recieveFlag = Boolean.FALSE;
	
	public Packet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the population
	 */
	public Population getPopulation() 
	{
		return population;
	}

	/**
	 * @param population the population to set
	 */
	public void setPopulation(Population population) 
	{
		this.population = population;
	}

	/**
	 * @return the destinationPort
	 */
	public int getDestinationPort() 
	{
		return destinationPort;
	}

	/**
	 * @param destinationPort the destinationPort to set
	 */
	public void setDestinationPort(int destinationPort) 
	{
		this.destinationPort = destinationPort;
	}

	/**
	 * @return the orginationPort
	 */
	public int getOrginationPort() 
	{
		return orginationPort;
	}

	/**
	 * @param orginationPort the orginationPort to set
	 */
	public void setOrginationPort(int orginationPort) 
	{
		this.orginationPort = orginationPort;
	}

	/**
	 * @return the sendFlag
	 */
	public boolean getSentFlag() 
	{
		return sendFlag;
	}

	/**
	 * @param sendFlag the sendFlag to set
	 */
	public void setSentFlag(boolean sendFlag) 
	{
		this.sendFlag = sendFlag;
	}

	/**
	 * @return the recieveFlag
	 */
	public boolean getRecieveFlag() 
	{
		return recieveFlag;
	}

	/**
	 * @param recieveFlag the recieveFlag to set
	 */
	public void setRecieveFlag(boolean recieveFlag) 
	{
		this.recieveFlag = recieveFlag;
	}

}
