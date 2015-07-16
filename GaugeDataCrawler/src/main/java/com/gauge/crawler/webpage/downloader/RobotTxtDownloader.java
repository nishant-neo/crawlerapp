/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gauge.crawler.webpage.downloader;

import com.gauge.crawler.browser.BrowserAgent;
import com.gauge.crawler.browser.BrowserAgentPool;
import com.gauge.crawler.gaugefile.FilePathHandeler;
import java.util.*;
import java.io.*;

/**
 *
 * @author Abhay
 */
// This class will responsible for downloading and saving the Robot.Txt file
public class RobotTxtDownloader implements Downloader {
    public Map<String, String> map = new HashMap<String, String>();
    BrowserAgent browserAgent;
    BrowserAgentPool pool;
    FilePathHandeler filePathHandeler;
    String tempPdfPath; // temp path of pdf
    
    public RobotTxtDownloader(){
        map.put("judis", "1");
        map.put("dspace", "2");
        map.put("lobis", "3");
        map.put("164.100.138.36", "4");
        map.put("jhr", "5");
        map.put("courtnic", "6");
        map.put("judgementhck", "7");
        map.put("mphc", "8");
        map.put("rhccasestatus", "9");
    }

    @Override
    public void download(String url) throws Exception {
        int slashslash = url.indexOf("//") + 6;
        String domain = url.substring(slashslash, url.indexOf('.', slashslash));
        
        try {
            browserAgent = (BrowserAgent) pool.borrowObject();
            File path = new File("/Program-File/robots/" + map.get((String)domain) + ".txt");
            browserAgent.download(url,path );
        } finally {
            pool.returnObject(browserAgent);// Returning BrowserAgent to pool
        }
       
    }
}
