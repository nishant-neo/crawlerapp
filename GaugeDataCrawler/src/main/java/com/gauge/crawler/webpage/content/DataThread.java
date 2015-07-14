/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gauge.crawler.webpage.content;

import com.gauge.crawler.gaugefile.FilePathHandeler;
import com.gauge.crawler.url.urlqueue.UrlQueue;
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
    FilePathHandeler filePath;
    UrlQueue urlQueue;

    public DataThread() throws Exception {
        this.urlQueue = UrlQueue.getObject();
        dataConentFile = new DataConentFile();
        filePath = new FilePathHandeler();

    }

    @Override
    public void run() {
        System.out.println("Data Thread called");

        String u = "http://judis.nic.in/judis_andhra/qrydisp.aspx?filename=12564;2014;PETITIONNo.3618OF2014";
        this.urlQueue.pushUrl(u);

        while (this.urlQueue.length() > 0) {
            System.out.println("Data extracting");
            String url = this.urlQueue.popUrl();
            System.out.println("loop in");
            if (this.urlQueue.addToVisitedList(url)) {
                try {
                    dataConentFile.openWebSite(url);
                } catch (ResponseException ex) {
                    Logger.getLogger(DataThread.class.getName()).log(Level.SEVERE, null, ex);
                }
                dataConentFile.extractData();
                dataConentFile.saveData();
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
