package de.faeustl.model;

public class TabellenPlatz {

	public String wpID;
	public Integer position;
	public String mannschaft;
	public Integer spiele;
	public String gewonnen;
	public String unentschieden;
	public String verloren;
	public int torePlus;
	public int toreMinus;
	
	public int punktePlus;
	public String punkteMinus;
	public String vereinsNr;
	
public String getJson() {
		
		String json;
		json = "\""+ wpID +"\": {\n" + 
				"                \"sp\": \""+spiele+"\",\n" + 
				"                \"pts\": \"" +  punktePlus   +  "\",\n" + 
				"                \"w\": \""+ gewonnen+"\",\n" + 
				"                \"d\": \""+ unentschieden+"\",\n" + 
				"                \"l\": \""+ verloren+"\",\n" + 
				"                \"f\": \""+ torePlus+"\",\n" + 
				"                \"a\": \""+ toreMinus +"\",\n" + 
				"                \"diff\": \""+ getToreDiff () +"\"\n" + 

				"            }";
		
		
		return json;
	}

public int getToreDiff () {
	return torePlus - toreMinus;
}

}
