package de.faeustl.main;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.ICSVParser;

import de.faeustl.model.Steckbrief;
import de.faeustl.wp.Search;
import de.faeustl.wp.Writer;

public class SteckbriefeImport {

	private static final String SAMPLE_CSV_FILE_PATH = "./Steckbrief2.csv";

    public static void main(String[] args) throws IOException {
        try (
            Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
        		
        		 
        		
        		
        		CSVReader csvReader = new CSVReaderBuilder(reader)
            		.withCSVParser(new CSVParserBuilder().withSeparator(';').build())
            		.withSkipLines(1)
            		
            		.build();
        ) {
        	
            // Reading Records One by One in a String array
        	List<String[]> records = csvReader
        			
        			.readAll();
        	
        	List<Steckbrief> lSteckbrief = new ArrayList<>();
        	
        	for (String[] record : records) {
        		
        		Steckbrief steckbrief = new Steckbrief(record[2], record[3], record[4], record[5], record[6], record[7], 
        				record[8], record[9], record[10], record[11], record[12], record[13]);
        		lSteckbrief.add(steckbrief);
        		System.out.println (steckbrief.vorname + " " + steckbrief.nachname);

        	}
        	
        	for (Steckbrief steckbrief: lSteckbrief) {
        		
        		steckbrief.wpID = Search.searchPlayer(steckbrief.getWPTitle());
        		if (steckbrief.wpID != "")
        			System.out.print("ID:" + steckbrief.wpID + "    ");
        			System.out.println(steckbrief.getWPSteckbrieJSON());
        			Writer.updatePlayersSteckbrief(steckbrief.wpID, steckbrief.getWPSteckbrieJSON());
        	}
        	
        }
    }
    
	public static String getJSONString(Steckbrief steckbrief) {
		ObjectMapper mapper = new ObjectMapper();
		
		String jsonInString = null;
		try {
			jsonInString = mapper.writeValueAsString(steckbrief);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(jsonInString);
		
		return jsonInString;
		
		
	}

}
