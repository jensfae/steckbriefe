package de.faeustl.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import com.opencsv.bean.CsvBindByName;

public class BHVSpiel {

	@CsvBindByName (column = "Datum")
    private String datum;
	
	@CsvBindByName (column = "Zeit")
    private String zeit;
	
	@CsvBindByName (column = "Hallennummer")
    private String hallennummer;
	
	@CsvBindByName (column = "Spielnummer")
    private String spielnummer;

	@CsvBindByName (column = "Heimmannschaft")
	private String heimmannschaft;
	
	public String wpHeimmannschaftID;
	
	@CsvBindByName (column = "Gastmannschaft")
	private String gastmannschaft;
	
	public String wpGastmannschaftID;
	
	@CsvBindByName (column = "Liga")
	private String liga;
	
	@CsvBindByName (column = "Staffelkurzbezeichnung")
	private String staffelkurzbezeichnung;

	@CsvBindByName (column = "Ergebnis")
	public String ergebnis;
	
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
		if (getErgebnisHeim()>getErgebnisGast())
		{
			return "win";
		}
		else if (getErgebnisHeim()<getErgebnisGast()) {
			return "loss";
		}
		else return "draw";
	}
	public String getGastWinLoss()
	{
		if (getErgebnisHeim()>getErgebnisGast())
		{
			return "loss";
		}
		else if (getErgebnisHeim()<getErgebnisGast()) {
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
	
	
	public String season = "\"seasons\": [286]";
	
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
		return liga;
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
		DateTimeFormatter formmat1 = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
		
		
		LocalDate ldt = LocalDate.parse(datum, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
		String format = formmat1.format(ldt);
		return format + "T" + zeit +":00";
		
	}
	
	public String getJSON() {
		
		String json = "{ \"leagues\": ["+ getWPNummer().trim() + " ], ";
		json += "\"title\": \"" + getHeimmannschaft() + " vs. " + getGastmannschaft() + "\", ";
		json += "\"date\": \"" +getSpielStart() + "\", ";
		json += "\"teams\": [ " + wpHeimmannschaftID + " , " + wpGastmannschaftID + "], ";
		json += season;
		json += ",\"slug\": \"18-19-" + spielnummer + "\"";
		json += ",\"venues\": [" + wpHallennummer + "]";
		
		
		if (getErgebnisHeim()>0) {
		json += ",\"results\": {\"0\": {\n" + 
				"            \"firsthalf\": \"1st Half\",\n" + 
				"            \"secondhalf\": \"2nd Half\",\n" + 
				"            \"goals\": \"Goals\",\n" + 
				"            \"outcome\": \"Spielausgang\"\n" + 
				"        },\"" + wpHeimmannschaftID + "\": { \"goals\": \"" +getErgebnisHeim() + "\",\n" + 
						"            \"outcome\": [\n" + 
						"                \""+getHeimWinLoss()+"\"\n" + 
						"            ] "
						
				
				
				+ "}, \"" +wpGastmannschaftID +"\": {\"goals\": \"" +  getErgebnisGast() + "\",\n" + 
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
	
	public String getDatum() {
		return datum;
	}

	public void setDatum(String datum) {
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
