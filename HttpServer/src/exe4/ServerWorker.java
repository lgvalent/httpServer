package exe4;

import java.io.IOException;
import java.net.Socket;

public class ServerWorker implements Runnable {
	private Socket socket;

	public ServerWorker(Socket s) {
		this.socket = s;
	}

	public void run() {
		try {
			HttpProtocol httpProtocol = new HttpProtocol();

			HttpRequest request = new HttpRequest(socket.getInputStream());
			
			HttpResponse response = httpProtocol.processMsg(request);
			
			response.write(socket.getOutputStream());
			
			socket.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
