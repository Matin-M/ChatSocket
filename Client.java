import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client{
	
	//Default Values
	public static String adress = "localhost";
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
			String temp1 = "localhost";
			int temp2 = 9999;
			Scanner serverInfo = new Scanner(System.in); 
			System.out.print("Enter the server IPv4, IPv6, or enter domain name to do DNS Lookup(Blank is default adress 'localhost'): ");
			try{
				temp1 = serverInfo.nextLine();
			}finally{ //finally block used to catch any unexpected issues in the try block.
				if(temp1.equals("")){
					adress = "localhost";
				}else{
					if (temp1.matches(".*[a-z].*")) { 
						InetAddress dns = InetAddress.getByName(temp1); 
						adress = dns.getHostAddress();
					}else{
						adress = temp1;
					}
				}
			}
			
			System.out.print("\nEnter the server port(Blank is default port 9999): ");
			port = serverInfo.nextInt();
			System.out.print(adress+" "+port);
			//Possible errors here
			client = new Socket(adress, port);
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

}
