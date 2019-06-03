package de.faeustl.model;

public class Mannschaft {
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWpID() {
		return wpID;
	}
	public void setWpID(String wpID) {
		this.wpID = wpID;
	}
	public int getSpiele() {
		return spiele;
	}
	public void setSpiele(int spiele) {
		this.spiele = this.spiele + spiele;
	}
	public int getGewonnen() {
		return gewonnen;
	}
	public void setGewonnen(int gewonnen) {
		this.gewonnen = gewonnen + this.gewonnen;
	}
	public int getVerloren() {
		return verloren;
	}
	public void setVerloren(int verloren) {
		this.verloren = verloren +this.verloren;
	}
	public int getUnentschieden() {
		return unentschieden;
	}
	public void setUnentschieden(int unentschieden) {
		this.unentschieden = this.unentschieden + unentschieden;
	}
	
	public int getPunkte()
	{
		return 2*gewonnen + unentschieden;
	}
	
	public int getTorePlus() {
		return torePlus;
	}
	public void setTorePlus(int torePlus) {
		this.torePlus += torePlus;
	}
	public int getToreMinus() {
		return toreMinus;
	}
	public void setToreMinus(int toreMinus) {
		this.toreMinus += toreMinus;
	}
	
	public int getDiffTore()
	{
		return torePlus - toreMinus;
	}
	
	
	private String name;
	private String wpID;
	private int spiele;
	private int gewonnen;
	private int verloren;
	private int unentschieden;
	private int torePlus;
	private int toreMinus;
	
	public String getJson() {
		
		String json = "";
		if (getWpID() != null) {
		json = "\""+getWpID() +"\": {\n" + 
				"                \"sp\": \""+getSpiele()+"\",\n" + 
				"                \"pts\": \"" +  getPunkte()   +  "\",\n" + 
//				"                \"pts\": \"0\",\n" + 
				"                \"w\": \""+ getGewonnen()+"\",\n" + 
				"                \"d\": \""+ getUnentschieden()+"\",\n" + 
				"                \"l\": \""+ getVerloren()+"\",\n" + 
				"                \"f\": \""+ getTorePlus()+"\",\n" + 
				"                \"a\": \""+ getToreMinus() +"\",\n" + 
				"                \"diff\": \""+getDiffTore()+"\"\n" + 
//				"                \"name\": \"TSV Haunstetten\",\n" + 
//				"                \"pos\": 5\n" + 
				"            }";
		}
		
		return json;
	}





	

}
