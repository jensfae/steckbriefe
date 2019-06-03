package de.faeustl.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


public class DHBTabellenPlatz {
	
	public String wpID;
	
	@XmlElement(name="Nr")
	public String position;
	
	@XmlElement(name="Verein")
	public String mannschaft;
	
	@XmlElement(name="Spiele")
	public String spiele;
//	
	@XmlElement(name="Gewonnen")
	public String gewonnen;
//	
	@XmlElement(name="Unentschieden")
	public String unentschieden;
//	
	@XmlElement(name="Verloren")
	public String verloren;
//	
	@XmlElement(name="TorePlus")
	public String torePlus;
	
	@XmlElement(name="ToreMinus")
	public String toreMinus;
	
	@XmlElement(name="D")
	public String toreDiff;
	
	@XmlElement(name="PunktePlus")
	public String punktePlus;
	
	@XmlElement(name="PunkteMinus")
	public String punkteMinus;

	@XmlElement(name="VereinsNr")
	public String vereinsNr;
	


	
public String getJson() {
		
		String json;
		json = "\""+ wpID +"\": {\n" + 
				"                \"sp\": \""+spiele+"\",\n" + 
				"                \"pts\": \"" +  punktePlus   +  "\",\n" + 
////				"                \"pts\": \"0\",\n" + 
				"                \"w\": \""+ gewonnen+"\",\n" + 
				"                \"d\": \""+ unentschieden+"\",\n" + 
				"                \"l\": \""+ verloren+"\",\n" + 
				"                \"f\": \""+ torePlus+"\",\n" + 
				"                \"a\": \""+ toreMinus +"\",\n" + 
				"                \"diff\": \""+ toreDiff+"\"\n" + 
////				"                \"name\": \"TSV Haunstetten\",\n" + 
////				"                \"pos\": 5\n" + 
				"            }";
		
		
		return json;
	}

}
