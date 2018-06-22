package exe1;

import java.io.*;
import java.net.*;

public class Client {
	public static void main(String argv[]) throws Exception {

		Socket s = new Socket("localhost", 5555);
		BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
		DataOutputStream out = new DataOutputStream(s.getOutputStream());
		System.out.print("DEBUG: Connected... ");
		System.out.println("Write something to receive server ECHO:");
		BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
		String msg = keyboard.readLine();

		System.out.println("DEBUG: Sending...");
		out.writeBytes(msg + "\n");

		System.out.println("DEBUG: Receiving...");
		msg = in.readLine();
		System.out.println(msg);
		s.close();
	}
}
