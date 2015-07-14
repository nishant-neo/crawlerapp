/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gauge.crawler.webpage.downloader;

/**
 *
 * @author Abhay
 */
//This class would responsible for handeling downloading of Html,pdf, and robot.txt page
public class DownloadingHandeler {

    public Downloader htmlPageDownloader;
    public Downloader pdfDownloader;
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

}
