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
			       .addPathSegment("sportspress")
			       .addPathSegment("v2")
			       .addPathSegment("players")
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
		
		JsonObject obj = null;
//		URL url = new URL("http://handball.esv1927.de/wp-json/wp/v2/sp_player/");
		   HttpUrl url = new HttpUrl.Builder()
			       .scheme("http")
			       .host("handball.esv1927.de")
			       .addPathSegment("wp-json")
			       .addPathSegment("sportspress")
			       .addPathSegment("v2")
			       .addPathSegment("teams")
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
	
	
public static String searchTeamByDHBNumber(String dhbNummer) {
		
		String userCredentials = "jensfae:cwa0503mu";
		String basicAuth = Base64.getEncoder().encodeToString((userCredentials).getBytes(StandardCharsets.UTF_8));
		OkHttpClient client = new OkHttpClient();
		String json = "";
		
		JsonObject obj = null;
//		URL url = new URL("http://handball.esv1927.de/wp-json/wp/v2/sp_player/");
		   HttpUrl url = new HttpUrl.Builder()
			       .scheme("http")
			       .host("handball.esv1927.de")
			       .addPathSegment("wp-json")
			       .addPathSegment("sportspress")
			       .addPathSegment("v2")
			       .addPathSegment("teams")
//			       .addPathSegment("wp")
//			       .addPathSegment("v2")
//			       .addPathSegment("sp_team")
			       
			       .addQueryParameter("filter[meta_key]", "dhb_mannschaftsnummer")
			       .addQueryParameter("filter[meta_value]", dhbNummer)
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
				System.out.println("Nicht gefunden: " + dhbNummer);
			}

			return obj.get("id").toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return obj.get("id").toString();
		
	}	

