import com.jcraft.jsch.*;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import java.io.*;
import java.nio.file.*;
import java.util.*;


public class Book {

    public static void main(String[] args) {
    
        try {
            Path inDir = FileSystems.getDefault().getPath("H:/Coursework/Advanced Algorithms/UA Numa Search/ParsedOutput/AA/");
            DirectoryStream<Path> stream = Files.newDirectoryStream(inDir);
            
            
            boolean pageNameTracker = false;
            String pageName = "";
            Map<String, Map<String, Integer>> a = new LinkedHashMap<>();
            
            for (Path file : stream) {
                InputStream in = Files.newInputStream(file);
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                
                String line;
                List<String[]> words = new ArrayList<>();
                while ((line = br.readLine()) != null) {
//                    System.out.print("");
                    if (!pageNameTracker) {
                        pageName = br.readLine();
                        pageNameTracker = true;
                    }
                    
                    line = Jsoup.clean(line, Whitelist.simpleText());
                    if (!line.equalsIgnoreCase("")) {
//                        if (line.contains("Some of these are of women held in servitude")) {
//                            line = removeEhWords(line);
                            String[] wordLine = line.split("[ 0123456789.,:;!?—()/\"]+");
//                            cleanCheck(wordLine);
                            words.add(wordLine);
//                        }
                    }
                }
                
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
//                System.out.println("1 page has word recorded.");
                a.put(pageName, frequencies);
                pageNameTracker = false;
                br.close();
            }
            System.out.println("All pages have word recorded.");
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void calculate() {
    
    }
    
    public static String removeEhWords(String line) throws IOException {
        
        Set<String> stopWords = new HashSet<>();
        InputStream in = Files.newInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String input;
        while ((input = br.readLine()) != null) {
            stopWords.add(input);
        }
    
        StringBuffer clean = new StringBuffer();
        int index = 0;
    
        while (index < line.length()) {

            int nextIndex = line.indexOf(" ", index);
            if (nextIndex == -1) {
                nextIndex = line.length() - 1;
            }
            
            String word = line.substring(index, nextIndex);
            if (word.equalsIgnoreCase("since")) {
                System.out.println("test");
                word = word.replaceAll("[.,:;!?—()/\"']+", "");
                if (!stopWords.contains(word.toLowerCase())) {
                    clean.append(word);
                    if (nextIndex < line.length()) {
                        clean.append(line.substring(nextIndex, nextIndex + 1));
                    }
                }
                index = nextIndex + 1;
            }
            
        }
        br.close();
        return clean.toString();
    }
    
    public static void cleanCheck(String[] wordLine) throws IOException{
    
        InputStream in = Files.newInputStream(Paths.get("H:\\Coursework\\Advanced Algorithms\\UA Numa Search\\src\\wordFilter.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String input;
        List<String> list = new ArrayList<>();
        while ((input = br.readLine()) != null) {
            list.add(input);
        }
        
        for (int i=0; i<wordLine.length; i++) {
            for (int j=0; j<list.size(); j++) {
                if (wordLine[i].equalsIgnoreCase(list.get(j))) {
                    System.out.println(wordLine[i]+":"+Arrays.toString(wordLine));
                }
            }
        }
        br.close();
        
    }
}
