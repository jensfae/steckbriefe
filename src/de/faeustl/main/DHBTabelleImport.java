package de.faeustl.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import de.faeustl.model.DHBTabelle;
import de.faeustl.model.DHBTabellenPlatz;
import de.faeustl.wp.Search;

public class DHBTabelleImport {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			DHBTabelleImport.getDHBData();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void getDHBData() throws JAXBException {
		HttpURLConnection conn;
		try {
			conn = (HttpURLConnection) new URL(

					"https://www.sis-handball.de/xmlexport/xml_dyn.aspx?user=1520401138&pass=979840&art=4&auf=001519418000000000000000000000000027000")
							.openConnection();

			conn.setRequestMethod("GET");
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			JAXBContext context = JAXBContext.newInstance(DHBTabelle.class);
			Unmarshaller un = context.createUnmarshaller();
			
			DHBTabelle dhbTabellenData = (DHBTabelle)un.unmarshal(rd);
			List<DHBTabellenPlatz> dhbMannschft = dhbTabellenData.getPlaetze();
			
			String json = "\"data\": {\n" + 
					"        \"0\": {\n" + 
					"            \"pos\": \"Pos.\",\n" + 
					"            \"name\": \"Mannschaft\",\n" + 
					"            \"ptstwo\": \"Pts-\",\n" + 
					"            \"pts\": \"Pkt\",\n" + 
					"            \"sp\": \"Sp\",\n" + 
					"            \"diff\": \"DIFF\",\n" + 
					"            \"w\": \"W\",\n" + 
					"            \"d\": \"D\",\n" + 
					"            \"l\": \"L\",\n" + 
					"            \"f\": \"F\",\n" + 
					"            \"a\": \"A\"\n" + 
					"        }";
			for (DHBTabellenPlatz mannschaft: dhbMannschft) {
				
				String wpNummer = Search.searchTeamByDHBNumber(mannschaft.vereinsNr);
				mannschaft.wpID=wpNummer;
				
				
				json += ", " + mannschaft.getJson()  ;
				
				
				
			}
			json += "}";
			System.out.println(json);
			System.out.print("fertig");
		}
		catch (Exception exc)
		{
			exc.printStackTrace();
		}
	}

}
