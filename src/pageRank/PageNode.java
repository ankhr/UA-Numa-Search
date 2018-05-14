package pageRank;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class PageNode {
	
	String name;
	Double weight;
	ArrayList<PageNode> in = new ArrayList<>();
	LinkedHashMap<PageNode,Double> out = new LinkedHashMap<PageNode,Double>();
	double[] vector;
	
	public PageNode(String name) {
		this.name = name;
	}
	public double[] getVecLoc(){
		return this.vector;
	}

	@Override
	public String toString() {
		return name;
	}
}
