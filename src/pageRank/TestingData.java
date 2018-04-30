package pageRank;

import java.util.LinkedHashMap;

public class TestingData {

	LinkedHashMap<String,PageNode> pages = new LinkedHashMap<>();
	
	public TestingData() {
		
		String name;
		for(int i=1; i<6; i++) {
			name = "Page"+i;
			this.pages.put(name,new PageNode(name));
		}
		
		this.pages.get("Page1").in.add(this.pages.get("Page3"));
		this.pages.get("Page1").out.put(this.pages.get("Page2"), 0.0);
		this.pages.get("Page1").out.put(this.pages.get("Page4"),0.0);
		this.pages.get("Page2").in.add(this.pages.get("Page1"));
		this.pages.get("Page2").in.add(this.pages.get("Page3"));
		this.pages.get("Page2").out.put(this.pages.get("Page3"),0.0);
		this.pages.get("Page2").out.put(this.pages.get("Page5"),0.0);
		this.pages.get("Page3").in.add(this.pages.get("Page2"));
		this.pages.get("Page3").in.add(this.pages.get("Page4"));
		this.pages.get("Page3").out.put(this.pages.get("Page1"),0.0);
		this.pages.get("Page3").out.put(this.pages.get("Page2"),0.0);
		this.pages.get("Page3").out.put(this.pages.get("Page5"),0.0);
		this.pages.get("Page4").in.add(this.pages.get("Page1"));
		this.pages.get("Page4").in.add(this.pages.get("Page5"));
		this.pages.get("Page4").out.put(this.pages.get("Page3"),0.0);
		this.pages.get("Page5").in.add(this.pages.get("Page2"));
		this.pages.get("Page5").in.add(this.pages.get("Page3"));
		this.pages.get("Page5").out.put(this.pages.get("Page4"),0.0);
	}
}
