package de.faeustl.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.opencsv.bean.CsvBindByName;

public class BHVSpielRest {

	
    private Date datum;
	

    private String zeit;
	

    private String hallennummer;
	
	
    private String spielnummer;

	
	private String heimmannschaft;
	
	public String wpHeimmannschaftID;
	
	
	private String gastmannschaft;
	
	public String wpGastmannschaftID;
	
	
	private String liga;
	
	
	private String staffelkurzbezeichnung;

	
	public String ergebnis;
	
	public int toreHeim;
	public int toreGast;
	public int halbZeitHeim;
	public int halbZeitGast;
	
	
	@SuppressWarnings("deprecation")
	public Integer getErgebnisHeim() {
		
		String split = ergebnis.substring(2).split(":")[0];
		
		return new Integer(split);
	}

	@SuppressWarnings("deprecation")
	public Integer getErgebnisGast() {
		
		String split = ergebnis.split(":")[1];
		
		return new Integer(split);
	}
	
	
	public String getHeimWinLoss()
	{
		if (toreHeim > toreGast)
		{
			return "win";
		}
		else if (toreHeim < toreGast) {
			return "loss";
		}
		else return "draw";
	}
	public String getGastWinLoss()
	{
		if (toreHeim > toreGast)
		{
			return "loss";
		}
		else if (toreHeim < toreGast) {
			return "win";
		}
		else return "draw";
	}
	
	
	public void setErgebnis(String ergebnis) {
		this.ergebnis = ergebnis;
	}

	public String getHalbzeitergebnis() {
		return halbzeitergebnis;
	}

	public void setHalbzeitergebnis(String halbzeitergebnis) {
		this.halbzeitergebnis = halbzeitergebnis;
	}

	@CsvBindByName (column = "Halbzeitergebnis")
	public String halbzeitergebnis;
	
	
	public String leagues = "339"; 
//			"\"seasons\": [286]";
	
	private String wpHallennummer;
	
	public String getWpHallennummer() {
		return wpHallennummer;
	}

	public void setWpHallennummer(String wpHallennummer) {
		this.wpHallennummer = wpHallennummer;
	}

	public String getGastmannschaft() {
		return gastmannschaft;
	}

	public void setGastmannschaft(String gastmannschaft) {
		this.gastmannschaft = gastmannschaft;
	}

	public String getLiga() {
		return "\"seasons\": [339]";
	}

	public void setLiga(String liga) {
		this.liga = liga;
	}

	public String getStaffelkurzbezeichnung() {
		return staffelkurzbezeichnung;
	}

	public void setStaffelkurzbezeichnung(String staffelkurzbezeichnung) {
		this.staffelkurzbezeichnung = staffelkurzbezeichnung;
	}

	public String getWPNummer() {
		try {
//			System.out.println(staffelkurzbezeichnung);
			
		return LigaEnum.valueOf(liga.replace(" ", "")).getWpNummer(staffelkurzbezeichnung);
		}
		catch (Exception exp)
		{
			return null;
		}
	}
	
	public String getSpielStart()
	{
		String isoDatePattern = "yyyy-MM-dd'T'HH:mm:ss";

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(isoDatePattern);

		String dateString = simpleDateFormat.format(getDatum());
		
		return dateString;
		
	}
	
	public String getJSON() {
		
//		String json = "{ \"leagues\": ["+ getWPNummer().trim() + " ], ";
		String json = "{ \"title\": \"" + getHeimmannschaft() + " vs. " + getGastmannschaft() + "\", ";
		json += "\"date\": \"" +getSpielStart() + "\", ";
		json += "\"teams\": [ " + wpHeimmannschaftID + " , " + wpGastmannschaftID + "], ";
//		json += season;
//		json += ",\"slug\": \"18-19-" + spielnummer + "\"";
		json += "\"leagues\": [ " + leagues + "] ";
		json += ",\"bhv_spielnummer\": \"" +getSpielnummer() + "\" ";
//		json += ",\"venues\": [" + wpHallennummer + "]";
		
		
		if (toreHeim>0) {
		json += ",\"results\": {\"0\": {\n" + 
				"            \"firsthalf\": \"1st Half\",\n" + 
				"            \"secondhalf\": \"2nd Half\",\n" + 
				"            \"goals\": \"Goals\",\n" + 
				"            \"outcome\": \"Spielausgang\"\n" + 
				"        },\"" + wpHeimmannschaftID + "\": { \"goals\": \"" +toreHeim + "\",\n" + 
						"            \"outcome\": [\n" + 
						"                \""+getHeimWinLoss()+"\"\n" + 
						"            ] "
						
				
				
				+ "}, \"" +wpGastmannschaftID +"\": {\"goals\": \"" +  toreGast + "\",\n" + 
						"            \"outcome\": [\n" + 
						"                \""+getGastWinLoss()+ "\"\n" + 
						"            ]}}";
				
		}
//				"results": {
//            "0": {
//                "firsthalf": "1st Half",
//                "secondhalf": "2nd Half",
//                "goals": "Goals",
//                "outcome": "Spielausgang"
//            },
//            "1576": {
//                "goals": "24",
//                "outcome": [
//                    "loss"
//                ]
//            },
//            "43276": {
//                "goals": "25",
//                "outcome": [
//                    "win"
//                ]
//            }
	
		json += ",\"status\": \"future\"}";
		
		
		
		
		return json;
		
		
	}
	
	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public String getZeit() {
		return zeit;
	}

	public void setZeit(String zeit) {
		this.zeit = zeit;
	}

	public String getHallennummer() {
		return hallennummer;
	}

	public void setHallennummer(String hallennummer) {
		this.hallennummer = hallennummer;
	}

	public String getSpielnummer() {
		return spielnummer;
	}

	public void setSpielnummer(String spielnummer) {
		this.spielnummer = spielnummer;
	}

	public String getHeimmannschaft() {
		return heimmannschaft;
	}

	public void setHeimmannschaft(String heimmannschaft) {
		this.heimmannschaft = heimmannschaft;
	}
}
