package island.connect;

//File Name GreetingServer.java
import individuals.populations.Population;
import island.Packet;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
 
public class Server extends Thread
{
 
    private static Socket socket;
    @SuppressWarnings("unused")
	private static Socket transitSocket;
    private ServerSocket serverSocket;
    
    private String host;
    private int backlog;
    private int port;
    private int destinationPort;
    private Packet txPacket;
    private Packet rxPacket;
    private Population rxPopulation;
    private Population txPopulation;
    private Client connectedClient;
    private Server transitServer;
    public int priorityKey = 0; //send or recieve
    
    private Thread t;
 
    /**
     * 
     * @param host  InetAddress parameter specifies the local IP address to bind to The 
     *              InetAddress is used for servers that may have multiple IP addresses, allowing the 
     *              server to specify which of its IP addresses to accept client requests on
     * @param backlog backlog parameter specifies how many incoming clients to store in a wait queue
     * @param port
     * @throws IOException
     */
    public Server(String h,int b, int p)
    { 
       this.host = h;
       this.backlog = b;
       this.port = p;
    }
    
   
    
    public void run() 
    {
    	
    	//int port = 25000;
        //ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server Started and listening on "+this.getName() + ":"+ this.port );

    	 while(true) 
         {
           	
           try
           {
 
            
            //Server is running always. This is done using this while(true) loop
         
            	socket = serverSocket.accept();
            	
            	
            	switch(priorityKey)
            	{
            	case 1: //receive 
 
                    /** 
                     * READing population from the CLIENT
                     */
            		//System.out.println("Anthony awuley was here some"); System.exit(0);
            		
                    ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                    this.setRxPacket((Packet) ois.readObject());
                    
                    System.out.println("Server received population of "+ this.getRxPacket().getPopulation().size() + 
                    		" from "+ this.getRxPacket().getOrginationPort() + " to " + this.getRxPacket().getDestinationPort());
                        
                    break;
            	case 2:  //send
            		
            		/**
                     * SENDing population to CLIENT
                     * --server is serving as transit
                     */
                    //socket = this.getTransitServer().serverSocket.accept();
                    //System.out.println("Server sending population from "+ this.getConnectedClient().getClientName() +
                    //		" to "+ this.getTransitServer().getConnectedClient().getClientName() );
            		System.out.println("Server sending a population of "+ this.getTxPacket().getPopulation().size() + " to " 
                                       + this.getTxPacket().getDestinationPort() );
            		  
                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                    oos.writeObject(this.getTxPacket());
            		
            		break;
            	default:
            		System.out.println("-- nothing transmitted" );
            		break;
            	
            	}
            	
            }
      
        catch(EOFException eof)
        {
        	eof.printStackTrace();
        }
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        finally
        {  
            try
            {
                //socket.close();
            }
            catch(Exception e){}
        }
      }
    }  
    
    
    public static Socket  getSocket()
    {
    	return socket;
    }
    
    public void startServer()
    {
       //int port = Integer.parseInt(p);
    	
       try 
       {
           InetAddress address = InetAddress.getByName(this.host);
           serverSocket = new ServerSocket(this.port,this.backlog,address);
           //System.out.println(this.port+" "+ this.backlog + " "+ address); System.exit(0);
           //serverSocket = new ServerSocket(port);
           //serverSocket.setSoTimeout(10000); //set in param file
         }catch(IOException e)
         { 
           e.printStackTrace();
         }
    	   
    	
        t = this;
        t.start();
      
    }

    
    public Thread getThread()
    {
    	return this.t;
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
	 * @return the txPacket
	 */
	public Packet getTxPacket() {
		return txPacket;
	}


	/**
	 * @param txPacket the txPacket to set
	 */
	public void setTxPacket(Packet txPacket) {
		this.txPacket = txPacket;
	}


	/**
	 * @return the rxPacket
	 */
	public Packet getRxPacket() {
		return rxPacket;
	}


	/**
	 * @param rxPacket the rxPacket to set
	 */
	public void setRxPacket(Packet rxPacket) {
		this.rxPacket = rxPacket;
	}


	/**
	 * @return the rxPopulation
	 */
	public Population getRxPopulation() {
		return rxPopulation;
	}


	/**
	 * @param rxPopulation the rxPopulation to set
	 */
	public void setRxPopulation(Population rxPopulation) {
		this.rxPopulation = rxPopulation;
	}


	/**
	 * @return the txPopultion
	 */
	public Population getTxPopulation() {
		return txPopulation;
	}


	/**
	 * @param txPopultion the txPopultion to set
	 */
	public void setTxPopulation(Population txPopulation) {
		this.txPopulation = txPopulation;
	}


    
    
}