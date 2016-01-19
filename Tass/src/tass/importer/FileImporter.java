package tass.importer;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.opencsv.CSVReader;

public class FileImporter {

	public Map<String, Double> getMagazineMap() throws IOException{
		Map<String, Double> magazines = new HashMap<String, Double>();
		
		String filePath = "src/tass/resources/average_IEEE.csv";
		CSVReader reader = null;

		reader = new CSVReader(new FileReader(filePath));
		String[] line = reader.readNext();
		while(line!=null){
				magazines.put(line[0].toLowerCase(), Double.parseDouble(line[1]));
				line = reader.readNext();
		}
		reader.close();

		return magazines;
	}
	
}
