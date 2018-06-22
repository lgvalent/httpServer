package exe1;

import java.io.*;
import java.net.*;

public class Server {
	public static void main(String args[]) throws Exception {
		System.out.println("DEBUG: Running...");
		ServerSocket ss = new ServerSocket(5555);
		Socket socket = ss.accept();
		System.out.println("DEBUG: Connected...");
		BufferedReader in  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		DataOutputStream out = new DataOutputStream(socket.getOutputStream());
		String msg = in.readLine();
		System.out.println("DEBUG: Processing '" + msg + "'...");
		msg = "ECHO: " + msg;
		out.writeBytes(msg + "\n");
		ss.close();
	}
}
