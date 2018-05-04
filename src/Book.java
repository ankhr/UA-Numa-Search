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
            
            for (Path file : stream) {
                InputStream in = Files.newInputStream(file);
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                
                String line;
                List<String[]> words = new ArrayList<>();
                while ((line = br.readLine()) != null) {
                    line = Jsoup.clean(line, Whitelist.simpleText());
                    if (!line.equalsIgnoreCase("")) {
                        line = clean(line);
                        String[] wordLine = line.split("[ ,!?.—():;0123456789/\"]+");
    
                        for (int i=0; i<wordLine.length; i++) {
                            if (wordLine[i].equalsIgnoreCase("the")) {
//                                System.out.println("the");
                            }
                        }
                        words.add(wordLine);
//                    for (int i=0; i<words.length; i++) {
//                        System.out.println(words[i]);
//                    }
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
                System.out.println(frequencies.containsKey("the"));
            }
            
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static String clean(String line) {
        
        Set<String> stopWords = new HashSet<String>();
        stopWords.add("a");
        stopWords.add("and");
        stopWords.add("is");
        stopWords.add("of");
        stopWords.add("the");
        stopWords.add("in");
    
        StringBuffer clean = new StringBuffer();
        int index = 0;
    
        while (index < line.length()) {
            // the only word delimiter supported is space, if you want other
            // delimiters you have to do a series of indexOf calls and see which
            // one gives the smallest index, or use regex
            int nextIndex = line.indexOf(" ", index);
            if (nextIndex == -1) {
                nextIndex = line.length() - 1;
            }
            String word = line.substring(index, nextIndex);
            if (!stopWords.contains(word.toLowerCase())) {
                clean.append(word);
                if (nextIndex < line.length()) {
                    // this adds the word delimiter, e.g. the following space
                    clean.append(line.substring(nextIndex, nextIndex + 1));
                }
            }
            index = nextIndex + 1;
        }
    
        return clean.toString();
    }
}
