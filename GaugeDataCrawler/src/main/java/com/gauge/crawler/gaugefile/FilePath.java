/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gauge.crawler.gaugefile;

import java.io.File;

/**
 *
 * @author Abhay
 */
// This class will handle all getting and setting path of text file, pdf file, html file 
public class FilePath {

    protected String basePath;
    File file;

    protected FilePath() {
        this.basePath = "/home/nitin/NetBeansProjects/GaugeAnalytics/gauge-data/GaugeDataCrawler/Program-File";
        file = null;
    }

//    // This method will used to set the base path
//    public void setBasePath(String basePath) {
//        this.basePath = basePath;
//    }
    // This method is used for generating TextPath
    protected String TextPath(String urlAndYear) {
        String[] str = urlAndYear.split("[; ]");
        //this.courtName(str[2])
        String finalPath = this.basePath + "/textFile/" + "TextCourt" + "/" + str[1];
        file = new File(finalPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        return finalPath;
    }

    // This method is used for generating pdfPath
    protected String PdfPath(String urlAndYear) {
        String[] str = urlAndYear.split("[: ]");
        String finalPath = this.basePath + "/pdfFile/" + this.courtName(str[0]) + "/" + str[1];
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

    public String textFileName() {// This method will return name of text file
        return null;

    }

    //This method would generate the name of court
    protected String courtName(String url) {

        return null;
    }

}
