package pageRank;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UANuma {

    public static void main(String[] args) throws Exception {
        ArrayList<PageNode> data = readData(".\\src\\parse\\tinywikivocab.txt", "./src/parse/tinywikivectors.txt");
        Annoy annoy = new Annoy(data, 15);
        PageNode searchPoint = new PageNode("searchpoint");
        searchPoint.vector = inferVector("anti-authoritarian", "./src/parse/tinyd2v.dat");
        List<PageNode> results = annoy.closestN(searchPoint, 20, 300);
        System.out.println(Arrays.toString(results.toArray()));
    }

    public static ArrayList<PageNode> readData(String articlesFileName,String vectorFileName) throws IOException {
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
                .exec("python ./src/parse/infer_vector.py " + d2vFilename + " " + search);
        BufferedReader stdInp = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String output = stdInp.readLine();
        System.out.println(output);
        return parseVector(output);
    }
}
