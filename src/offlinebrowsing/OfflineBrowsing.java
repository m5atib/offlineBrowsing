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


public class OfflineBrowsing {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
//         TODO code application logic here
        String site = "https://www.aaup.edu/";

        downloadURL dsite = new downloadURL(site);

        //writeFile("src\\htmlContent\\website.txt", lines);
        //writeFile("src\\htmlContent\\hrefList.txt", hreflinks);
        //writeFile("src\\htmlContent\\srcList.txt", srclinks);
        //writeFile("src\\htmlContent\\siteList.txt", sitelinks);
        
        //TO DOWNLOAD WEBSITE IMAGES 
        
        //

    }

    public static void renderPath(String originURL, String filePath) {
        //this method should add ORIGIN URL to relative path example : https://aaup.edu + "//Academics//Academic-Calendar" 
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
