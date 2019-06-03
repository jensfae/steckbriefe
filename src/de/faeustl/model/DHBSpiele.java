package de.faeustl.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="AlleSpiele")

public class DHBSpiele {

	private List<DHBSpiel> spiele;
	  
	
	  public List<DHBSpiel> getSpiele() {
	      return spiele;
	  }
	  @XmlElement(name="Spiel")
	  public void setSpiele(List<DHBSpiel> spieleList) {
	      this.spiele = spieleList;
	  }
	
}
