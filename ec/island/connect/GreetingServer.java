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
package ec.island.connect;

import ec.individuals.populations.Population;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class GreetingServer extends Thread
{
   private ServerSocket serverSocket;
   private static Socket socket;
   @SuppressWarnings("unused")
   private static Socket transitSocket;
   
   private String host;
   private int backlog;
   private int port;
   private String serverName;
   private Population sentPopulation;
   private Population receivedPopulation;
   private Client connectedClient;
   private Server transitServer;
   
   
   public GreetingServer(String h,int b, int p)
   { 
      this.host = h;
      this.backlog = b;
      this.port = p;
   }

   @Override
public void run()
   {
	   try
       {

           //int port = 25000;
           //ServerSocket serverSocket = new ServerSocket(port);
           System.out.println("Server Started and listening to the port "+ this.port);

           //Server is running always. This is done using this while(true) loop
           while(true) 
           {
               /**
                * READing population from the CLIENT
                */
           	   socket = serverSocket.accept();
               //System.out.println("Server received population from "+ this.getConnectedClient().getClientName() );
               ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
               Population pi = (Population) ois.readObject();
               pi.setSize(20);
               System.out.println("Message received from client is "+pi.size());
               //this.setReceivedPopulation((Population) ois.readObject());
              
               /**
                * SENDing population to CLIENT
                * --server is serving as transit
                */
               //socket = this.getTransitServer().serverSocket.accept();
               //System.out.println("Server sending population from "+ this.getConnectedClient().getClientName() +
               //		" to "+ this.getTransitServer().getConnectedClient().getClientName() );
               
               
               //oos.writeObject(this.getReceivedPopulation());
               //System.out.println("Message sent to the client is "+returnMessage);
               
               ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
               oos.writeObject(pi);
              
           }
       }
       catch (Exception e) 
       {
           e.printStackTrace();
       }
       finally
       {
           try
           {
               socket.close();
           }
           catch(Exception e){}
       }
   }
   
   public void startServer()
   {
		
       try 
       {
           InetAddress address = InetAddress.getByName(this.host);
           serverSocket = new ServerSocket(this.port,this.backlog,address);
           //System.out.println(this.port+" "+ this.backlog + " "+ address); System.exit(0);
           //serverSocket = new ServerSocket(port);
           serverSocket.setSoTimeout(10000); //set in param file
         }catch(IOException e)
         {
           e.printStackTrace();
         }
    	  
       
        Thread t = this;
        //if(!t.isAlive())
        //{
          t.start();
        //}
        
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
	 * @return the backlog
	 */
	public int getBacklog() 
	{
		return backlog;
	}



	/**
	 * @param backlog the backlog to set
	 */
	public void setBacklog(int backlog) 
	{
		this.backlog = backlog;
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
	 * @return the serverName
	 */
	public String getServerName() 
	{
		return serverName;
	}


	/**
	 * @param serverName the serverName to set
	 */
	public void setServerName(String serverName) 
	{
		this.serverName = serverName;
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


	/**
	 * @return the transitServer
	 */
	public Server getTransitServer() {
		return transitServer;
	}


	/**
	 * @param transitServer the transitServer to set
	 */
	public void setTransitServer(Server transitServer) {
		this.transitServer = transitServer;
	}

  
   
   
}
