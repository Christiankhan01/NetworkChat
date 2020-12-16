import java.io.PrintWriter;

import java.net.Socket;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


//this class is responsible for 
//listening for data and Sending

public class ServerPerson extends Thread
{
	private String personName; 
	private PrintWriter out; 
	private BufferedReader in; 
	private static ArrayList<ServerPerson> people;
	private boolean isFile = false; 

	public PrintWriter getOut() {
		return out;
	}


	public String getPersonName() {
		try {
			personName = in.readLine();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(personName + " just joined" );
		return personName; 
	}

	public void run() {	
		getPersonName();  
		broadCast(this.personName + " just joined"); 
		while (true) {
			try {	
				String message = in.readLine();
				System.out.println("Receieved Message: "  + message);
				broadCast(message); 

			}catch (IOException e) 
			{

				e.printStackTrace();

			}

		}
	}


	/**
	 * For serverAdmin to be able to send data, 
	 * 1. 	should be added to ServerPerson and keep privileges 
	 * 2. 	should be able to printWrite own messages. 
	 */
	public void broadCast(String s) 
	{
		for(ServerPerson p: people) 
		{
			if(p != this)

			{
				synchronized(this) {
					if(s.startsWith("STARTFILE")) {
						isFile = true;
					}
					if(isFile) {
						p.getOut().println(s);
					}else {
						p.getOut().println(p.personName + "  " + s);

					}
					p.getOut().flush();; //Forces server to send msg, doesn't buffer
					if(s.startsWith("STOPFILE")) {
						isFile=false; 
					}


				}
			}
		}
	}

	ServerPerson(Socket s, ArrayList<ServerPerson> people) 
	{

		ServerPerson.people = people;		

		try 
		{	//Receive information from this.person
			in = new BufferedReader(new InputStreamReader(s.getInputStream()));

			//Send information from this.person
			out = new PrintWriter(s.getOutputStream(), true);
		} catch (IOException e) 
		{

			e.printStackTrace();
		}

	}
}
