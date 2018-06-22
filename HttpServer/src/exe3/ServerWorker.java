package exe3;

import java.io.*;
import java.net.*;

public class ServerWorker implements Runnable {
	private Socket socket;

	public ServerWorker(Socket s) {
		this.socket = s;
	}

	public void run() {
		try {
			CalculatorProtocol cp = new CalculatorProtocol();
			BufferedReader in = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));
			DataOutputStream out = new DataOutputStream(
						socket.getOutputStream());
			String msg; 
			while ((msg = in.readLine()) != null){ // Is socket connected yet?
					System.out.println("DEBUG: Processing '" + msg + "'...");
					msg = cp.processMsg(msg);
					out.writeBytes(msg);
			}
			socket.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
