package pageRank;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class testBook {

    public static void main(String[] args) {

        try {
            // Path to parsed page files
            Path inDir = FileSystems.getDefault().getPath(System.getProperty("user.dir") + "/src/parse/output/AA/");
            DirectoryStream<Path> stream = Files.newDirectoryStream(inDir);
            
            boolean pageNameTracker = false;
            String pageName = "";
            
            // <PageName, WordFrequency>
            Map<String, Map<String, Integer>> a = new LinkedHashMap<>(); // need better name
            
            // Process all page files
            for (Path file : stream) {
                InputStream in = Files.newInputStream(file);
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                
                String line;
                List<String[]> words = new ArrayList<>();
                while ((line = br.readLine()) != null) {
                    if (!pageNameTracker) {
                        pageName = br.readLine();
                        pageNameTracker = true;
                    }
                    
                    // Strip everything but words
                    line = Jsoup.clean(line, Whitelist.simpleText());
                    if (!line.equalsIgnoreCase("")) {
                            String[] wordLine = line.split("[ 0123456789.,:;!?—()/\"]+");
                            words.add(wordLine);
                    }
                }
                
                // Find word frequency
                Map<String, Integer> frequencies = new LinkedHashMap<>();
                for (String[] wordArray : words) {
                    for (String word : wordArray) {
                        if (!word.isEmpty()) {
                            Integer frequency = frequencies.get(word);
                            if (frequency == null) {
                                frequency = 0;
                            }
                            ++frequency;
                            frequencies.put(word, frequency);
                        }
                    }
                }
                a.put(pageName, frequencies);
                pageNameTracker = false;
                br.close();
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void euclideanDistance() {
        // TODO
    }
}
