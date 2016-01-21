package tass.importer;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import tass.data.Article;

public class WebImporter {
	
	public List<Article> getArticlesForWords(String keyWords, String year1, String year2){
		List<Article> articles = new ArrayList<Article>();
		String urlString = generateUrl(keyWords,year1,year2);
		//get articles for words from IEEE database
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        Document document = null;
        try {
        	document = factory.newDocumentBuilder().parse(new URL(urlString).openStream());
        	NodeList documents = document.getElementsByTagName("document");
        	int len = documents.getLength();
        	for(int i = 0; i< len; i++){
        		articles.add(getArticleFromNode(documents.item(i)));
        	}

		} catch (SAXException | IOException | ParserConfigurationException e) {
			e.printStackTrace();
		}
        
        
		return articles;
	}
	
	private Article getArticleFromNode(Node node){
		Article article = new Article();

    	Element element = (Element) node;
    	Node title = element.getElementsByTagName("title").item(0);
    	Node pubTitle = element.getElementsByTagName("pubtitle").item(0);
    	article.setArticleName(title.getTextContent());
    	article.setMagazineName(moveSuffix(pubTitle.getTextContent()));

		return article;
	}
	
	private String moveSuffix(String title){
		
		String pattern = "([^,]*),([^,]*)";
		
		Pattern regexPattern = Pattern.compile(pattern);
		Matcher matcher = regexPattern.matcher(title);
		if(matcher.find()){
			if(matcher.groupCount()>=2)
				return matcher.group(2).trim()+" "+matcher.group(1).trim();
		}
		return title;
	}
	
	private String generateUrl(String keyWords, String year1, String year2){
		String firstPart = "http://ieeexplore.ieee.org/gateway/ipsSearch.jsp?querytext=(";
		String secondPart = ")&pu=IEEE&pys=";
		String thirdPart = "&pye=";
		String fourthPart = "&ctype=Journals&hc=1000";
		String queryString = keyWords.replace(" ", "%20").replace(")", "%29").replace("(", "%28").toUpperCase();
		return firstPart+queryString+secondPart+year1+thirdPart+year2+fourthPart;
	}
	
}
