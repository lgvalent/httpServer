package exe6;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.activation.MimetypesFileTypeMap;

public class HttpProtocol {

	public HttpResponse processMsg(HttpRequest msg) {
		HttpResponse response = new HttpResponse();

		switch (msg.httpMethod) {
		case GET: response = processGET(msg); break;
		default:
			response.statusCode = "501";
			response.reasonPhrase = "Not Implemented";
		}

		return response;
	}
	
	
	private HttpResponse processGET(HttpRequest msg){
		File file = new File(msg.path);
		
		if(file.exists()){
			try {
				HttpResponse response = new HttpResponse();
				response.statusCode = "200";
				response.headerFields.put("Content-Length", ""+file.length());
				response.headerFields.put("Content-Type", new MimetypesFileTypeMap().getContentType(file));
				response.body = Files.readAllBytes(Paths.get(msg.path));
				return response;
			} catch (IOException e) {
				return response500(msg, e);
			}
		} else 
			return response404(msg);
	}
	
	private HttpResponse response404(HttpRequest msg){
		HttpResponse response = new HttpResponse();
		response.statusCode = "404";
		response.reasonPhrase = "Resource not found!";
		
		/* source from http://google.com/notFound.html*/
		String pageSource = "<!DOCTYPE html>\n<html lang=en>\n  <meta charset=utf-8>\n  <meta name=viewport content=\"initial-scale=1, minimum-scale=1, width=device-width\">\n  <title>Error 404 (Not Found)!!1</title>\n  <style>\n    *{margin:0;padding:0}html,code{font:15px/22px arial,sans-serif}html{background:#fff;color:#222;padding:15px}body{margin:7% auto 0;max-width:390px;min-height:180px;padding:30px 0 15px}* > body{background:url(//www.google.com/images/errors/robot.png) 100% 5px no-repeat;padding-right:205px}p{margin:11px 0 22px;overflow:hidden}ins{color:#777;text-decoration:none}a img{border:0}@media screen and (max-width:772px){body{background:none;margin-top:0;max-width:none;padding-right:0}}#logo{background:url(//www.google.com/images/branding/googlelogo/1x/googlelogo_color_150x54dp.png) no-repeat;margin-left:-5px}@media only screen and (min-resolution:192dpi){#logo{background:url(//www.google.com/images/branding/googlelogo/2x/googlelogo_color_150x54dp.png) no-repeat 0% 0%/100% 100%;-moz-border-image:url(//www.google.com/images/branding/googlelogo/2x/googlelogo_color_150x54dp.png) 0}}@media only screen and (-webkit-min-device-pixel-ratio:2){#logo{background:url(//www.google.com/images/branding/googlelogo/2x/googlelogo_color_150x54dp.png) no-repeat;-webkit-background-size:100% 100%}}#logo{display:inline-block;height:54px;width:150px}\n  </style>\n  <a href=//www.google.com/><span id=logo aria-label=Google></span></a>\n  <p><b>404.</b> <ins>That’s an error.</ins>\n  <p>The requested URL <code>" +
							msg.path +
							"</code> was not found on this server.  <ins>That’s all we know.</ins>"; 
		response.body = pageSource.getBytes();		
		return response;
	}

	private HttpResponse response500(HttpRequest msg, Exception e){
		HttpResponse response = new HttpResponse();
		response.statusCode = "500";
		response.reasonPhrase = "Internal Error";
		
		/* source from http://google.com/notFound.html*/
		String pageSource = "<!DOCTYPE html>\n<html lang=en>\n  <meta charset=utf-8>\n  <meta name=viewport content=\"initial-scale=1, minimum-scale=1, width=device-width\">\n  <title>Internal Error 500!</title>\n  <style>\n    *{margin:0;padding:0}html,code{font:15px/22px arial,sans-serif}html{background:#fff;color:#222;padding:15px}body{margin:7% auto 0;max-width:390px;min-height:180px;padding:30px 0 15px}* > body{background:url(//www.google.com/images/errors/robot.png) 100% 5px no-repeat;padding-right:205px}p{margin:11px 0 22px;overflow:hidden}ins{color:#777;text-decoration:none}a img{border:0}@media screen and (max-width:772px){body{background:none;margin-top:0;max-width:none;padding-right:0}}#logo{background:url(//www.google.com/images/branding/googlelogo/1x/googlelogo_color_150x54dp.png) no-repeat;margin-left:-5px}@media only screen and (min-resolution:192dpi){#logo{background:url(//www.google.com/images/branding/googlelogo/2x/googlelogo_color_150x54dp.png) no-repeat 0% 0%/100% 100%;-moz-border-image:url(//www.google.com/images/branding/googlelogo/2x/googlelogo_color_150x54dp.png) 0}}@media only screen and (-webkit-min-device-pixel-ratio:2){#logo{background:url(//www.google.com/images/branding/googlelogo/2x/googlelogo_color_150x54dp.png) no-repeat;-webkit-background-size:100% 100%}}#logo{display:inline-block;height:54px;width:150px}\n  </style>\n  <a href=//www.google.com/><span id=logo aria-label=Google></span></a>\n  <p><b>404.</b> <ins>That’s an internal error.</ins>\n  <p>The requested URL <code>" +
							msg.path +
							"</code> generate the following error:<p>"+
							e.getMessage() +
							".  <ins>That’s all we know.</ins>"; 
		response.body = pageSource.getBytes();		
		return response;
	}
}
