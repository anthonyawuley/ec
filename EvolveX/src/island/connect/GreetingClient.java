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
package island.connect;

import individuals.populations.Population;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

public class GreetingClient
{
	
    private static Socket socket;
    private String host;
    private int port;
    private int mailBoxCapacity;
    private int numberOfMigrants;
    private int numberOfConnections;
    private int updateFrequecy;
    private int startGeneration;
    private ArrayList<Integer> connectingDemmes;
    private String clientName;
    private Population sentPopulation;
    private Population receivedPopulation;
    private Client connectedClient;
	
    
    public GreetingClient(String h, int p)
    {
    	this.host = h;
    	this.port = p;
    }
	
	
	
   public void startClient()
   {
	   try
       {
           
           InetAddress address = InetAddress.getByName(this.host);
           socket = new Socket(address, this.port);

           /**
            * SENDing population to another client via SERVER
            */
           //System.out.println("Sending population of "+this.getReceivedPopulation().size() +" to " + this.getConnectedClient().clientName );
           //ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
           /** Get population to be dispatched */
           //Population txPop = new Population();
           //oos.writeObject(txPop);
           
           ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
           oos.writeObject(new Population());
           System.out.println("Message sent to the server x: "+ 2);
           
           
           /**
            * RECEIVing population from another client via SERVER
            */
           //System.out.println("Receiving population of "+this.getReceivedPopulation().size() +" from " + this.getConnectedClient().clientName );
           //ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
           //Population rxPop = (Population) ois.readObject();
           
           /** dispatcher. send pop to mailbox */
           //System.out.println("Message received from the server : " +rxPop.toString());
           
           ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
           Population pi = (Population) ois.readObject();
           System.out.println("Message received from the server x: " +pi.size());
           
           
       }
       catch (Exception exception) 
       {
           exception.printStackTrace();
       }
       finally
       {
           //Closing the socket
           try
           {
               socket.close();
           }
           catch(Exception e)
           {
               e.printStackTrace();
           }
       }
   }
   
   
   
   
   
   /**
	 * @return the host
	 */
	public String getHost() 
	{
		return host;
	}

	/**
	 * @param host the host to set
	 */
	public void setHost(String host) 
	{
		this.host = host;
	}

	/**
	 * @return the port
	 */
	public int getPort() 
	{
		return port;
	}

	/**
	 * @param port the port to set
	 */
	public void setPort(int port) 
	{
		this.port = port;
	}

	/**
	 * @return the clientName
	 */
	public String getClientName() 
	{
		return clientName;
	}

	/**
	 * @param clientName the clientName to set
	 */
	public void setClientName(String clientName) 
	{
		this.clientName = clientName;
	}


	/**
	 * @return the sentPopulation
	 */
	public Population getSentPopulation() 
	{
		return sentPopulation;
	}


	/**
	 * @param sentPopulation the sentPopulation to set
	 */
	public void setSentPopulation(Population sentPopulation) 
	{
		this.sentPopulation = sentPopulation;
	}


	/**
	 * @return the receivedPopulation
	 */
	public Population getReceivedPopulation() 
	{
		return receivedPopulation;
	}


	/**
	 * @param receivedPopulation the receivedPopulation to set
	 */
	public void setReceivedPopulation(Population receivedPopulation) 
	{
		this.receivedPopulation = receivedPopulation;
	}


	/**
	 * @return the mailBoxCapacity
	 */
	public int getMailBoxCapacity() 
	{
		return mailBoxCapacity;
	}


	/**
	 * @param mailBoxCapacity the mailBoxCapacity to set
	 */
	public void setMailBoxCapacity(int mailBoxCapacity) 
	{
		this.mailBoxCapacity = mailBoxCapacity;
	}


	/**
	 * @return the numberOfMigrants
	 */
	public int getNumberOfMigrants() 
	{
		return numberOfMigrants;
	}


	/**
	 * @param numberOfMigrants the numberOfMigrants to set
	 */
	public void setNumberOfMigrants(int numberOfMigrants) 
	{
		this.numberOfMigrants = numberOfMigrants;
	}


	/**
	 * @return the numberOfConnections
	 */
	public int getNumberOfConnections() 
	{
		return numberOfConnections;
	}


	/**
	 * @param numberOfConnections the numberOfConnections to set
	 */
	public void setNumberOfConnections(int numberOfConnections) 
	{
		this.numberOfConnections = numberOfConnections;
	}


	/**
	 * @return the updateFrequecy
	 */
	public int getUpdateFrequecy() 
	{
		return updateFrequecy;
	}


	/**
	 * @param updateFrequecy the updateFrequecy to set
	 */
	public void setUpdateFrequecy(int updateFrequecy) 
	{
		this.updateFrequecy = updateFrequecy;
	}


	/**
	 * @return the startGeneration
	 */
	public int getStartGeneration() 
	{
		return startGeneration;
	}


	/**
	 * @param startGeneration the startGeneration to set
	 */
	public void setStartGeneration(int startGeneration) 
	{
		this.startGeneration = startGeneration;
	}


	/**
	 * @return the connectingDemmes
	 */
	public ArrayList<Integer> getConnectingDemmes() 
	{
		return connectingDemmes;
	}


	/**
	 * @param connectingDemmes the connectingDemmes to set
	 */
	public void setConnectingDemmes(ArrayList<Integer> connectingDemmes) 
	{
		this.connectingDemmes = connectingDemmes;
	}


	/**
	 * @return the connectedClient
	 */
	public Client getConnectedClient() 
	{
		return connectedClient;
	}


	/**
	 * @param connectedClient the connectedClient to set
	 */
	public void setConnectedClient(Client connectedClient) 
	{
		this.connectedClient = connectedClient;
	}
   
   
   
}
