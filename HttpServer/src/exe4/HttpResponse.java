package exe4;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class HttpResponse {
	public byte httpMajorVersion = 1;
	public byte httpMinorVersion = 1;
	public String statusCode;
	public String reasonPhrase;

	public Map<String, String> headerFields = new HashMap<String, String>();

	public byte[] body;

	public void write(OutputStream output) throws IOException {
		PrintWriter pw = new PrintWriter(output);
		writeFirstLine(pw);
		writeHeader(pw);
		pw.write("\r\n"); //RF2616: header body separator
		pw.flush();
		if(body != null) //RFC2616: exists methods without body in response 
			output.write(body);
	}
	
	private void writeFirstLine(PrintWriter writer) throws IOException{
		writer.append("HTTP").append("/"+httpMajorVersion).append("."+httpMinorVersion);
		writer.write(' ');
		writer.print(statusCode);
		writer.write(' ');
		writer.print(reasonPhrase);
		writer.print("\r\n");
	}

	private void writeHeader(PrintWriter writer) throws IOException{
		for(Entry<String, String> entry: headerFields.entrySet()){
			writer.print(entry.getKey());
			writer.print(": ");
			writer.print(entry.getValue());
			writer.print("\r\n");
		}
	}
	
}
