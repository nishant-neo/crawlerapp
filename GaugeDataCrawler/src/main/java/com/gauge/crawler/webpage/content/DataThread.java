/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gauge.crawler.webpage.content;

import com.gauge.crawler.exception.GaugeParsingException;
import com.gauge.crawler.url.urlqueue.UrlQueue;
import com.gauge.crawler.webpage.downloader.DownloadingHandeler;
import com.jaunt.ResponseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Abhay
 */
// This thread class will responsible for downloading text , pdf and data-html page
public class DataThread implements Runnable {

    DataConentFile dataConentFile;
    UrlQueue urlQueue;
    DownloadingHandeler downloadingHandeler;

    public DataThread() throws Exception {
        this.urlQueue = UrlQueue.getObject();
        dataConentFile = new DataConentFile();
        downloadingHandeler = DownloadingHandeler.getObject();
    }

    @Override
    public void run() {
        System.out.println("Data Thread called");

        String u = "http://164.100.138.36/casest/generatenew.php?path=data/judgment/2015/&fname=CRCR.A125892012.pdf&smflag=N;2014";
        this.urlQueue.pushUrl(u);

        while (this.urlQueue.length() > 0) {
            System.out.println("Data extracting");
            String url = this.urlQueue.popUrl();
            System.out.println("loop in");
            if (this.urlQueue.addToVisitedList(url)) {// True if this url is not visited
                if (downloadingHandeler.hasPdf(url)) {// True if this url has pdf
                    System.out.println("This url has pdf");
                    downloadingHandeler.downloadPdf(url);// Here we have to handle exceptio if parser get failed to parse pdf document
                    try {
                        dataConentFile.getTextFromPdf(downloadingHandeler.getTempPdfPath(), url);
                    } catch (GaugeParsingException ex) {
                        Logger.getLogger(DataThread.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    dataConentFile.saveData();
                } else {// This part is for text page
                    System.out.println("There is no pdf");
                    try {
                        dataConentFile.openWebSite(url);
                    } catch (ResponseException ex) {
                        Logger.getLogger(DataThread.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    dataConentFile.extractData();
                    dataConentFile.saveData();
                }

                System.out.println("Data extracted ");
            }
        }
        try {
            dataConentFile.closeBrowser();
        } catch (Exception ex) {
            Logger.getLogger(DataThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
