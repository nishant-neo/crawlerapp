/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gauge.crawler.webpage.downloader;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Abhay
 */
//This class would responsible for handeling downloading of Html,pdf, and robot.txt page
public class DownloadingHandeler {

    public Downloader htmlPageDownloader;
    public PdfDownloader pdfDownloader;
    public Downloader robotTxtDownloader;

    private static final DownloadingHandeler instance = new DownloadingHandeler();

    private DownloadingHandeler() {
        htmlPageDownloader = new HtmlPageDownloader();
        pdfDownloader = new PdfDownloader();
        robotTxtDownloader = new RobotTxtDownloader();
    }

    public static DownloadingHandeler getObject() {
        return instance;

    }

    public void downloadPdf(String url) {
        try {
            this.pdfDownloader.download(url);
        } catch (Exception ex) {
            try {
                this.pdfDownloader.download(url);
            } catch (Exception ex1) {
                Logger.getLogger(DownloadingHandeler.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }

    public String getTempPdfPath() {
        return this.pdfDownloader.tempPdfPath;
    }

    public void downloadHTML() {

    }

    // This method will responsible for checking url at run time, for that url has pdf or text
    public boolean hasPdf(String url) {// We have to handle exceptio part of this method
        String[] temp = url.split("[; ]");
        URL url1 = null;
        try {
            url1 = new URL(temp[0]);
        } catch (MalformedURLException ex) {
            Logger.getLogger(DownloadingHandeler.class.getName()).log(Level.SEVERE, null, ex);
        }
        URLConnection urlConn = null;
        try {
            urlConn = url1.openConnection();
        } catch (IOException ex) {
            Logger.getLogger(DownloadingHandeler.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (urlConn.getContentType().equalsIgnoreCase("application/pdf")) {
            return true;
        } else {
            return false;
        }
    }

}
