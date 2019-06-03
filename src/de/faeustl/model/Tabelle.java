package de.faeustl.model;

import java.util.ArrayList;
import java.util.List;

import de.faeustl.wp.Search;

public class Tabelle {

	public String wpID;
	private String startString = "\"data\": {\n" + 
			"        \"0\": {\n" + 
			"            \"pos\": \"Pos.\",\n" + 
			"            \"name\": \"Mannschaft\",\n" + 
			"            \"ptstwo\": \"Pts-\",\n" + 
			"            \"pts\": \"Pkt\",\n" + 
			"            \"sp\": \"Sp\",\n" + 
			"            \"diff\": \"DIFF\",\n" + 
			"            \"w\": \"W\",\n" + 
			"            \"d\": \"D\",\n" + 
			"            \"l\": \"L\",\n" + 
			"            \"f\": \"F\",\n" + 
			"            \"a\": \"A\"\n" + 
			"        }";
	
	public List<TabellenPlatz> plaetze = new ArrayList <TabellenPlatz>() ;
	
	public String getJSON () {
		
		String json = startString;
		
		for (TabellenPlatz mannschaft: plaetze) {
			
			
			
			
			json += ", " + mannschaft.getJson()  ;
			
			
			
		}
		json += "}";
		System.out.println(json);
		return json;
		
	}
	
	
}
