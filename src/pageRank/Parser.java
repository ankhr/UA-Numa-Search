package pageRank;

import com.jcraft.jsch.*;
import org.apache.commons.lang3.StringUtils;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.*;

public class Parser {
    
    public LinkedHashMap<String,PageNode> pages = new LinkedHashMap<>();
    
    public LinkedHashMap<String,PageNode> parse() {
        
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
                //Charset charset = Charset.forName("UTF-8");
                //Path output = FileSystems.getDefault().getPath("dump.txt");
                //BufferedWriter bw = Files.newBufferedWriter(output, charset);
                
                String line;
                int count = 0;
                while ((line = br.readLine()) != null && count != 1000) {
                    //bw.write(line+"\n");
                    String links[] = StringUtils.substringsBetween(line, "[[", "]]");
                    if (links != null) {
                        for (int i=0; i<links.length; i++) {
                            System.out.print(links[i]+",");
                            pages.put(links[i], new PageNode(links[i]));
                        }
                        System.out.println();
                    }
                    count++;
                }
    
//                bw.close();
            }catch (Exception e) {
                System.out.println("Exception occurred during reading file from SFTP server due to " + e.getMessage());
            }
            
            
            sftpChannel.exit();
            session.disconnect();
        } catch (JSchException e) {
            e.printStackTrace();
        } catch (SftpException e) {
            e.printStackTrace();
        }
        
        return pages;
    }
    
    
}
