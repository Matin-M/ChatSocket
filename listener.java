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

public class listener implements Runnable{
	
	private Socket client = null;
	String response = null;
	BufferedReader in = null;
	
	
	public listener(){
		System.out.println("There is no default constructor for listner class!");
	}
	
	public listener(Socket client){
		this.client = client;
		IO();
	}
	
	//Insert documentation
	public void IO(){
		try{
			in = new BufferedReader(new InputStreamReader(client.getInputStream()));
		}catch(IOException e){
			System.err.println("listner had an error: " + e);
		}
		
	}
	
	@Override
	public void run() {
		try{
			while(true){
				response = in.readLine();
				if(response.equals("") || response.equals(null)){
					System.err.println("Other client has disconnected, or the ServerSocket lost connection: ");
				}
				System.out.println("Client 2 Says: " + response + "\n");
			}
		}catch(IOException e){
			System.err.println("Sorry! Listner class had an error: " + e);
		}
	}

	
}
