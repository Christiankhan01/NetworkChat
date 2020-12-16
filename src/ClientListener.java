import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
	//this class is responsible for listening for a connection between clients and server 
	// and writing messages to file.
	public class ClientListener extends Thread {
		private BufferedReader serverInput;
		
		private boolean isFile = false; 
		private PrintWriter writer; 
		
		ClientListener(BufferedReader serverInput){
			this.serverInput = serverInput;
		}
		
		public void run() 
		{
			while (true) {
				
			String message = "";
			try { 
				message = serverInput.readLine();
			} catch (IOException e) {
				System.out.println("Connection Dropped, quitting...");
				System.exit(-1);
				
				return; 
			}
			System.out.println( message);
			//bonus here! write to file!
			if (message.startsWith("STARTFILE")) {
				isFile = true; 
				try {
					//create a file named xxxx.txt
					writer = new PrintWriter("testFile2.txt");
				} catch (Exception e) {
					System.out.println("Something happened");
				}		
				//stop writing to file here
			}else if(message.equals("STOPFILE")) {
				writer.flush();
				System.out.println("File Completed ");
				isFile = false; 
				
			}else {
				if (isFile) {
				
				writer.println(message);
				
				writer.println("Hello There");
				writer.flush();
				System.out.println("======= " + message);
				}else{
				System.out.println("Receieved Message: " + message);
				}
			}	
		}
	}
}
	
