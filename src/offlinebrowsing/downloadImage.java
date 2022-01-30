/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package offlinebrowsing;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.Callable;
import javax.imageio.ImageIO;

/**
 *
 * @author Admin
 */
public class downloadImage implements Callable<Void> {

    private String link;
    private String filepath;

    public downloadImage(String link, String filepath) {
        this.link = link;
        this.filepath = filepath;
    }

    public Void call() throws IOException {

        try {

            URL url = new URL(link);
            //System.out.println(url.getContent().getClass().getName());
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
