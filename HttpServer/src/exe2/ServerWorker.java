package exe2;

import java.io.*;
import java.net.*;

public class ServerWorker implements Runnable {
	private Socket socket;

	public ServerWorker(Socket s) {
		this.socket = s;
	}

	public void run() {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			DataOutputStream out = new DataOutputStream(
					socket.getOutputStream());
			String msg;
			msg = in.readLine();
			System.out.println("DEBUG: Processing '" + msg + "'...");
			msg = "ECHO: " + msg;
			out.writeBytes(msg + "\n");
			socket.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
