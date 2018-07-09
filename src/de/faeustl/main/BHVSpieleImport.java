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

					"https://bhv-handball.liga.nu/cgi-bin/WebObjects/nuLigaDokumentHBDE.woa/wa/nuDokument?dokument=RegionMeetingsFOP&championship=BHV+2018%2F19")
							.openConnection();

			conn.setRequestMethod("GET");
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			@SuppressWarnings("rawtypes")
			CsvToBean<BHVSpiel> csvToBean = new CsvToBeanBuilder(rd).withType(BHVSpiel.class)
					.withIgnoreLeadingWhiteSpace(true).withSeparator(';').build();

			Iterator<BHVSpiel> csvUserIterator = csvToBean.iterator();
			HashMap<String, String> mannschaften = new HashMap<>();

			while (csvUserIterator.hasNext()) {
				BHVSpiel spiel = csvUserIterator.next();
				if (spiel.getWPNummer() != null) {
					spiel.wpHeimmannschaftID=createMannschaft(mannschaften, spiel.getHeimmannschaft());
					spiel.wpGastmannschaftID=createMannschaft(mannschaften, spiel.getGastmannschaft());
					System.out.println(spiel.getJSON());
					
					String wpID = Search.searchSpiel(spiel.getSpielnummer());
					if (wpID != null) {
						Writer.update(wpID, spiel.getJSON());
					}
					else Writer.createSpiel(spiel.getJSON());

				}
			}
			for (String name : mannschaften.keySet()) {
				System.out.println(name + mannschaften.get(name).toString());
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

	private String createMannschaft(HashMap<String, String> mannschaften, String pMannschaftsname) {
		JsonObject jsonTeam = null;
		String slug = pMannschaftsname.replace("I", "").replace("/", "").replace(".", "-").replace("ü", "ue").trim();
		slug=slug.replace(" ", "-");
		
		String lastChar = slug.substring(slug.length()-1);
		
		if (lastChar.equals(".") || lastChar.equals("-"))
			slug = slug.substring(0,slug.length()-1);
		
		if (!mannschaften.containsKey(slug.toLowerCase())) {
			 jsonTeam = Search.searchTeam(slug);

			if (jsonTeam != null) {
				System.out.println("Mannschaft hinzugefügt: " + pMannschaftsname);
				mannschaften.put(jsonTeam.get("slug").getAsString(), jsonTeam.get("id").getAsString());
			}
			else {
				jsonTeam = Writer.createTeam(pMannschaftsname, slug);
				mannschaften.put(jsonTeam.get("slug").getAsString(), jsonTeam.get("id").getAsString());
			}
			return jsonTeam.get("id").getAsString();
		}
		return mannschaften.get(slug.toLowerCase()).toString();
	}
}
