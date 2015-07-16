/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gauge.crawler.url;

import com.gauge.crawler.commons.Validator;
import com.gauge.crawler.webpage.downloader.RobotTxtDownloader;

/**
 *
 * @author Abhay
 */
public class UrlValidatorByRobotTxt implements Validator {

    @Override
    public boolean isValid(Object field) {
         //throw new UnsupportedOperationException("Not supported yet."); 
        //To change body of generated methods, choose Tools | Templates.
        //String robots = field.toString();
        RobotTxtDownloader obj = new RobotTxtDownloader();
        String link = (String) field;
        int slashslash = link.indexOf("//") + 6;
        String domain = link.substring(slashslash, link.indexOf('.', slashslash));
        obj.map.get(domain)+ ".txt"
         //Some method to access the file that contains the robots.txt for a particular domain. 
        //String robots = "";// Convert the contents to String data  type
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
