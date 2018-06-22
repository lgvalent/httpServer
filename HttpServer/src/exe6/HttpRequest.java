package exe6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class HttpRequest {
	static enum HttpMethods {
		GET, POST, HEAD, DELETE, TRACE, CONNECT, OPTIONS
	};

	public HttpMethods httpMethod;
	public String path;
	public byte httpMajorVersion;
	public byte httpMinorVersion;

	public Map<String, String> headerFields = new HashMap<String, String>();

	public HttpRequest(InputStream input) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(input));
		readFirstLine(br);
		readHeader(br);
	}

	private void readFirstLine(BufferedReader reader) throws IOException {
		String firstLine = reader.readLine();
		System.out.println("DEBUG: First line of request: " + firstLine);

		String[] parts = firstLine.split(" ");
		httpMethod = HttpMethods.valueOf(parts[0]);
		path = parts[1];
		// HTTP/1.1
		httpMajorVersion = Byte.parseByte("" + parts[2].charAt(5));
		httpMinorVersion = Byte.parseByte("" + parts[2].charAt(7));
	}

	private void readHeader(BufferedReader reader) throws IOException {
		String line;
		while (!(line = reader.readLine()).equals("")) {
			System.out.println("DEBUG: Header line: " + line);
			String[] parts = line.split(":"); 
			String key = parts[0];
			String value = parts[1].trim(); // Remove SP. LWS not supported yet!
			headerFields.put(key, value);
		}
	}
}
