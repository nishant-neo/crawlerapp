/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gauge.crawler.webpage.downloader;

import com.gauge.crawler.browser.BrowserAgent;
import com.gauge.crawler.browser.BrowserAgentPool;
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

    BrowserAgent browserAgent;
    FilePathHandeler filepath;
    BrowserAgentPool pool;
    private final FilePathValidator pathValidator;

    HtmlPageDownloader() {
        this.filepath = FilePathHandeler.getObject();
        pathValidator = new FilePathValidator();
        this.pool = BrowserAgentPool.getPoolObject();
    }

    private final SecureRandom random = new SecureRandom();

    public String nextSessionId() {
        return new BigInteger(130, random).toString(32);
    }

    @Override
    // This method will download original html page
    public void download(String url) throws Exception {
        // String  = (String) url; 
        browserAgent = (BrowserAgent) pool.borrowObject();

        String urlS = (String) url;
        try {
            String randomvalue = this.nextSessionId();
            browserAgent.visit(urlS);

            String finalpath = filepath.getHtmlPagePath("") + "/" + urlS.replaceAll("/", "_") + "_" + randomvalue + ".html";
            if (!pathValidator.isValid(finalpath)) {
                System.out.println(finalpath + " path is not valid\nDownloading in error folder");
                finalpath = "/error_downloads";
                ///write to error log
            }
            browserAgent.doc.saveAs(finalpath);
        } catch (JauntException e) {
            System.err.println(e);

        } finally {
            this.pool = BrowserAgentPool.getPoolObject();
        }

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
