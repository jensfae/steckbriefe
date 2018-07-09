package de.faeustl.wp;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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
		
	
			try {
				client.newCall(request).execute();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	


	}
	

	
	public static JsonObject createTeam(String pName, String pSlug)
	{
		String userCredentials = "jensfae:cwa0503mu";
		String basicAuth = Base64.getEncoder().encodeToString((userCredentials).getBytes(StandardCharsets.UTF_8));
		
		String insertString = "{\"title\" : \"" + pName +"\", \"slug\": \"" + pSlug + "\", \"status\":\"publish\"}" ;
		OkHttpClient client = new OkHttpClient();
		String json = "";
		
		JsonObject obj = null;
		
//		URL url = new URL("http://handball.esv1927.de/wp-json/wp/v2/sp_player/");
		   HttpUrl url = new HttpUrl.Builder()
			       .scheme("http")
			       .host("handball.esv1927.de")
			       .addPathSegment("wp-json")
			       .addPathSegment("wp")
			       .addPathSegment("v2")
			       .addPathSegment("sp_team")
			     
			       .build();
			  
		
		Request request = new Request.Builder()
				.url(url)
				.addHeader("Content-Type", "application/json")
				.addHeader("Cache-Control", "no-cache")
				.addHeader("Authorization", "Basic "+basicAuth)
				.post(RequestBody.create(MediaType.parse(insertString), insertString))				
				.build();
		Response response = null;
		
	
			try {
				response = client.newCall(request).execute();
				JsonParser jsonParsor = new JsonParser();
				json = response.body().string();
				
				obj =(JsonObject)jsonParsor.parse(json);     
				
				

				return obj;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return obj;


	}
	public static JsonObject createSpiel(String pJson)
	{
		String userCredentials = "jensfae:cwa0503mu";
		String basicAuth = Base64.getEncoder().encodeToString((userCredentials).getBytes(StandardCharsets.UTF_8));
		
		
		OkHttpClient client = new OkHttpClient();
		String json = "";
		
		JsonObject obj = null;
		

		   HttpUrl url = new HttpUrl.Builder()
			       .scheme("http")
			       .host("handball.esv1927.de")
			       .addPathSegment("wp-json")
			       .addPathSegment("wp")
			       .addPathSegment("v2")
			       .addPathSegment("sp_event")
			     
			       .build();
			  
		
		Request request = new Request.Builder()
				.url(url)
				.addHeader("Content-Type", "application/json")
				.addHeader("Cache-Control", "no-cache")
				.addHeader("Authorization", "Basic "+basicAuth)
				.post(RequestBody.create(MediaType.parse(pJson), pJson))				
				.build();
		Response response = null;
		
	
			try {
				response = client.newCall(request).execute();
				JsonParser jsonParsor = new JsonParser();
				json = response.body().string();
				
				obj =(JsonObject)jsonParsor.parse(json);     
				
				

				return obj;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return obj;


	}
	public static JsonObject update(String pSpielnummer, String pJson)
	{
		String userCredentials = "jensfae:cwa0503mu";
		String basicAuth = Base64.getEncoder().encodeToString((userCredentials).getBytes(StandardCharsets.UTF_8));
		
		
		OkHttpClient client = new OkHttpClient();
		String json = "";
		
		JsonObject obj = null;
		

		   HttpUrl url = new HttpUrl.Builder()
			       .scheme("http")
			       .host("handball.esv1927.de")
			       .addPathSegment("wp-json")
			       .addPathSegment("wp")
			       .addPathSegment("v2")
			       .addPathSegment("sp_event")
			       .addPathSegment(pSpielnummer)
			       .build();
			  
		
		Request request = new Request.Builder()
				.url(url)
				.addHeader("Content-Type", "application/json")
				.addHeader("Cache-Control", "no-cache")
				.addHeader("Authorization", "Basic "+basicAuth)
				.post(RequestBody.create(MediaType.parse(pJson), pJson))				
				.build();
		Response response = null;
		
	
			try {
				response = client.newCall(request).execute();
				JsonParser jsonParsor = new JsonParser();
				json = response.body().string();
				
				obj =(JsonObject)jsonParsor.parse(json);     
				
				

				return obj;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return obj;


	}
}
