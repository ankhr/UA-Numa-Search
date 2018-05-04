package pageRank;

import java.util.LinkedHashMap;
import java.util.Map;

public class PageRank {

	public static void main(String[] args) {
		
		int counter = 0;
		Boolean changes;
		Double newWeight;
		Double[] deadWeight = {0.0, 0.0};
		PageNode page;
		
		//create map
		TestingData sample = new TestingData();
		LinkedHashMap<String,PageNode> pages = sample.pages;
		
		//initialize weights
		Double initialWeight = 1.0/pages.size();
		for(Map.Entry<String,PageNode> entry : pages.entrySet())
			entry.getValue().weight = initialWeight;
		
		//initialize edge weights
		for(Map.Entry<String,PageNode> entry : pages.entrySet()) {
			for(Map.Entry<PageNode,Double> link : entry.getValue().out.entrySet())
				link.setValue(entry.getValue().weight/entry.getValue().out.size());
			if(entry.getValue().out.size() == 0)
				deadWeight[0] += entry.getValue().weight;
		}
		
		//page rank
		do {
			counter++;
			changes = false;
			deadWeight[1] = deadWeight[0] / pages.size();
			deadWeight[0] = 0.0;
			//calculate new node weights
			for(Map.Entry<String,PageNode> entry : pages.entrySet()) {
				newWeight = 0.0;
				page = entry.getValue();
				for(PageNode link : page.in)
					newWeight += pages.get(link.name).out.get(page);
				newWeight += deadWeight[1];
				if(page.out.size() == 0)
					deadWeight[0] += newWeight;
				if(Double.compare(newWeight, page.weight) != 0)
					changes = true;
				page.weight = newWeight;
			}
			//calculate new edge weights
			for(Map.Entry<String,PageNode> entry : pages.entrySet()) {
				page = entry.getValue();
				for(Map.Entry<PageNode,Double> link : page.out.entrySet())
					link.setValue(page.weight/page.out.size());
			}
		} while(changes && counter < 50000);
		
		//testing
		Double total = 0.0;
		Double value;
		for(Map.Entry<String,PageNode> entry : pages.entrySet()) {
			value = entry.getValue().weight;
			System.out.println(entry.getKey() + ": " + value);
			total += value;
		}
		System.out.println();
		System.out.println("Total: " + total);
		System.out.println("Counter: " + counter);
		
	}

}
