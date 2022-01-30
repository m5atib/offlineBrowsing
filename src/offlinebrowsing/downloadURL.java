/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package offlinebrowsing;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *
 * @author Admin
 */
public class downloadURL {

    String site;

    public downloadURL(String site) throws Exception {
        URL webSite = new URL(site);
        String pageContent = new String(new BufferedInputStream(webSite.openStream()).readAllBytes());
        String lines[] = pageContent.split(">");

        ArrayList<String> sitelinks = getAllInnerTag("=\"" + site + "/", lines);
        ArrayList<String> hreflinks = getAllInnerTag("=\"/", lines);
        ArrayList<String> srclinks = getAllInnerTag("src=\"", lines);

        //here download all page videos
        //downloadURLImages(sitelinks);
        //downloadURLImages(hreflinks);
        //downloadURLImages(srclinks);
        //here download all page videos using method or class downloadVideo
        //....
        //....
        //....
        //concatenate all arrays of links as a one array
        ArrayList<String> allLinks = new ArrayList<String>();
        allLinks.addAll(sitelinks);
        allLinks.addAll(hreflinks);
        allLinks.addAll(srclinks);

        for (String Li : allLinks) {
            if (!Li.contains(site)) {
                System.out.println(site + Li);
            } else {
                System.out.println(Li);
            }
        }
    }

    public ArrayList<String> getAllInnerTag(String tagName, String lines[]) {

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

    public void downloadURLImages(ArrayList<String> srclinks) {
        ExecutorService svc = Executors.newFixedThreadPool(srclinks.size());

        ArrayList<Future> tasks = new ArrayList<Future>();

        for (String url : srclinks) {
            //System.out.println(url);
            tasks.add(svc.submit(new downloadImage(url, "src\\htmlContent\\Images\\")));
        }

        //you must delete all repeatly path such as \
        svc.shutdown();

    }

}
