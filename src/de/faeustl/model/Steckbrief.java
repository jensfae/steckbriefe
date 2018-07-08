package de.faeustl.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Steckbrief {

	public String nachname;
	public String vorname;
	public String position;
	public String alter;
	public String groeße;
	public String nationalitaet;
	public String erfolge;
	public String beim_esv;
	public String bisherige_vereine;
	public String beruf;
	public String hobbys;
	public String persoenliche_ziele;
	public String lebensmotto;
	public String wpID;

	public  void out() {
		System.out.println("ID : " + wpID);
		System.out.println("Name : " + nachname);
		System.out.println("Vorname : " + vorname);
		System.out.println("Position : " + position);
		System.out.println("Nationalität : " + nationalitaet);
		System.out.println("==========================");
		
	}

	public Steckbrief(String pNachname, String pVorname, String pPosition, String pAlter, String pGroesse,
			String pNationalität, String pErfolge, String pBeim_ESV, String pBisherige_Vereine, String pBeruf,
			String pHobbys, String pPersoenliche_Ziele, String pLebensmott) {
		
		this.nachname = pNachname;
		this.vorname = pVorname;
		this.position = pPosition;
		this.alter = pAlter;
		this.groeße = pGroesse;
		this.nationalitaet = pNationalität;
		this.erfolge = pErfolge;
		this.beim_esv = pBeim_ESV;
		this.bisherige_vereine = pBisherige_Vereine;
		this.beruf = pBeruf;
		this.hobbys = pHobbys;
		this.persoenliche_ziele = pPersoenliche_Ziele;
		this.lebensmotto = pLebensmott;

	}
	
	public String getWPTitle()
	{
		return vorname + " " + nachname;
	}

	public String getWPSteckbrieJSON(){
		String steckbrief = null;
		steckbrief = "{\"fields\":{";
		steckbrief += "\"beim_esv_seit\":\"" + beim_esv.replaceAll("\n", ", ") + "\",";
		steckbrief += "\"position\":\"" + position.replaceAll("\n", ", ") + "\",";
		steckbrief += "\"alter\":\"" + alter.replaceAll("\n", ", ") + "\",";
		steckbrief += "\"groeße_in_cm\":\"" + groeße.replaceAll("\n", ", ") + "\",";
		steckbrief += "\"nationalitaet\":\"" + nationalitaet.replaceAll("\n", ", ") + "\",";
		steckbrief += "\"erfolge\":\"" + erfolge.replaceAll("\n", ", ") + "\",";
		steckbrief += "\"bisherige_vereine\":\"" + bisherige_vereine.replaceAll("\n", ", ") + "\",";
		steckbrief += "\"beruf\":\"" + beruf.replaceAll("\n", ", ") + "\",";
		steckbrief += "\"hobbys\":\"" + hobbys.replaceAll("\n", ", ") + "\",";
		steckbrief += "\"saisonziel\":\"" + persoenliche_ziele.replaceAll("\n", ", ") + "\",";
		steckbrief += "\"lebensmotto\":\"" + lebensmotto.replaceAll("\n", ", ") + "\"";
		steckbrief += "}}";
		return steckbrief;
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getAlter() {
		return alter;
	}

	public void setAlter(String alter) {
		this.alter = alter;
	}

	public String getGroeße() {
		return groeße;
	}

	public void setGroeße(String groeße) {
		this.groeße = groeße;
	}

	public String getNationalitaet() {
		return nationalitaet;
	}

	public void setNationalitaet(String nationalitaet) {
		this.nationalitaet = nationalitaet;
	}

	public String getErfolge() {
		return erfolge;
	}

	public void setErfolge(String erfolge) {
		this.erfolge = erfolge;
	}

	public String getBeim_esv() {
		return beim_esv;
	}

	public void setBeim_esv(String beim_esv) {
		this.beim_esv = beim_esv;
	}

	public String getBisherige_vereine() {
		return bisherige_vereine;
	}

	public void setBisherige_vereine(String bisherige_vereine) {
		this.bisherige_vereine = bisherige_vereine;
	}

	public String getBeruf() {
		return beruf;
	}

	public void setBeruf(String beruf) {
		this.beruf = beruf;
	}

	public String getHobbys() {
		return hobbys;
	}

	public void setHobbys(String hobbys) {
		this.hobbys = hobbys;
	}

	public String getPersoenliche_erfolge() {
		return persoenliche_ziele;
	}

	public void setPersoenliche_erfolge(String persoenliche_erfolge) {
		this.persoenliche_ziele = persoenliche_erfolge;
	}

	public String getLebensmotto() {
		return lebensmotto;
	}

	public void setLebensmotto(String lebensmotto) {
		this.lebensmotto = lebensmotto;
	}

	public String getWpID() {
		return wpID;
	}

	public void setWpID(String wpID) {
		this.wpID = wpID;
	}
}
