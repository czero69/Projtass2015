package tass.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import tass.data.Article;
import tass.data.Row;
import tass.importer.FileImporter;
import tass.importer.WebImporter;
import tass.view.View;

public class Controller{

	private View view;
	private Thread guiThread;
	private Map<String, Double> magazineMap;
	private WebImporter webImporter = new WebImporter();
	
	private String year1;
	private String year2;
	
	public void start() throws IOException{
		magazineMap = (new FileImporter()).getMagazineMap();
		guiThread = new Thread(view);
		guiThread.start();
		view.setController(this);
	}
	
	public void setView(View view) {
		this.view = view;
	}
	
	public void setYears(String year1, String year2) {
		this.year1 = year1;
		this.year2 = year2;
	}
	
	public List<Row> getDataForValue(String userInput){
		List<Row> rowData = new ArrayList<Row>();
		List<Article> articleList = webImporter.getArticlesForWords(userInput, year1, year2);
		
		for(Article article : articleList){
			Row testRow = new Row();
			testRow.setArticleName(article.getArticleName());
			testRow.setMagazineName(article.getMagazineName());
			String searchString = article.getMagazineName().toLowerCase().replace("&", "and");
			if(magazineMap.containsKey(searchString)){
				testRow.setImpactFactor(magazineMap.get(searchString));
			} else {
				testRow.setImpactFactor(-1);
			}
			rowData.add(testRow);
		}
		
		return rowData;
	}
	
}
