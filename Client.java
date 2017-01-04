import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client{
	
	public static final String ADRESS = "localhost";
	public static final int PORT = 9999;

	public static void main(String[] args) throws IOException {

		
		String text;
		Scanner input;
		
		//Sockets for server contact, printwriter and bufferedreader used to read
		//and write from server.
		

		Socket client = null;
		PrintWriter out = null;
		
		listener listen;
		Thread thread;
		
		//Try-catch system to handle errors.
		
		try{
			
			client = new Socket(ADRESS,PORT);
			System.out.println("kek");
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
			
		}catch(IOException e){
			
			
			System.err.println("Oops! There was an Error: " + e);
			
		}
		
		
		
	}

}
