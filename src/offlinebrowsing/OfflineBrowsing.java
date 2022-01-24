/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package offlinebrowsing;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.*;

/**
 *
 * @author Admin
 */
class downloadImage implements Callable<Void> {

    private String link;
    private String filepath;
    public downloadImage(String link, String filepath ) {
        this.link = link;
        this.filepath = filepath;
    }

    public Void call() throws IOException {

        try {
            URL url = new URL(link);
            if (url.getContent().getClass().getName().contains("Image")) {
                String splitter[] = link.split("\\.");
                String format = splitter[splitter.length - 1];
                
                splitter = link.split("/");
                String fileName = splitter[splitter.length - 1];
                
                ImageIO.write(ImageIO.read(url),format ,new File(filepath+ fileName));
            }
        } catch (Exception e) {
            return null;
        }

        return null;
    }

}

public class OfflineBrowsing {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
//        String site = "https://aaup.edu";
//        URL webSite = new URL(site);
//
//        String pageContent = new String(new BufferedInputStream(webSite.openStream()).readAllBytes());
//        String lines[] = pageContent.split(">");
//        
//        ArrayList<String> sitelinks = getAllInnerTag("=\""+site+"/",lines);
//        ArrayList<String> hreflinks = getAllInnerTag("=\"/",lines);
//        ArrayList<String> srclinks = getAllInnerTag("src=\"",lines);
//        
//        writeFile("src\\htmlContent\\website.txt", lines);
//        writeFile("src\\htmlContent\\hrefList.txt", hreflinks);
//        writeFile("src\\htmlContent\\srcfList.txt", srclinks);
//        writeFile("src\\htmlContent\\siteList.txt", sitelinks);
//
//        ExecutorService svc = Executors.newFixedThreadPool(srclinks.size());
//
//        ArrayList<Future> tasks = new ArrayList<Future>();
//
//        for (String url : srclinks) {
//            tasks.add(svc.submit(new downloadImage(site+url,"src\\htmlContent\\Images\\")));
//        }
//        //you must delete all repeatly path such as \
//        svc.shutdown();
           
        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Admin\\Desktop\\FALL 2021-2022\\SOFTWARE ENGINEERING\\inputnotes.txt"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\Admin\\Desktop\\FALL 2021-2022\\SOFTWARE ENGINEERING\\outputnotes.txt"));
        
        String as = "";
        String data = "";
        String temp[];
        
        byte v = 2;
        char ch = (char)v;
        String rub = ch+"";
        while ((data=br.readLine())!= null){
            temp  = data.split("\\.");
            for (String t: temp){
                bw.write(t+"\n");
            }
            bw.newLine();
        }
        bw.close();
        br.close();
        
//        BufferedReader br2 = new BufferedReader(new FileReader("C:\\Users\\Admin\\Desktop\\FALL 2021-2022\\SOFTWARE ENGINEERING\\inputnotes.txt"));
//        BufferedWriter bw2 = new BufferedWriter(new FileWriter("C:\\Users\\Admin\\Desktop\\FALL 2021-2022\\SOFTWARE ENGINEERING\\outputnotes.txt"));
//        
//        while ((data=br2.readLine())!= null){
//            temp  = data.split("/[A-Z]/g");
//            for (String t: temp){
//                if (t.contains(rub)) {
//                    t.replace(rub, "");
//                    
//                } 
//                bw2.write(t+"\n\n");
//            }
//        }
//        bw2.close();
//        br2.close();
        
        

    }
    public static void renderPath(String originURL ,String filePath){
        //this method should add ORIGIN URL to relative path example : https://aaup.edu + "//Academics//Academic-Calendar" 
    }
    public static ArrayList<String> getAllInnerTag(String tagName, String lines[]) {

        ArrayList<String> list = new ArrayList<String>();
        int x;
        for (String l : lines) {
            x = l.indexOf(tagName);
            if (x > -1) {
                String splitter[] = l.substring(x).split("\"");
                if (splitter.length > 0) {
                    list.add(splitter[1]);
                }
            }
        }
        return list;
    }

    public static ArrayList<String> readFile(String fileName) throws Exception {

        ArrayList<String> links = new ArrayList<String>();
        BufferedReader bufin = new BufferedReader(new FileReader(fileName));
        String line = "";
        while ((line = bufin.readLine()) != null) {
            links.add(line);
        }
        return links;

    }

    public static void writeFile(String filePath, ArrayList<String> lines) throws IOException {

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath));
        for (String l : lines) {
            bufferedWriter.write(l + '\n');
        }

        bufferedWriter.flush();
        bufferedWriter.close();

    }

    public static void writeFile(String filePath, String lines[]) throws IOException {

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath));
        for (String l : lines) {
            bufferedWriter.write(l + ">\n");
        }

        bufferedWriter.flush();
        bufferedWriter.close();

    }

}
