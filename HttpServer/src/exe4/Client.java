package exe4;

import java.io.*;
import java.net.*;

public class Client {
	public static void main(String argv[]) throws Exception {
		Socket s = new Socket("localhost", 5555);
		BufferedReader in = new BufferedReader(new InputStreamReader(
				s.getInputStream()));
		DataOutputStream out = new DataOutputStream(s.getOutputStream());
		out.writeBytes("GET /test.html HTTP/1.1\r\n");
		out.writeBytes("\r\n");
		String msg;
		while ((msg = in.readLine()) != null)
			System.out.println(msg);
		s.close();

		s = new Socket("localhost", 5555);
		in = new BufferedReader(new InputStreamReader(s.getInputStream()));
		out = new DataOutputStream(s.getOutputStream());
		out.writeBytes("POST /test.html HTTP/1.1\r\n");
		out.writeBytes("\r\n");
		while ((msg = in.readLine()) != null)
			System.out.println(msg);
		s.close();
	}
}
