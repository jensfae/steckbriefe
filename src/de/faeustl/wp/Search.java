package de.faeustl.wp;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Search {

	public static String searchPlayer(String pName) {
		
		String userCredentials = "jensfae:cwa0503mu";
		String basicAuth = Base64.getEncoder().encodeToString((userCredentials).getBytes(StandardCharsets.UTF_8));
		OkHttpClient client = new OkHttpClient();
		String json = "";
		String id = "";
//		URL url = new URL("http://handball.esv1927.de/wp-json/wp/v2/sp_player/");
		   HttpUrl url = new HttpUrl.Builder()
			       .scheme("http")
			       .host("handball.esv1927.de")
			       .addPathSegment("wp-json")
			       .addPathSegment("wp")
			       .addPathSegment("v2")
			       .addPathSegment("sp_player")
			       .addQueryParameter("search", pName)
			       .addQueryParameter("fields", "id,title.rendered")
			       .build();
			  
		
		Request request = new Request.Builder()
				.url(url)
				.addHeader("Content-Type", "application/json")
				.addHeader("Cache-Control", "no-cache")
				.addHeader("Authorization", "Basic "+basicAuth)
				.get()				
				.build();
		Response response = null;
		
		try {
			response = client.newCall(request).execute();
			JsonParser jsonParsor = new JsonParser();
			json = response.body().string();
			
			JsonArray   arr=(JsonArray)jsonParsor.parse(json);     
			
			if (arr.size() > 0) {
			JsonObject obj=(JsonObject)arr.get(0); 

			id = obj.get("id").toString();
			}

			return id;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return id;
	}
	public static JsonObject searchTeam(String pName) {
		
		String userCredentials = "jensfae:cwa0503mu";
		String basicAuth = Base64.getEncoder().encodeToString((userCredentials).getBytes(StandardCharsets.UTF_8));
		OkHttpClient client = new OkHttpClient();
		String json = "";
		String id = "";
		JsonObject obj = null;
//		URL url = new URL("http://handball.esv1927.de/wp-json/wp/v2/sp_player/");
		   HttpUrl url = new HttpUrl.Builder()
			       .scheme("http")
			       .host("handball.esv1927.de")
			       .addPathSegment("wp-json")
			       .addPathSegment("wp")
			       .addPathSegment("v2")
			       .addPathSegment("sp_team")
			       .addQueryParameter("slug", pName)
			       .addQueryParameter("fields", "id,slug,title.rendered")
			       .build();
			  
		
		Request request = new Request.Builder()
				.url(url)
				.addHeader("Content-Type", "application/json")
				.addHeader("Cache-Control", "no-cache")
				.addHeader("Authorization", "Basic "+basicAuth)
				.get()				
				.build();
		Response response = null;
		
		try {
			response = client.newCall(request).execute();
			JsonParser jsonParsor = new JsonParser();
			json = response.body().string();
			
			JsonArray   arr=(JsonArray)jsonParsor.parse(json);     
			
			if (arr.size() > 0) {
			 obj=(JsonObject)arr.get(0); 
				
			
			}
			else {
				System.out.println("Nicht gefunden: " + pName);
			}

			return obj;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return obj;
		
	}
}
