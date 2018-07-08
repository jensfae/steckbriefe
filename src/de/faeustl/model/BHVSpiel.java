package de.faeustl.model;

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
	
	@CsvBindByName (column = "Liga")
	private String liga;
	
	
	public String getWPNummer() {
		try {
		return LigaEnum.valueOf(liga.replace(" ", "")).getWpNummer();
		}
		catch (Exception exp)
		{
			return null;
		}
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
