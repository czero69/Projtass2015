package tass.importer;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;

import tass.data.Magazine;

public class FileImporter {

	public List<Magazine> getMagazineList() throws IOException{
		List<Magazine> magazines = new ArrayList<Magazine>();
		
		//import magazines info from file
		String filePath = "src/tass/resources/average_IEEE.csv";
		CSVReader reader = null;

		reader = new CSVReader(new FileReader(filePath));
		String[] line = reader.readNext();
		while(line!=null){
				magazines.add(new Magazine(line[0], Double.parseDouble(line[1])));
				line = reader.readNext();
		}
		reader.close();

		return magazines;
	}
	
}
