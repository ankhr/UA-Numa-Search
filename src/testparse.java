import com.jcraft.jsch.*;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Timer;

public class testparse {
    
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
                Timer timer = new Timer();
                while ((line = br.readLine()) != null) {
                  if (count == 1000000) {
                      System.out.println(count);
                  }
                    if (count == 10000000) {
                        System.out.println(count);
                    }
                    count++;
                }
                System.out.print(count);
    
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
