package exe4;

public class HttpProtocol {

	public HttpResponse processMsg(HttpRequest msg) {
		HttpResponse response = new HttpResponse();

		switch (msg.httpMethod) {
		case GET:
			response.statusCode = "200";
			response.reasonPhrase = "Ok";
			response.body = "I'm almost a HTTP server!".getBytes();
			response.reasonPhrase = "Ok";
			break;
		default:
			response.statusCode = "501";
			response.reasonPhrase = "Not Implemented";
		}

		return response;
	}
}
