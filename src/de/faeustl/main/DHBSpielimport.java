package de.faeustl.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import de.faeustl.model.DHBSpiel;
import de.faeustl.model.DHBSpiele;
import de.faeustl.wp.Search;
import de.faeustl.wp.Writer;

public class DHBSpielimport {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			DHBSpielimport.getDHBData();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void getDHBData() throws JAXBException {
		HttpURLConnection conn;
		try {
			conn = (HttpURLConnection) new URL(

					"https://www.sis-handball.de/xmlexport/xml_dyn.aspx?user=1520401138&pass=979840&art=1&auf=001519418000000000000000000000000027000")
							.openConnection();

			conn.setRequestMethod("GET");
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			JAXBContext context = JAXBContext.newInstance(DHBSpiele.class);
			Unmarshaller un = context.createUnmarshaller();
			
			DHBSpiele dhbSpieleData = (DHBSpiele)un.unmarshal(rd);
			List<DHBSpiel> dhbSpiele = dhbSpieleData.getSpiele();
		    
			//iterate over object
			
			HashMap<String, String> mannschaften = new HashMap<>();
			HashMap<String, String> venues = new HashMap<>();	
			
			for(DHBSpiel spiel: dhbSpiele){
				
				if (mannschaften.containsKey(spiel.getHeimNr()))
				{
					spiel.wpHeimNummer = mannschaften.get(spiel.getHeimNr()).toString();
				}
				else
				{
					spiel.wpHeimNummer = Search.searchTeamByDHBNummer(spiel.getHeimNr()).get("id").getAsString();
					mannschaften.put(spiel.getHeimNr(), spiel.wpHeimNummer);
				}
				if (mannschaften.containsKey(spiel.getGastNr()))
				{
					spiel.wpGastNummer = mannschaften.get(spiel.getGastNr()).toString();
				}
				else
				{
					spiel.wpGastNummer = Search.searchTeamByDHBNummer(spiel.getGastNr()).get("id").getAsString();
					mannschaften.put(spiel.getGastNr(), spiel.wpGastNummer);
				}
				
				if (venues.containsKey(spiel.getHalle()))
				{
					spiel.wpHallennummer=venues.get(spiel.getHalle()).toString();
				}
				else {
					try {
					spiel.wpHallennummer = Search.findVenueByDHBNummer(spiel.getHalle()).get("id").getAsString();
					venues.put(spiel.getHalle(), spiel.wpHallennummer);
					}
					catch (Exception exc)
					{
						System.out.println("Halle: " + spiel.getHalle() + "Mannschaft: " + spiel.getHeim());
					}
				}
				
				
				String wpID = Search.searchSpiel(spiel.getLiga());
				if (wpID != null) {
					Writer.update(wpID, spiel.getJSON());
				}
//				Writer.createSpiel(spiel.getJSON());
				System.out.println(spiel.getJSON());
//				System.out.println("Heim : "+spiel.getHeim());
//				System.out.println("--------------------------");
		    }
			for (String name : venues.keySet()) {
				System.out.println(name +" "+ venues.get(name).toString());
			}
			
			rd.close();

			// System.out.println(result.toString());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
