/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gauge.crawler.gaugefile;

import com.gauge.crawler.exception.GaugeCrawlerException;
import java.io.File;

/**
 *
 * @author Abhay
 */
// This class will handle all getting and setting path of text file, pdf file, html file 
public class FilePath {

    protected String basePath;
    private File file;
    private String currentTempPath;
    private int fileNumber; // we have to update fileNumber in log files and get file number every time at start of program

    protected FilePath() {
        this.basePath = "/home/nitin/NetBeansProjects/GaugeAnalytics/gauge-data/GaugeDataCrawler/Program-File";
        file = null;
        this.fileNumber = 100;
    }

    protected String TextPath(String urlAndYear) throws ArrayIndexOutOfBoundsException {
        String[] str = urlAndYear.split("[; ]");
        //this.courtName(str[2])
        String finalPath = this.basePath + "/textFile/" + "CourtName" + "/" + str[1];
        file = new File(finalPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        this.currentTempPath = finalPath;
        finalPath += "/" + str[2]; // str[2] has file name from metadat
        return finalPath;
    }

    // This overloaded method is used for generating TextPath
    protected String TextPath(String urlAndYear, String text) throws GaugeCrawlerException {//overloaded method  
        String str = text.substring(0, 500).toLowerCase().replaceAll("[^a-zA-Z0-9 ]", "");
        if (str.contains("petition")) {
            int startIndex = str.indexOf("petition");
            return this.currentTempPath + "/" + str.substring(startIndex, startIndex + 16);
        } else {
            throw new GaugeCrawlerException();
        }
    }

    // This overloaded method is used for generating TextPath
    protected String TextPath() {// Read file number form logfiles
        String filePath = this.currentTempPath + "/" + this.fileNumber;
        this.fileNumber++;
        return filePath;

    }

    // This method is used for generating pdfPath
    protected String PdfPath(String urlAndYear) {
        String[] str = urlAndYear.split("[; ]");
        String finalPath = this.basePath + "/pdfFile/" + "CourtName" + "/" + str[1];
        file = new File(finalPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        return finalPath;
    }

    // This method is used for generating htmlPath
    protected String HtmpPagePath(String urlAndYear) {
        String[] str = urlAndYear.split("[: ]");
        String finalPath = this.basePath + "/htmlOriginalPage/" + this.courtName(str[0]) + "/" + str[1];
        file = new File(finalPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        return finalPath;
    }

    public String textFileName(String urlwithFileName) {// This method will return name of text file
        return null;

    }

    //This method would generate the name of court
    protected String courtName(String url) {

        return null;
    }

}
