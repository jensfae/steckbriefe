package de.faeustl.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Spiel")
public class DHBSpiel {
	
	public String getLiga() {
		return Liga;
	}

	public void setLiga(String liga) {
		Liga = liga;
	}

	public String getSpielDatum() {
		return SpielDatum;
	}

	public void setSpielDatum(String spielDatum) {
		SpielDatum = spielDatum;
	}

	public String getMannschaft1() {
		return Mannschaft1;
	}

	public void setMannschaft1(String mannschaft1) {
		Mannschaft1 = mannschaft1;
	}
	
	public String getMannschaft2() {
		return Mannschaft2;
	}

	public void setMannschaft2(String mannschaft2) {
		Mannschaft2 = mannschaft2;
	}

	public String getHalle() {
		return Halle;
	}

	public void setHalle(String halle) {
		Halle = halle;
	}

	public String getSpielVon() {
		
		LocalDateTime start = LocalDateTime.parse(SpielVon, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
		
		return start.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME).toString();
	}

	public void setSpielVon(String spielVon) {
		SpielVon = spielVon;
	}
	
	public String getHeim() {
		return Heim;
	}

	public void setHeim(String heim) {
		Heim = heim;
	}
	
	public String getHeimNr() {
		return HeimNr;
	}

	public void setHeimNr(String heimNr) {
		HeimNr = heimNr;
	}
	
	public String getGast() {
		return Gast;
	}

	public void setGast(String gast) {
		Gast = gast;
	}
	
	public String getGastNr() {
		return GastNr;
	}

	public void setGastNr(String gastNr) {
		GastNr = gastNr;
	}
	
	public String getSpieltag() {
		return Spieltag;
	}

	public void setSpieltag(String spieltag) {
		Spieltag = spieltag;
	}

	@XmlElement(name="Liga",required=false,nillable=false)
	private String Liga;
	
	@XmlElement(name="SpielDatum",required=true,nillable=false)
	private String SpielDatum;
	
	@XmlElement(name="Mannschaft1",required=true,nillable=false)
	private String Mannschaft1;
	
	@XmlElement(name="Mannschaft2",required=true,nillable=false)
	private String Mannschaft2;
	
	@XmlElement(name="Halle")
	private String Halle;
	
	@XmlElement(name="SpielVon")
	private String SpielVon;


	
	
	@XmlElement(name="Heim")
	private String Heim;
	
	@XmlElement(name="HeimNr")
	private String HeimNr;
	
	@XmlElement(name="Gast")
	private String Gast;
	
	@XmlElement(name="GastNr")
	private String GastNr;
	
	@XmlElement(name="Spieltag")
	private String Spieltag;
	
	
	public String wpHeimNummer;
	public String wpGastNummer;
	
	@XmlElement(name="Tore1")
	public int ergebnisHeim;
	@XmlElement(name="Tore2")
	public int ergebnisGast;
	
	public String getHeimWinLoss() {
		
			if (ergebnisHeim>ergebnisGast)
			{
				return "win";
			}
			else if (ergebnisHeim<ergebnisGast) {
				return "loss";
			}
			else return "draw";
		
	}
	public String getGastWinLoss()
	{
		if (ergebnisHeim>ergebnisGast)
		{
			return "loss";
		}
		else if (ergebnisHeim<ergebnisGast) {
			return "win";
		}
		else return "draw";
	}
	
	public String seasons = " \"seasons\": [ 286]";
	public String leagues = " \"leagues\": [ 56]";
	public String wpHallennummer;
	
public String getJSON() {
		
		String json = "{ " + leagues +", ";
		json += "\"title\": \"" + Heim + " vs. " + Gast + "\", ";
		json += "\"date\": \"" + getSpielVon() + "\", ";
		json += "\"teams\": [ " + wpHeimNummer + " , " + wpGastNummer + "], ";
		json += seasons;
		json += ",\"slug\": \"18-19-" + Liga + "\"";
		json += ",\"venues\": [" + wpHallennummer + "],";
		json += "\"dhb_spielnummer\": \"" + Liga +"\"";
		if (ergebnisHeim>0) {
		json += ",\"results\": {\"0\": {\n" + 
				"            \"firsthalf\": \"1st Half\",\n" + 
				"            \"secondhalf\": \"2nd Half\",\n" + 
				"            \"goals\": \"Goals\",\n" + 
				"            \"outcome\": \"Spielausgang\"\n" + 
				"        },\"" + wpHeimNummer + "\": { \"goals\": \"" +ergebnisHeim + "\",\n" + 
						"            \"outcome\": [\n" + 
						"                \""+getHeimWinLoss()+"\"\n" + 
						"            ] "
						
				
				
				+ "}, \"" +wpGastNummer +"\": {\"goals\": \"" +  ergebnisGast + "\",\n" + 
						"            \"outcome\": [\n" + 
						"                \""+getGastWinLoss()+ "\"\n" + 
						"            ]}}";
				
		}
		json += ",\"status\": \"future\"}";
		
		return json;
		
		
	}


}
