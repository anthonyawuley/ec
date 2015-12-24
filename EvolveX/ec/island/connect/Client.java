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

//File Name GreetingClient.java

import island.Packet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
 
public class Client 
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
    private Packet txPacket;
    private Packet rxPacket;
    private Client connectedClient;
    public int priorityKey = 0; //tx or recieve
    
 
    public Client(String h, int p)
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
             
            System.out.println("Client Started and listening on "+this.getClientName() + ":"+ this.port );
             
            switch(priorityKey)
            {
            case 1: //client-server communication
            	
            	/**
                 * SENDing population to another client to SERVER
                 */
                System.out.println("Client sending packet population of "+this.getTxPacket().getPopulation().size() +
                		      " to "+ this.getTxPacket().getDestinationPort());
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                /** Get population to be dispatched */
                oos.writeObject(this.getTxPacket());
                
            	break;
            case 2: //client-server-client communication
            	
            	/**
                 * RECEIVing population from another client via SERVER
                 */
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Packet rxPop = (Packet) ois.readObject();
                this.setRxPacket(rxPop); //set new population
                /** dispatcher  pop to mailbox */
                System.out.println("Packet received from   " + this.getRxPacket().getOrginationPort());
            	
            	break;
            default:
            	System.out.println("Client operation not set...");
            	break;
            
            }
           //socket.close();
            
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
                //socket.close();
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
	 * @return the txPacket
	 */
	public Packet getTxPacket() 
	{
		return txPacket;
	}


	/**
	 * @param txPacket the txPacket to set
	 */
	public void setTxPacket(Packet txPacket) 
	{
		this.txPacket = txPacket;
	}


	/**
	 * @return the rxPacket
	 */
	public Packet getRxPacket() 
	{
		return rxPacket;
	}


	/**
	 * @param rxPacket the rxPacket to set
	 */
	public void setRxPacket(Packet rxPacket) 
	{
		this.rxPacket = rxPacket;
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