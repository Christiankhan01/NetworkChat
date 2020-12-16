import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * ServerAdmin class extending a thread.
 * 
 * @run() 	- Takes input, assign to string. 
 * 					If "quit" then exit. 
 *
 */
public class ServerAdmin extends Thread {


	public void run() {
		Scanner scan = new Scanner(System.in); 
		while (true) {
			String cmd = scan.nextLine();  				//Wait on input
			if (cmd.equals("quit")) {
				System.exit(0);


			}
		}
	}

}



