package de.faeustl.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="TabelleGesamtMitAK")

public class DHBTabelle {

	  private List<DHBTabellenPlatz> plaetze = new ArrayList <DHBTabellenPlatz>() ;
	  
	  @XmlElement(name="Platzierung")
	  
	  public List<DHBTabellenPlatz> getPlaetze() {
	      
		  if (this.plaetze == null) {
		       this.plaetze =  new LinkedList<DHBTabellenPlatz>();
		    }
		  return plaetze;
	  }
	  
	  public void setSpiele(List<DHBTabellenPlatz> platzList) {
	      this.plaetze = platzList;
	  }
	
}
