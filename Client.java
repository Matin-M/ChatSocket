import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client{
	
	//WHile we defasult to localhost as the address
	// other examples of address would be:
	// "califronia.servecounterstrike.com"
	// THis is different from a URL in that:
	// URL associated with an IP address
	// (Registered domain name maps to IP address)
	// THe address here is always a registered
	// domain name.
	public static String address = "localhost";
	
	// Example ports 
	// that are allowed, for Linux there is a range
	// of integer values that correspond to 'ports'
	// these are different than for 
	// 0-2555 for Linux
	// 0-3000 for windows
	
	public static int port = 9999;

	public static void main(String[] args) throws IOException {

		//Input data 
		String text;
		Scanner input;
		
		//Sockets for server contact, printwriter and bufferedreader used to read
		//and write from server.
		Socket client = null;
		PrintWriter out = null;
		
		//
		/*
		listener.start()
		listener.setPriority();
		*/
		listener listen;
		Thread thread;
		
		
		
		//Try-catch system to handle possible errors. 
		try{
		
			System.out.print(address+" "+port);
			//Possible errors here
			client = new Socket(address, port);
			out = new PrintWriter(client.getOutputStream(), true);
			listen = new listener(client);
			thread = new Thread(listen);
			thread.setPriority(Thread.MAX_PRIORITY);
			thread.start();
            
            while(true){
        		input = new Scanner(System.in);
        		text = input.nextLine();
        		out.println(text);
        		
        		
            }
		//This catch block is used to inform the user. More advanced implementation such as re-directs and other features 
        //will be implemented soon. 
		}catch(Exception e){
			System.err.println("Oops! There was an Error: " + e);
		}	
		
	}
	
	public void useServer(){
		try{
			String temp1 = "localhost";
			int temp2 = 9999;
			Scanner serverInfo = new Scanner(System.in); 
			System.out.print("Enter the server IPv4, IPv6, or enter domain name to do DNS Lookup(Blank is default address 'localhost'): ");
			System.out.println("The localhost adress is used for testing");
			try{
				temp1 = serverInfo.nextLine();
			}finally{ //finally block used to catch any unexpected issues in the try block.
				if(temp1.equals("") || temp1.equals(" ")){
					address = "localhost";
				}else{
					//Check if the submitted string of text is a domain(detect for latin charactars). 
					if (temp1.matches(".*[a-z].*")) { 
						InetAddress dns = InetAddress.getByName(temp1); 
						address = dns.getHostAddress();
					}else{
						address = temp1;
					}
				}
				System.out.print("\nEnter the server port(Blank is default port 9999): ");
				port = serverInfo.nextInt();
			}
		}catch(UnknownHostException u){
			System.err.println("Improper host error: ");
			//System.err.println(u.printStackTrace());
		}catch(Exception e){
			System.err.println("There was an error:" + e.getMessage());
		}
		
		
	}

}
