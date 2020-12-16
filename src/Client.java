import java.io.IOException;

import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.JOptionPane;
public class Client {
//this class contains the client main method
//can only be run after the server is running.
	public static void main(String[] args) throws IOException {
		Socket s = new Socket("localhost",9015);
		BufferedReader input =
				new BufferedReader(new InputStreamReader(s.getInputStream()));

		PrintWriter out = new PrintWriter(s.getOutputStream(), true);


		ClientListener clientListener = new ClientListener(input);
		clientListener.start();


		Scanner scan = new Scanner(System.in);
		String name = scan.nextLine();
		out.println(name);
		while (true) {
			System.out.println("Give Input: ");
			String message = scan.nextLine();

			if(message.startsWith("STARTFILE")) {
				String[] fileName = message.split(" ");
				out.println(message);		

				try (BufferedReader br = new BufferedReader(new FileReader(fileName[1]))) {
					String line;
					while ((line = br.readLine()) != null) {
						out.println(line);
					}
				}

				out.println("STOPFILE");
				scan.close(); 
			}

			out.println(message);		//Sends To server
		}
	}
}

