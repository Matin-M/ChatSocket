import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.omg.CORBA.portable.InputStream;


public class clientHandler2 implements Runnable{ //must only work in separate class

	private Socket client1;
	private Socket client2;
	
	
	//Client 1 Communication
	private PrintWriter out = null;
	                
	//Client 2 Communication
	BufferedReader in2 = null;
	String response2 = null;

	
	public clientHandler2(){
		System.err.println("There is no default constuctor");
	}
	
	//Constructor
	public clientHandler2(Socket client1, Socket client2){
		this.client1 = client1;
		this.client2 = client2;
		
		IO();
		               
	}
	
	public void IO(){
		try {
			in2 = new BufferedReader(new InputStreamReader(client2.getInputStream()));
            out = new PrintWriter(client1.getOutputStream(), true);
		} catch (IOException e) {
			System.err.println("Client Handler 2 had an error: " + e);
		}
          
	}
	
	//Overridden run method
	
	@Override
	public void run() {
		
		while(true){
			try {
				//response from client 1 and sends it to client 2
				response2 = in2.readLine();
				out.println(response2);
			} catch (IOException e) {
				System.err.println("Client Handler 2 had an error: " + e);
				break;

			}
		}
       
	}
	
}
