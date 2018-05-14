package pageRank;

import java.io.*;
import java.util.*;

public class UANuma {
    public Annoy annoy;
    public ArrayList<PageNode> data;
    public HashMap<String, PageNode> hashMap;
    private int NUM_TREES = 15;
    private int DEFAULT_K = 300;
    private int DEFAULT_N = 20;
    String d2vFilename;
    
    public UANuma(String d2vFile, String docFilename, String vectorFilename) throws IOException {
        
        this.data = readData(docFilename, vectorFilename);
        this.hashMap = new HashMap<>(data.size());
        for (PageNode datum:data) {
            hashMap.put(datum.name, datum);
        }
        this.annoy = new Annoy(data, NUM_TREES);
        this.d2vFilename = d2vFile;
    }
    
    
    public static ArrayList<PageNode> readData(String articlesFileName, String vectorFileName) throws IOException {
        ArrayList<PageNode> data = new ArrayList<>(3000);
        BufferedReader brArticles = new BufferedReader(new FileReader(articlesFileName));
        BufferedReader brVector = new BufferedReader(new FileReader(vectorFileName));
        String title = "";
        while (((title = brArticles.readLine()) != null)) {
            PageNode pageNode = new PageNode(title);
            pageNode.vector = parseVector(brVector.readLine());
            data.add(pageNode);
        }
        
        brVector.close();
        brArticles.close();
        return data;
    }
    
    private static double[] parseVector(String s) {
        String[] vals = s.split(",");
        double[] vec = new double[vals.length];
        for (int i = 0; i < vals.length; i++) {
            vec[i] = Double.parseDouble(vals[i]);
        }
        return vec;
    }
    
    private static double[] inferVector(String search, String d2vFilename) throws IOException {
        Process p = Runtime
                .getRuntime()
                .exec("py C:/Users/alton/Desktop/Test1/src/parse/infer_vector.py " + d2vFilename + " " + search);
        BufferedReader stdInp = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String output = stdInp.readLine();
        System.out.println(output);
        return parseVector(output);
    }
    
    public List<PageNode> getSearchResults(String search) throws Exception {
        PageNode searchPoint = new PageNode("searchPoint");
        searchPoint.vector = inferVector(search, this.d2vFilename);
        return annoy.closestN(searchPoint, DEFAULT_N, DEFAULT_K);
    }
    
    public List<PageNode> getSimilarPages(PageNode pageNode) throws Exception {
        return annoy.closestN(pageNode, DEFAULT_N, DEFAULT_K);
    }
}