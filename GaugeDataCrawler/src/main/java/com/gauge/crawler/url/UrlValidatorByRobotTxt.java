/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gauge.crawler.url;

import com.gauge.crawler.commons.Validator;
import com.gauge.crawler.webpage.downloader.RobotTxtDownloader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Abhay
 */
public class UrlValidatorByRobotTxt implements Validator {

    @Override
    public boolean isValid(Object field)throws Exception {
        String robots = "";
        RobotTxtDownloader obj = new RobotTxtDownloader();
        String link = (String) field;
        int slashslash = link.indexOf("//") + 6;
        String domain = link.substring(slashslash, link.indexOf('.', slashslash));
        BufferedReader br = null;
        try {
            String sCurrentLine;
            br = new BufferedReader(new FileReader("/Program-File/robots"+obj.map.get(domain) + ".txt"));
            while ((sCurrentLine = br.readLine()) != null) {
                robots = robots + sCurrentLine + "\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        int firstIndex, lastIndex, searched = 0, nextUseragent, flag = 1;
        //Extracting all the disallowed links and comparing to the url provided
        while (robots.indexOf("User-agent: *", searched) != -1) {

            lastIndex = robots.indexOf("User-agent: *", searched);
            nextUseragent = robots.indexOf("User-agent: *", lastIndex + 12);
            if (nextUseragent == -1) {
                nextUseragent = robots.length() - 1;
            }
            while (robots.indexOf("Disallow: ", lastIndex) != -1 && robots.indexOf("Disallow: ", lastIndex) < nextUseragent) {

                firstIndex = robots.indexOf("Disallow: ", lastIndex) + 9;
                lastIndex = robots.indexOf("\n", firstIndex);
                if (lastIndex == -1) {
                    lastIndex = robots.length() - 1;
                }
                String sub = robots.substring(firstIndex, lastIndex);
                //System.out.println(sub);
                if (link.contains(sub)) {
                    flag = 0;
                    break;
                }
                searched = lastIndex;
            }
        }
        if (!robots.contains("User-agent: *\n" + "Crawl-delay:") && flag == 0) {
            return false;
        } else if (robots.contains("User-agent: *\n" + "Crawl-delay:")) {
            int temp = robots.indexOf("User-agent: *\n" + "Crawl-delay:");
            int delayTime = Integer.parseInt(robots.substring(temp + 27, robots.indexOf("\n", temp + 27)));
            //This delayTime can be sent to some other method to impose the delay
            return true;//with an extra comdition that there ought to be delay time
        }

        return true;
    }

}
