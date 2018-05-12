package pageRank;

import org.jsoup.*;
import org.jsoup.safety.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Book {
    
    // <PageName, WordFrequency>
    public Map<String, Map<String, Integer>> book = new LinkedHashMap<>();
    
    Set<String> wordList = new HashSet<>();
    Set<String> stopWords = new HashSet<>();
    
    public Book() {
        initStopWords();
        initBook();
    }
    
    public void initStopWords() {
    
        try {
//            InputStream in = Files.newInputStream(Paths.get(System.getProperty("user.dir") + "/src/stopwords.txt"));
            InputStream in = Files.newInputStream(Paths.get("H:/Coursework/Advanced Algorithms/UA Numa Search/src/stopwords.txt"));
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            
            String line;
            while ((line = br.readLine()) != null) {
                stopWords.add(line);
            }
            
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void initBook() {
        
        try {
            // Path to parsed page files
//            Path inDir = FileSystems.getDefault().getPath(System.getProperty("user.dir") + "/src/parse/output/AA/");
            Path inDir = FileSystems.getDefault().getPath("H:/Coursework/Advanced Algorithms/UA Numa Search/src/parse/output/AA/");
            DirectoryStream<Path> stream = Files.newDirectoryStream(inDir);
    
            boolean pageNameTracker = false;
            String pageName = "";
    
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
                        String[] wordLine = line.split("[ =+!@#$%^&*()~`0123456789.,:;?—/\"]+");
                        words.add(wordLine);
                    }
                }
        
                // Find word frequency
                Map<String, Integer> frequencies = new LinkedHashMap<>();
                for (String[] wordArray : words) {
                    for (String word : wordArray) {
                        if (!word.isEmpty()) {
                            if (!wordList.contains(word) && !stopWords.contains(word)) {
                                wordList.add(word);
                            }
                            Integer frequency = frequencies.get(word);
                            if (frequency == null) {
                                frequency = 0;
                            }
                            ++frequency;
                            frequencies.put(word, frequency);
                        }
                    }
                }
                book.put(pageName.toLowerCase(), frequencies);
                pageNameTracker = false;
                br.close();
            }
            
        }catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println("");
    }
    
    public double euclideanDistance(PageNode page1, PageNode page2) {
        // TODO
        return 0.0;
    }
    
    public void doSomething(PageNode page1, PageNode page2) {
        
        Map<String, Integer> a = book.get(page1.name);
        System.out.println(a);
    
        Map<String, Integer> b = book.get(page2.name);
        System.out.println(b);
    }
    
    
    
    public static class Driver {
    
        public static void main(String[] args) {
            
            Book book = new Book();
            
            PageNode page1 = new PageNode("Anarchism");
            PageNode page2 = new PageNode("Aardvark");
            
            book.doSomething(page1, page2);
            book.euclideanDistance(page1, page2);
        }
    }
}


