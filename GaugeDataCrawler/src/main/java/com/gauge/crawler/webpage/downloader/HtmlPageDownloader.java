/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gauge.crawler.webpage.downloader;

import com.gauge.crawler.browser.BrowserAgent;
import com.gauge.crawler.browser.BrowserAgentPool;
import com.gauge.crawler.gaugefile.FilePathHandeler;
import com.jaunt.*;

/**
 *
 * @author Abhay
 */
// This Class will Responsible for downloading the original html page and save to File System
public class HtmlPageDownloader implements Downloader {

    BrowserAgent browserAgent;
    FilePathHandeler filePathHandeler;
    BrowserAgentPool pool;

    HtmlPageDownloader() {
        this.filePathHandeler = FilePathHandeler.getObject();
        this.pool = BrowserAgentPool.getPoolObject();
    }

    @Override
    // This method will download original html page
    public void download(String urlString) throws Exception {
        // String  = (String) url; 
        browserAgent = (BrowserAgent) pool.borrowObject(); // Taking BrowserAgent from pool

        String[] tempUrl = urlString.split("[; ]");
        try {
            browserAgent.visit(tempUrl[0]);
            String filePath = this.filePathHandeler.getHtmlPagePath(urlString) + ".html";
            browserAgent.doc.saveAs(filePath);
        } catch (JauntException e) {
            System.err.println(e.getMessage());
        } finally {
            this.pool.returnObject(this.browserAgent); // Returning Browseragent to pool
        }
    }

}
