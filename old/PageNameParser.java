package pageRank;

import com.jcraft.jsch.*;
import org.apache.commons.lang3.StringUtils;
import java.io.*;
import java.util.*;
import java.nio.charset.Charset;
import java.nio.file.*;

public class PageNameParser {
    
    public static void main (String[] args) {
        
        HashMap<Integer, String> map = new HashMap<>();
        try {
            JSch jsch = new JSch();
            Session session = jsch.getSession("uaserver", "74.117.171.100", 22);
            session.setConfig("StrictHostKeyChecking", "no");
            session.setPassword("UApass01234a");
            session.connect();
            
            Channel channel = session.openChannel("sftp");
            channel.connect();
            ChannelSftp sftpChannel = (ChannelSftp) channel;
            InputStream in = sftpChannel.get("/uafs/wikidata/enwiki-latest-pages-articles.xml");
            
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
//                Charset charset = Charset.forName("UTF-8");
//                Path output = FileSystems.getDefault().getPath("dump.txt");
//                BufferedWriter bw = Files.newBufferedWriter(output, charset);
                
                String line;
                int count = 0;
                String[] pageInfo = new String[2];
                while ((line = br.readLine()) != null) {
//                    bw.write(line+"\n");
                    String title[] = StringUtils.substringsBetween(line, "<title>", "</title>");
                    String id[] = StringUtils.substringsBetween(line, "<id>", "</id>");
                    
                    if (title != null) {
                        for (int i=0; i<title.length; i++) {
                            System.out.print(title[i]+"\n");
                            pageInfo[1] = title[i];
//                            pages.put(links[i], new PageNode(links[i]));
                        }
                    }
                    if (id != null) {
                        for (int i=0; i<id.length; i++) {
                            System.out.print(id[i]+", ");
                            pageInfo[0] = id[i];
                            //pages.put(links[i], new PageNode(links[i]));
                            while (!line.trim().contains("<text xml")) {
                                line = br.readLine();
                            }
                        }
                    }
    
                    String links[] = StringUtils.substringsBetween(line, "[[", "]]");
                    if (links != null) {
                        for (String link : links) {
                            if (!link.contains("File:") && !link.contains("Category:") && !link.contains("Wikipedia:")) {
                                System.out.println("              " + link);
                            }
                        }
                    }
                    
                    if (pageInfo[0] != null && pageInfo[1] != null) {
                        map.put(Integer.parseInt(pageInfo[0]), pageInfo[1]);
                    }
                    count++;
                }
    
//                bw.close();
            }catch (Exception e) {
                System.out.println("Exception occurred during reading file from SFTP server due to " + e.getMessage());
                e.printStackTrace();
            }
            
            
            sftpChannel.exit();
            session.disconnect();
        } catch (JSchException e) {
            e.printStackTrace();
        } catch (SftpException e) {
            e.printStackTrace();
        }
        
        System.out.println();
        System.out.println("1189, " + map.get(12));
    
        System.out.println("25, " + map.get(25));
    
        System.out.println("36, " + map.get(36));
        
        
        
    }
}
