import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
public class Server {
	
	//this class contains the server main method.
	//must be run first before clients can connect
	private static  ArrayList <ServerPerson> people; 	 
	
	public static void main(String[] args) {
		people = new ArrayList <ServerPerson> ();

		ServerAdmin admin = new ServerAdmin(); 
		admin.start();
		ServerSocket listener = null; 
		//        Server socket needs a port number and waits for a connection
		try {
			listener = new ServerSocket(9015); //Opens A server socket on port
			System.out.println("Server Running ........"); 
			while (true) { // Channel         
				Socket socket = listener.accept();            	//Meant for input / output (regular socket Waits for someone to connect and is waiting frozen
				ServerPerson sp = new ServerPerson(socket, people ); 
				people.add(sp); 
				sp.start();	// BUG HERE --> Had to change sp.start() to sp.run()
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				listener.close(); //Debugger points here <------
			} catch (IOException e) {
				e.printStackTrace();
			}	//needed to quit server 
		}

	}
}
