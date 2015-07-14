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
import com.jaunt.JauntException;
import java.io.File;

/**
 *
 * @author Abhay
 */
// THis calss will responsible for downloading and saving the pdf file
public class PdfDownloader implements Downloader {

    BrowserAgent browserAgent;
    BrowserAgentPool pool;
    String url;
    FilePathHandeler filepath;
    private final FilePathValidator pathValidator;

    PdfDownloader() {
        pathValidator = new FilePathValidator();
        this.pool = BrowserAgentPool.getPoolObject();
    }

    @Override
    //This method will download pdf file
    public void download(Object url) throws Exception {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String urlS = (String) url;
        browserAgent = (BrowserAgent) pool.borrowObject();
        filepath = FilePathHandeler.getObject();

        try {
            String path1 = filepath.getPdfFilePath("") + "/" + "test1.pdf";

            if (!pathValidator.isValid(path1)) {
                System.out.println(path1 + " path is not valid\nDownloading in error folder");
                path1 = "/error_downloads";
                ///write to error log
            }
            File path2 = new File(path1);
            browserAgent.download(urlS, path2);

        } catch (JauntException e) {
            System.err.println(e);
        }
    }

}
