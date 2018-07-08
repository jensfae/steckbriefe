package de.faeustl.wp;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Writer {

	
	public static void updatePlayersSteckbrief(String wpID, String pSteckbrief)
	{
		String userCredentials = "jensfae:cwa0503mu";
		String basicAuth = Base64.getEncoder().encodeToString((userCredentials).getBytes(StandardCharsets.UTF_8));
		OkHttpClient client = new OkHttpClient();

//		URL url = new URL("http://handball.esv1927.de/wp-json/wp/v2/sp_player/");
		   HttpUrl url = new HttpUrl.Builder()
			       .scheme("http")
			       .host("handball.esv1927.de")
			       .addPathSegment("wp-json")
			       .addPathSegment("wp")
			       .addPathSegment("v2")
			       .addPathSegment("sp_player")
			       .addPathSegment(wpID)
			       .build();
			  
		
		Request request = new Request.Builder()
				.url(url)
				.addHeader("Content-Type", "application/json")
				.addHeader("Cache-Control", "no-cache")
				.addHeader("Authorization", "Basic "+basicAuth)
				.post(RequestBody.create(MediaType.parse(pSteckbrief), pSteckbrief))				
				.build();
		Response response = null;
		
	
			try {
				response = client.newCall(request).execute();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	


	}
}
