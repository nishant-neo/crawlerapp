/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gauge.crawler.gaugefile;

/**
 *
 * @author Abhay
 */
public class FilePathHandeler {

    private final FilePath filePath;
    private final FilePathValidator filePathValidator;

    public FilePathHandeler() {
        filePath = new FilePath();
        filePathValidator = new FilePathValidator(); 
    }

    // This method will used to set the base path
    public void setBasePath(String basePath) {
        filePath.basePath = basePath;
    }

    // This method will use for getting the path of text file
    public String getTextFilePath() {
        return filePath.TextPath();
    }

    // This method is used for getting the path of pdf file
    public String getPdfFilePath() {
        return filePath.PdfPath();

    }

    // This method will used for getting the path of html page 
    public String getHtmlPagePath() {
        return filePath.HtmpPagePath();

    }

}
