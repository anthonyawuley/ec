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

import ec.individuals.populations.Population;
import ec.island.connect.Client;
import ec.island.connect.Server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

/**
 * 
 * @author Anthony Awuley
 *
 */
public final class IslandModel {
	

     /** */
     public static final String NAMING = "demme";
	 /** */
     public static final String SERVER_PORT = NAMING+".server-port";
     /** */
     public static final String SERVER_ADDRESS = NAMING+".server-address";
     /** */
     public static final String SERVER_NAME = NAMING+".server-name";
     /** */
     public static final String I_AM_A_SERVER = NAMING+".i-am-server";
     /** */
     public static final String SERVER_CONNECTING_CLIENTS = NAMING+".num-islands";
     
     
     /** Brief description of client */
     public static final String CLIENT_NAME = NAMING+".client.name";
     /** */
     public static final String CLIENT_PORT = NAMING+".client.port";
     /** */
     public static final String CLIENT_ADDRESS = NAMING+".client.address";
     /** */
     public static final String CLIENT_NUMBER_OF_CONNECTIONS = NAMING+".client.connections";
     /** */
     public static final String CLIENT_NUMBER_OF_MIGRATIONS = NAMING+".client.migrations";
     /** */
     public static final String CLIENT_MAILBOX_CAPACITY = NAMING+".client.mailbox-capacity";
     /** */
     public static final String CLIENT_UPDATE_FREQUENCY = NAMING+".client.update-frequency";
     /** */
     public static final String CLIENT_STARTING_GENERATION = NAMING+".client.start-generation";
     
     
     
     public ArrayList<Client> clients = new ArrayList<>();
     
     private ArrayList<Server> servers;
     
