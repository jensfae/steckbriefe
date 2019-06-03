package de.faeustl.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;

import com.google.gson.JsonObject;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import de.faeustl.model.BHVSpiel;
import de.faeustl.model.Mannschaft;
import de.faeustl.wp.Search;
import de.faeustl.wp.Writer;

public class BHVSpieleImport {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new BHVSpieleImport().getBHVData();
	}

	@SuppressWarnings("unchecked")
	public void getBHVData() {
		HttpURLConnection conn;
		try {
			conn = (HttpURLConnection) new URL(

//					"https://bhv-handball.liga.nu/cgi-bin/WebObjects/nuLigaDokumentHBDE.woa/wa/nuDokument?dokument=RegionMeetingsFOP&championship=BHV+2018%2F19")
					 "https://bhv-handball.liga.nu/cgi-bin/WebObjects/nuLigaDokumentHBDE.woa/wa/nuDokument?dokument=RegionMeetingsFOP&championship=OS+2018%2F19")
					 .openConnection();

			conn.setRequestMethod("GET");
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream(),"ISO-8859-15"));

			@SuppressWarnings("rawtypes")
			CsvToBean<BHVSpiel> csvToBean = new CsvToBeanBuilder(rd).withType(BHVSpiel.class)
					.withIgnoreLeadingWhiteSpace(true).withSeparator(';').build();

			Iterator<BHVSpiel> csvUserIterator = csvToBean.iterator();
			HashMap<String, Mannschaft> mannschaften = new HashMap<>();
			HashMap<String, String> venues = new HashMap<>();
			while (csvUserIterator.hasNext()) {
				BHVSpiel spiel = csvUserIterator.next();
				if (spiel.getWPNummer() != null) {
					spiel.wpHeimmannschaftID=createMannschaft(mannschaften, spiel.getHeimmannschaft());
					spiel.wpGastmannschaftID=createMannschaft(mannschaften, spiel.getGastmannschaft());
					
					spiel.setWpHallennummer(findOrCreateVenue(venues, spiel));
					
//					System.out.println(spiel.getJSON());
					
					if (spiel.getErgebnisHeim()>0) {
					
					if (mannschaften.containsKey(spiel.getHeimmannschaft()))
					{
						Mannschaft heimmannschaft = mannschaften.get(spiel.getHeimmannschaft()) ;
						heimmannschaft.setName(spiel.getHeimmannschaft());
						heimmannschaft.setTorePlus(spiel.getErgebnisHeim());
						heimmannschaft.setToreMinus(spiel.getErgebnisGast());
						heimmannschaft.setWpID(spiel.wpHeimmannschaftID);
						heimmannschaft.setSpiele(1);
						if (spiel.getErgebnisHeim()>spiel.getErgebnisGast())
							heimmannschaft.setGewonnen(1);
						else if (spiel.getErgebnisHeim()<spiel.getErgebnisGast())
							heimmannschaft.setVerloren(1);
						else heimmannschaft.setUnentschieden(1);
						
					}
					if (mannschaften.containsKey(spiel.getGastmannschaft()))
					{
						Mannschaft heimmannschaft = mannschaften.get(spiel.getGastmannschaft()) ;
						heimmannschaft.setName(spiel.getGastmannschaft());
						heimmannschaft.setTorePlus(spiel.getErgebnisGast());
						heimmannschaft.setToreMinus(spiel.getErgebnisHeim());
						heimmannschaft.setWpID(spiel.wpGastmannschaftID);
						heimmannschaft.setSpiele(1);
						if (spiel.getErgebnisHeim()<spiel.getErgebnisGast())
							heimmannschaft.setGewonnen(1);
						else if (spiel.getErgebnisHeim()>spiel.getErgebnisGast())
							heimmannschaft.setVerloren(1);
						else heimmannschaft.setUnentschieden(1);
						
					}
					}
					
					System.out.println(spiel.getJSON());
					
					String wpID = Search.searchSpiel(spiel.getSpielnummer());
					if (wpID != null) {
						Writer.update(wpID, spiel.getJSON());
					}
					else Writer.createSpiel(spiel.getJSON());
					
					

				}
			}
			
			int anzahlmannschaften = mannschaften.keySet().size();
			int x = 0;
			String jsonString= "";
			
			for (String name : mannschaften.keySet()) {
				x++;
				jsonString = jsonString + mannschaften.get(name).getJson();
				if (x < anzahlmannschaften) jsonString = jsonString + ",";
				
			}
			System.out.println(jsonString);
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

	

	private String createMannschaft(HashMap<String, Mannschaft> mannschaften, String pMannschaftsname) {
		JsonObject jsonTeam = null;
		String mannschaftsname = pMannschaftsname;
		String slug = pMannschaftsname.replace("II", "").replace("/", "").replace(".", "-").replace("ü", "ue").replace("ß", "ss").trim();
//		mannschaftsname = mannschaftsname.replace("I", "").trim();
		slug=slug.replace(" ", "-");
		
		String lastChar = slug.substring(slug.length()-1);
		
		if (lastChar.equals(".") || lastChar.equals("-"))
			slug = slug.substring(0,slug.length()-1);
		
		if (!mannschaften.containsKey(mannschaftsname)) {
			 jsonTeam = Search.searchTeam(slug);
			 System.out.println(slug);
			if (jsonTeam != null) {
//				System.out.println("Mannschaft hinzugefügt: " + pMannschaftsname)
				
				Mannschaft  mannschaft = new Mannschaft();
				mannschaft.setWpID(jsonTeam.get("id").getAsString());
				
				mannschaften.put(mannschaftsname, mannschaft);
			}
			else {
//				jsonTeam = Writer.createTeam(pMannschaftsname, slug);
				mannschaften.put(mannschaftsname, new Mannschaft());
			}
			return jsonTeam.get("id").getAsString();
		}
		return mannschaften.get(mannschaftsname).getWpID();
	}
	
	private String findOrCreateVenue(HashMap<String, String> venue, BHVSpiel pSpiel) {
		JsonObject jsonVenue = null;
		String slug = pSpiel.getHeimmannschaft().replace("I", "").replace("/", "").replace(".", "-").replace("ü", "ue").trim();
		slug=slug.replace(" ", "-");
		slug=slug.replace("ß", "ss");
		
		String lastChar = slug.substring(slug.length()-1);
		
		if (lastChar.equals(".") || lastChar.equals("-"))
			slug = slug.substring(0,slug.length()-1);
		
		if (!venue.containsKey(slug.toLowerCase())) {
			 
			jsonVenue = Search.findVenue(slug, pSpiel.getHallennummer(), pSpiel.getHeimmannschaft());

			if (jsonVenue != null) {
//				System.out.println("Halle hinzugefügt: " + pSpiel.getHallennummer());
				venue.put(jsonVenue.get("slug").getAsString(), jsonVenue.get("id").getAsString());
			}
			else {
//				jsonVenue = Writer.createTeam(pVenueName, slug);
				try {
					venue.put(jsonVenue.get("slug").getAsString(), jsonVenue.get("id").getAsString());
				}
				catch (Exception exc){
					System.out.println("Halle nicht gefunden: " + pSpiel.getHallennummer());
					jsonVenue = Writer.createVenue(" {\"name\": \""+ pSpiel.getHeimmannschaft() + "\",\"slug\": \""+slug+"\",\"hallennummer_bhv\": \""+ pSpiel.getHallennummer()+ " \"} ");
				}
			}
			return jsonVenue.get("id").getAsString();
		}
		return venue.get(slug.toLowerCase()).toString();
	}
}
