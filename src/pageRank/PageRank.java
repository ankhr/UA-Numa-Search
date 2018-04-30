package pageRank;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class PageRank {

	public static void main(String[] args) {
		
		//create map
		TestingData sample = new TestingData();
		LinkedHashMap<String,PageNode> pages = sample.pages;
		
		//calculate initial weight
		Double initialWeight = 1.0/pages.size();
		
		//initialize weights
		for(Map.Entry<String,PageNode> page : pages.entrySet()) {
			page.getValue().weight = initialWeight;
		}
		
		int counter = 0;
		int changes = 0;
		Double newWeight;
		
		//page rank
		do {
			counter++;
			changes = 0;
			for(Map.Entry<String,PageNode> page : pages.entrySet()) {
				for(Map.Entry<PageNode,Double> link : page.getValue().out.entrySet()) {
					link.setValue(page.getValue().weight/page.getValue().out.size());
				}
			}
			for(Map.Entry<String,PageNode> page : pages.entrySet()) {
				newWeight = 0.0;
				for(PageNode link : page.getValue().in) {
					newWeight = newWeight + pages.get(link.name).out.get(page.getValue());
				}
				if(Double.compare(newWeight, page.getValue().weight) != 0) {
					changes++;
				}
				page.getValue().weight = newWeight;
			}
		} while(changes != 0 && counter < 50000);
		
		// Display elements
		for(Map.Entry<String,PageNode> page : pages.entrySet()) {
			System.out.println(page.getKey()+": "+page.getValue().weight);
		}
		
	}

}
