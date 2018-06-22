package exe6;

import java.net.*;

public class Server {
	public static void main(String args[]) throws Exception {
		ServerSocket ss = new ServerSocket(5555);
		while(ss.isBound()){
			Socket s = ss.accept();
			new Thread(new ServerWorker(s)).run();
		}	
		ss.close();
	}
}
