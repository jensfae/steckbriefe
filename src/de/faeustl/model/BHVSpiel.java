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
	
	public String season = "\"seasons\": [286]";
	
	
	
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