     private Boolean isServer = Boolean.FALSE;
     private Boolean isClient = Boolean.FALSE;
     private Properties properties;
     private Client client;
     
     
     
     
     public IslandModel(Properties p) 
 	 {
 		// TODO Auto-generated constructor stub
 		setProperties(p);
 		/*
 		 * demme.i-am-server     = true
           demme.server-addr     = 127.0.0.1
           demme.server-port     = 6000
           demme.num-islands     = 4
           demme.island.0.id     = 6001
           demme.island.1.id     = 6002
           demme.island.2.id     = 6003
           demme.island.3.id     = 6004
 		 */
 		boolean i_am_a_server = Boolean.FALSE;
 		i_am_a_server = Boolean.parseBoolean(properties.getProperty(I_AM_A_SERVER));
 		
 		String host = properties.getProperty(SERVER_ADDRESS);
 		
 		int sPort = Integer.parseInt(properties.getProperty(SERVER_PORT));
 		
 		int cPort = Integer.parseInt(properties.getProperty(CLIENT_PORT));
			
 		 //ArrayList<ArrayList<String>> clientServerConfig = new ArrayList<>();
 		 
    	 //GreetingServer s = new GreetingServer(host,0,sPort);
    	 //s.start();
    	 //System.out.println(); System.exit(0);
    	 
 		//start server processes
 		if(i_am_a_server)
 		{	
 			int numberOfConnectingClients = Integer.parseInt(properties.getProperty(SERVER_CONNECTING_CLIENTS));
 	 		
 			/** Start main server */
 			System.out.println("Starting main server on port "+ sPort +" ... \n"
 					+ "Binding all " + numberOfConnectingClients +" client ports on server: " + host +"");
 			Server main = new Server(host,0,sPort);
 			main.setName(properties.getProperty(SERVER_NAME));
 			main.startServer();
 			
 			/** Start ports of clients on server*/
 			this.setServers(
 					startServerServices(host,numberOfConnectingClients)
 					);
 			
 			this.setIsServer(true);
 		}
 		else //set i-am-server process to false on clients
 		{
 		  setClient(startClientServices(host,cPort, 
				properties.getProperty(CLIENT_NAME), 
				Integer.parseInt(properties.getProperty(CLIENT_NUMBER_OF_CONNECTIONS)), 
						Integer.parseInt(properties.getProperty(CLIENT_NUMBER_OF_MIGRATIONS)), 
								Integer.parseInt(properties.getProperty(CLIENT_MAILBOX_CAPACITY)), 
										Integer.parseInt(properties.getProperty(CLIENT_UPDATE_FREQUENCY)),
												Integer.parseInt(properties.getProperty(CLIENT_STARTING_GENERATION))));
 		  this.setIsClient(true);
 		}
 		
 		
 	 } 
     
     
	public ArrayList<Server> startServerServices(String host,int numberOfConnectingClients) 
	{
		// TODO Auto-generated constructor stub
		ArrayList<Server> s = new ArrayList<>();
    	
   	    //System.out.println(); System.exit(0);
		 
	    for(int i=0;i<numberOfConnectingClients;i++)
	    {
	       s.add(new Server(host,0,Integer.parseInt(properties.getProperty(NAMING+".island."+i+".id"))));
		   s.get(i).setName(properties.getProperty(NAMING+".island."+i+".name"));
		   s.get(i).startServer();
		} 
	    //System.out.println("All binding of clients ports to server successfull...\n");
	    
		return s;
	}

	
	@SuppressWarnings("static-access")
	public void closeServers(ArrayList<Server> servers)
	{
		for(Server s: servers)
		{
		  try 
		  {
			Server.getSocket().close();
		   } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		   }
		}
	}
	
	
	public Client startClientServices(String host, int port, String name, int numCon, 
			int numMig, int mailCapa, int updFreq, int startGen ) 
	{
		//TODO Auto-generated constructor stub
    	//Server.startServer(host,0,port);
    	//Client.startClient(host,port);
		Client c = new Client(host,port);
		c.setClientName(name);
		c.setMailBoxCapacity(mailCapa);
		c.setNumberOfConnections(numCon);
		 
		ArrayList<Integer> connectedDemmes = new ArrayList<>();
		
		for(int i=0;i<numCon;i++)
		{   //demme.0.connect.port = <port number>
			connectedDemmes.add(Integer.parseInt(properties.getProperty(NAMING+".client."+i+".connect-port")));
		}
		c.setConnectingDemmes(connectedDemmes);
		c.setNumberOfMigrants(numMig);
		c.setStartGeneration(startGen);
		c.setUpdateFrequecy(updFreq);
		
		//start client
		c.startClient();
		
		//myClient = client;
		
		return c;
		
	}
	
   
   /**
    * prepare packets to be transmitted by a client
    * @param client
    * @param population
    * @return
    */
   public void txClientPackets(Client client,Population population)
   {
	   
	   ArrayList<Integer> conPorts = client.getConnectingDemmes();
	  
	   for(int port : conPorts)
	   {
		    Packet packet = new Packet();
		    packet.setDestinationPort(port);
		    packet.setPopulation(population);
		    packet.setOrginationPort(client.getPort());
		    packet.setSentFlag(false);
		    packet.setRecieveFlag(false);
		    //System.out.println("I tried to send a packet!!");
		    
		    client.setTxPacket(packet);
		    client.priorityKey = 1; //activate transmit process
		    client.startClient();
	   } 
	
   }
	
	 
	
	/**
	 * scan servers for received packet and transmit to destination port
	 * @param generation
	 * @param servers
	 */
	public void serverMigratePacketToClient(ArrayList<Server> s,int generation)
	{ 
		 
		for(Server rx : s) //get the destination server
		{ 
		   rx.priorityKey = 1; //try receiving
		   
		   if(rx.getRxPacket()!=null)
		   { 
			 if(!rx.getRxPacket().getRecieveFlag())
			 {
				//System.out.println("Sending a population of "+ packet.getPopulation().size() + " to " + packet.getDestinationPort());
				//s.setReceivedPopulation(pop);
			       
			   for(Server tx : s)
			   {  
			      if((tx.getPort() == rx.getRxPacket().getDestinationPort()))
			      {  
				    tx.setTxPacket(rx.getRxPacket()); //transmit received packet
				    //rx.getRxPacket().setRecieveFlag(true);
				    tx.priorityKey = 2;
				    break;
			      }  
			    }  
			   rx.getRxPacket().setRecieveFlag(true);
			   break;
			 }
			 
		   }
		}  
	} 
	
	 
	
	public Population rxClientPopulation(Client client)
	{
		client.priorityKey = 2; //Receive
		client.startClient();
		System.out.println("Recieving  population of "+ 
			        client.getRxPacket().getPopulation().size() + " from " + client.getClientName());
		return client.getRxPacket().getPopulation();
		 
	}

	/**
	 * @return the parameters
	 */
	public Properties getProperties() 
	{
		return properties;
	}


	/**
	 * @param parameters the parameters to set
	 */
	public void setProperties(Properties parameters) 
	{
		this.properties = parameters;
	}


	/**
	 * @return the isServer
	 */
	public Boolean getIsServer() {
		return isServer;
	}


	/**
	 * @param isServer the isServer to set
	 */
	public void setIsServer(Boolean isServer) {
		this.isServer = isServer;
	}


	/**
	 * @return the isClient
	 */
	public Boolean getIsClient() {
		return isClient;
	}


	/**
	 * @param isClient the isClient to set
	 */
	public void setIsClient(Boolean isClient) {
		this.isClient = isClient;
	}


	/**
	 * @return the client
	 */
	public Client getClient() {
		return this.client;
	}


	/**
	 * @param myClient the myClient to set
	 */
	public void setClient(Client myClient) {
		this.client = myClient;
	}
	
	public ArrayList<Server> getServers()
	{
		return this.servers;
	}
	
	public void setServers(ArrayList<Server> s)
	{
		this.servers = s;
	}
	

}