public static JsonObject searchTeamByDHBNummer(String pNummer) {
		
		String userCredentials = "jensfae:cwa0503mu";
		String basicAuth = Base64.getEncoder().encodeToString((userCredentials).getBytes(StandardCharsets.UTF_8));
		OkHttpClient client = new OkHttpClient();
		String json = "";
		
		JsonObject obj = null;
//		URL url = new URL("http://handball.esv1927.de/wp-json/wp/v2/sp_player/");
		   HttpUrl url = new HttpUrl.Builder()
			       .scheme("http")
			       .host("handball.esv1927.de")
			       .addPathSegment("wp-json")
			       .addPathSegment("sportspress")
			       .addPathSegment("v2")
			       .addPathSegment("teams")
//			       .addPathSegment("wp")
//			       .addPathSegment("v2")
//			       .addPathSegment("sp_team")
			   
			       .addQueryParameter("filter[meta_key]", "dhb_mannschaftsnummer")
			       .addQueryParameter("filter[meta_value]", pNummer)
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
				System.out.println("Nicht gefunden: " + pNummer);
			}

			return obj;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return obj;
		
	}
	
	public static String searchSpiel(String pSpielnummer) {
		
		String userCredentials = "jensfae:cwa0503mu";
		String basicAuth = Base64.getEncoder().encodeToString((userCredentials).getBytes(StandardCharsets.UTF_8));
		OkHttpClient client = new OkHttpClient();
		String json = "";
		String id = null;
//		URL url = new URL("http://handball.esv1927.de/wp-json/wp/v2/sp_player/");
		   HttpUrl url = new HttpUrl.Builder()
			       .scheme("http")
			       .host("handball.esv1927.de")
			       .addPathSegment("wp-json")
			       .addPathSegment("sportspress")
			       .addPathSegment("v2")
			       .addPathSegment("events")
//			       .addPathSegment("wp")
//			       .addPathSegment("v2")
//			       .addPathSegment("sp_event")

			       .addQueryParameter("slug", "18-19-" + pSpielnummer)
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

	public static JsonObject findVenueBySlug(String pSlug, String pHallennummer) {
		
		String userCredentials = "jensfae:cwa0503mu";
		String basicAuth = Base64.getEncoder().encodeToString((userCredentials).getBytes(StandardCharsets.UTF_8));
		OkHttpClient client = new OkHttpClient();
		String json = "";
	
		JsonObject obj= null;
		   HttpUrl url = new HttpUrl.Builder()
			       .scheme("http")
			       .host("handball.esv1927.de")
			       .addPathSegment("wp-json")
			       .addPathSegment("sportspress")
			       .addPathSegment("v2")
			       .addPathSegment("venues")
			       .addQueryParameter("search", pSlug)
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

			return obj;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return obj;
	}
public static JsonObject findVenueByDHBNummer(String pHallennummer) {
		
		String userCredentials = "jensfae:cwa0503mu";
		String basicAuth = Base64.getEncoder().encodeToString((userCredentials).getBytes(StandardCharsets.UTF_8));
		OkHttpClient client = new OkHttpClient();
		String json = "";
	
		JsonObject obj= null;
		   HttpUrl url = new HttpUrl.Builder()
			       .scheme("http")
			       .host("handball.esv1927.de")
			       .addPathSegment("wp-json")
			       .addPathSegment("sportspress")
			       .addPathSegment("v2")
			       .addPathSegment("venues")
			       .addQueryParameter("filter[meta_key]", "hallennummer_dhb")
			       .addQueryParameter("filter[meta_value]", pHallennummer)
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

			return obj;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return obj;
	}

	public static JsonObject findVenue(String pSlug, String pNummer, String pName) {
		
		String userCredentials = "jensfae:cwa0503mu";
		String basicAuth = Base64.getEncoder().encodeToString((userCredentials).getBytes(StandardCharsets.UTF_8));
		OkHttpClient client = new OkHttpClient();
		String json = "";
	
		JsonObject obj= null;
		   HttpUrl url = new HttpUrl.Builder()
			       .scheme("http")
			       .host("handball.esv1927.de")
			       .addPathSegment("wp-json")
			       .addPathSegment("sportspress")
			       .addPathSegment("v2")
			       .addPathSegment("venues")
			       .addQueryParameter("filter[meta_key]", "hallennummer_bhv")
			       .addQueryParameter("filter[meta_value]", pNummer)
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
			else
			{
				obj=findVenueBySlug(pSlug, pNummer);
				try {
					obj=Writer.updateVenue(obj.get("id").getAsString(), "{ \"hallennummer_bhv\": \" "+ pNummer + "\" }");
				}
				catch (Exception exc)
				 {System.out.println("Halle nicht gefunden: " +pName);}
			}
			return obj;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Halle nicht gefunden: " +pName);
			e.printStackTrace();
		}

		
		return obj;
	}
	public static JsonObject searchTeamByBHVNummer(String pNummer) {
			
			String userCredentials = "jensfae:cwa0503mu";
			String basicAuth = Base64.getEncoder().encodeToString((userCredentials).getBytes(StandardCharsets.UTF_8));
			OkHttpClient client = new OkHttpClient();
			String json = "";
			
			JsonObject obj = null;
	//		URL url = new URL("http://handball.esv1927.de/wp-json/wp/v2/sp_player/");
			   HttpUrl url = new HttpUrl.Builder()
				       .scheme("http")
				       .host("handball.esv1927.de")
				       .addPathSegment("wp-json")
				       .addPathSegment("sportspress")
				       .addPathSegment("v2")
				       .addPathSegment("teams")
	//			       .addPathSegment("wp")
	//			       .addPathSegment("v2")
	//			       .addPathSegment("sp_team")
				   
				       .addQueryParameter("filter[meta_key]", "bhv_mannschaftsnummer")
				       .addQueryParameter("filter[meta_value]", pNummer)
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
					System.out.println("Nicht gefunden: " + pNummer);
				}
	
				return obj;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
			return obj;
			
		}
	public static String searchBHVSpieByNumberl(String pSpielnummer) {
			
			String userCredentials = "jensfae:cwa0503mu";
			String basicAuth = Base64.getEncoder().encodeToString((userCredentials).getBytes(StandardCharsets.UTF_8));
			OkHttpClient client = new OkHttpClient();
			String json = "";
			String id = null;
	//		URL url = new URL("http://handball.esv1927.de/wp-json/wp/v2/sp_player/");
			   HttpUrl url = new HttpUrl.Builder()
				       .scheme("http")
				       .host("handball.esv1927.de")
				       .addPathSegment("wp-json")
				       .addPathSegment("sportspress")
				       .addPathSegment("v2")
				       .addPathSegment("events")
	//			       .addPathSegment("wp")
	//			       .addPathSegment("v2")
	//			       .addPathSegment("sp_event")
	
				       .addQueryParameter("filter[meta_key]", "bhv_spielnummer")
				       .addQueryParameter("filter[meta_value]", pSpielnummer)
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
}
