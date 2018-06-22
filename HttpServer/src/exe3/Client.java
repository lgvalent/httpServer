package exe3;

import java.io.*;
import java.net.*;

public class Client {
	public static void main(String argv[]) throws Exception {
		Socket s = new Socket("localhost", 5555);
		BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
		DataOutputStream out = new DataOutputStream(s.getOutputStream());
		out.writeBytes("RESET\r\n");
		String msg = in.readLine();
		System.out.println(msg);

		out.writeBytes("ADD:22\r\n");
		msg = in.readLine();
		System.out.println(msg);
		
		out.writeBytes("SUB:2\r\n");
		msg = in.readLine();
		System.out.println(msg);

		out.writeBytes("DIV:2\r\n");
		msg = in.readLine();
		System.out.println(msg);
		s.close();
	}
}
