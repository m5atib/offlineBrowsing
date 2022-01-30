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

    public downloadImage(String link, String filepath) {
        this.link = link;
        this.filepath = filepath;
    }

    public Void call() throws IOException {

        try {

            URL url = new URL(link);
            System.out.println(url.getContent().getClass().getName());
            if (url.getContent().getClass().getName().contains("Image")) {
                //System.out.println(link);
                String splitter[] = link.split("\\.");
                String format = splitter[splitter.length - 1];

                splitter = link.split("/");
                String fileName = splitter[splitter.length - 1];

                ImageIO.write(ImageIO.read(url), format, new File(filepath + fileName));
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
//         TODO code application logic here
        String site = "https://www.aaup.edu";
        URL webSite = new URL(site);

        String pageContent = new String(new BufferedInputStream(webSite.openStream()).readAllBytes());
        String lines[] = pageContent.split(">");

        ArrayList<String> sitelinks = getAllInnerTag("=\"" + site + "/", lines);
        ArrayList<String> hreflinks = getAllInnerTag("=\"/", lines);
        ArrayList<String> srclinks = getAllInnerTag("src=\"", lines);

        writeFile("src\\htmlContent\\website.txt", lines);
        writeFile("src\\htmlContent\\hrefList.txt", hreflinks);
        writeFile("src\\htmlContent\\srcList.txt", srclinks);
        writeFile("src\\htmlContent\\siteList.txt", sitelinks);

        ExecutorService svc = Executors.newFixedThreadPool(srclinks.size());

        ArrayList<Future> tasks = new ArrayList<Future>();

        for (String url : srclinks) {
            //System.out.println(url);
            tasks.add(svc.submit(new downloadImage(url, "src\\htmlContent\\Images\\")));
        }
        
        ExecutorService svc2 = Executors.newFixedThreadPool(sitelinks.size());
        ArrayList<Future> tasks2 = new ArrayList<Future>();
        for (String url : sitelinks) {
            //System.out.println(url);
            tasks2.add(svc2.submit(new downloadImage(url, "src\\htmlContent\\Images\\")));
        }
        //you must delete all repeatly path such as \
        svc.shutdown();
        svc2.shutdown();

    }

    public static void renderPath(String originURL, String filePath) {
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
