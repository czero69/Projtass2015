package tass.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import tass.data.Article;
import tass.data.Magazine;
import tass.data.Row;
import tass.importer.FileImporter;
import tass.importer.WebImporter;
import tass.view.View;

public class Controller{

	private View view;
	private Thread guiThread;
	private List<Magazine> magazineList;
	private WebImporter webImporter = new WebImporter();
	
	public void start() throws IOException{
		magazineList = (new FileImporter()).getMagazineList();
		guiThread = new Thread(view);
		guiThread.start();
		view.setController(this);
	}
	
	public void setView(View view) {
		this.view = view;
	}
	
	public List<Row> getDataForValue(String userInput){
		List<Row> rowData = new ArrayList<Row>();
		List<String> keyWords = getListOfWords(userInput);
		List<Article> articleList = webImporter.getArticleNamesForWords(keyWords);
		
		//connect articles with magazines (on magazine name) to create rows (articleName, magazineName, impactFactor)
		
		//test - replace with real implelentation
		Row testRow = new Row();
		testRow.setArticleName("test name");
		testRow.setMagazineName(magazineList.get(0).getMagazineName());
		testRow.setImpactFactor(magazineList.get(0).getImpactFactor());
		rowData.add(testRow);
		//end test
		
		return rowData;
	}
	
	private List<String> getListOfWords(String userInput){
		List<String> keyWords = new ArrayList<String>();
		
		//get keyWords form user input
		
		return keyWords;
	}
	

}
