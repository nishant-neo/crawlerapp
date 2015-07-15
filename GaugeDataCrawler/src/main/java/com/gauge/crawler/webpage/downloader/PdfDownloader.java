/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gauge.crawler.webpage.downloader;

import com.gauge.crawler.browser.BrowserAgent;
import com.gauge.crawler.browser.BrowserAgentPool;
import com.gauge.crawler.gaugefile.FilePathHandeler;
import java.io.File;

/**
 *
 * @author Abhay
 */
// THis calss will responsible for downloading and saving the pdf file
public class PdfDownloader implements Downloader {

    BrowserAgent browserAgent;
    BrowserAgentPool pool;
    FilePathHandeler filePathHandeler;
    String tempPdfPath; // temp path of pdf

    protected PdfDownloader() {
        filePathHandeler = FilePathHandeler.getObject();
        this.pool = BrowserAgentPool.getPoolObject();
    }

    @Override
    //This method would download the pdf file
    public void download(String url) throws Exception {
        String[] temp = url.split("[; ]");
        browserAgent = (BrowserAgent) pool.borrowObject();
        String filePath = this.filePathHandeler.getPdfFilePath(url) + "/" + "temp.pdf";
        this.tempPdfPath = filePath;
        File path = new File(filePath);
        System.out.println(path.getAbsolutePath());
        System.out.println("Visiting this url ------------ " + path);
        browserAgent.download(temp[0], path);
        pool.returnObject(browserAgent);
    }

}
