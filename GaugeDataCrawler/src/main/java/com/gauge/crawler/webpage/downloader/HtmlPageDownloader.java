/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gauge.crawler.webpage.downloader;

import com.gauge.crawler.browser.BrowserAgent;
import static com.gauge.crawler.commons.MainClass.pool;
import com.gauge.crawler.gaugefile.FilePathHandeler;
import com.gauge.crawler.gaugefile.FilePathValidator;
import com.jaunt.*;
import java.math.BigInteger;
import java.security.SecureRandom;

/**
 *
 * @author Abhay
 */
// This Class will Responsible for downloading the original html page and save to File System
public class HtmlPageDownloader implements Downloader {

    BrowserAgent agent;
    FilePathHandeler filepath;
    private final FilePathValidator pathValidator;

    HtmlPageDownloader() {
        pathValidator = new FilePathValidator();
    }

    private final SecureRandom random = new SecureRandom();

    public String nextSessionId() {
        return new BigInteger(130, random).toString(32);
    }

    @Override
    // This method will download original html page
    public void download(Object url) throws Exception {
        // String  = (String) url; 
        agent = (BrowserAgent) pool.borrowObject();
        filepath = new FilePathHandeler();

        String urlS = (String) url;
        try {
            String randomvalue = this.nextSessionId();
            agent.visit(urlS);

            String finalpath = filepath.getHtmlPagePath() + "/" + urlS.replaceAll("/", "_") + "_" + randomvalue + ".html";
            if (!pathValidator.isValid(finalpath)) {
                System.out.println(finalpath + " path is not valid\nDownloading in error folder");
                finalpath = "/error_downloads";
                ///write to error log
            }
            agent.doc.saveAs(finalpath);
        } catch (JauntException e) {
            System.err.println(e);

        }

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
