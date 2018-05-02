package pageRank;

import com.jcraft.jsch.*;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;

public class PageNameParser {
    
    public static void main (String[] args) {
        
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
                while ((line = br.readLine()) != null && count != 5000) {
                    //bw.write(line+"\n");
                    String title[] = StringUtils.substringsBetween(line, "<title>", "</title>");
                    //String id[] = StringUtils.substringsBetween(line, "<id>", "</id>");
                    if (title != null) {
                        for (int i=0; i<title.length; i++) {
                            System.out.print(title[i]+","+"\n");
                            //pages.put(links[i], new PageNode(links[i]));
                        }
                    }
//                    if (id != null) {
//                        for (int i=0; i<id.length; i++) {
//                            System.out.print(id[i]+","+"\n");
//                            //pages.put(links[i], new PageNode(links[i]));
//                        }
//                    }
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
    }
}
