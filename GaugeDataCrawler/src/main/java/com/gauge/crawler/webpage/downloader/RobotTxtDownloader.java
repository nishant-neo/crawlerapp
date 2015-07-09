/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gauge.crawler.webpage.downloader;

import java.net.URL;
import java.util.*;
import java.io.*;

/**
 *
 * @author Abhay
 */
// This class will responsible for downloading and saving the Robot.Txt file
public class RobotTxtDownloader implements Downloader {

    @Override
    public void download(Object url) throws Exception {
        URL link = new URL((String) url + "/robots.txt");
        InputStream in = link.openStream();
        String inputStreamString = new Scanner(in, "UTF-8").useDelimiter("\\A").next();
        System.out.println(inputStreamString);
        FileOutputStream fop = null;
        File file;
        String content = inputStreamString;
        try {
            file = new File("/Program-File/link" + "vfv" + ".txt");
            fop = new FileOutputStream(file);
            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }
            // get the content in bytes
            byte[] contentInBytes = content.getBytes();
            fop.write(contentInBytes);
            fop.flush();
            fop.close();
            //System.out.println("Done");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
