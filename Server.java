import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.omg.CORBA.portable.InputStream;

//Make sure to implement threads in another class when trying to multithread applications


public class Server {
        
    
	public static final String ADRESS = "localhost"; //Alternatively use localhost as the network adress
	public static final int PORT = 9999; //Make sure to use universally open ports: unix and other systems may be different
	public static ServerSocket serverSocket;
	
	public static void main(String[] args) {

		
        //Server port 
		ServerSocket server = null;
               
        //Client 1 Communication
		Socket connection = null;

        //Client 2 Communication
        Socket connection2 = null;
        
        //Clients
        //ArrayList<Socket> clients = new ArrayList<>();
        //ArrayList<clientHandler1> handler = new ArrayList<>();
        
        //loopstuff
        int sentinel = 0;
		
		//Try-catch system to handle errors.
	
		try{
			
			//New ServerSocket object
			server = new ServerSocket(PORT); 
			
			//Work with clients
			System.out.println("Waiting for Client 1 Connection...");
			connection = server.accept(); //.accept() method waits.
			System.out.println("Client 1 has connected!");
            System.out.println("Waiting for Client 2 Connection...");
            connection2 = server.accept(); //.accept() method waits
            System.out.println("Client 2 has connected!");
				
            //Start threads for clientHandling
            clientHandler1 client1 = new clientHandler1(connection,connection2);
            clientHandler2 client2 = new clientHandler2(connection, connection2);
            Thread thread1 = new Thread(client1);
            Thread thread2 = new Thread(client2);
            thread1.setPriority(Thread.MAX_PRIORITY);
            thread2.setPriority(Thread.MAX_PRIORITY);
            
            //start threads
            thread1.start();
            thread2.start();
            
            
	            
				
		}catch(IOException e){
			
			System.err.print("Oops! There was an Error: " + e);
		}
		
		 
}
}



