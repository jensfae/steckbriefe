package de.faeustl.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import de.faeustl.model.BHVSpiel;
import de.faeustl.wp.Search;

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
					"https://bhv-handball.liga.nu/cgi-bin/WebObjects/nuLigaDokumentHBDE.woa/wa/nuDokument?dokument=RegionMeetingsFOP&championship=OS+2018%2F19")
							.openConnection();

		StringBuilder result = new StringBuilder();
		conn.setRequestMethod("GET");
		BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));


        @SuppressWarnings("rawtypes")
		CsvToBean<BHVSpiel> csvToBean = new CsvToBeanBuilder(rd)
                .withType(BHVSpiel.class)
                .withIgnoreLeadingWhiteSpace(true)
                .withSeparator(';')
                .build();

        Iterator<BHVSpiel> csvUserIterator = csvToBean.iterator();

        while (csvUserIterator.hasNext()) {
        	BHVSpiel spiel = csvUserIterator.next();
        		if (spiel.getWPNummer() != null) {
        		System.out.println(Search.searchTeam(spiel.getHeimmannschaft().replace(" ", "-").replace("I", "").replace("/", "").trim()));
        		System.out.println(spiel.getWPNummer());
        		}
//            System.out.println("Datum : " + spiel.getDatum());
//            System.out.println("Zeit : " + spiel.getZeit());
//            System.out.println("Spielnummer : " + spiel.getSpielnummer());
//            System.out.println("Hallennummer : " + spiel.getHallennummer());
//            System.out.println("Heimverein : " + spiel.getHeimmannschaft());
//            System.out.println("Liga : " + spiel.getWPNummer());
//            System.out.println("==========================");
        }
   
        rd.close();
		
		
		//System.out.println(result.toString());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
