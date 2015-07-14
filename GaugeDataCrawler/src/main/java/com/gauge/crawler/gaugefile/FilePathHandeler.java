/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gauge.crawler.gaugefile;

import com.gauge.crawler.exception.GaugeCrawlerException;

/**
 *
 * @author Abhay
 */
public class FilePathHandeler {

    private final FilePath filePath;
    private final FilePathValidator filePathValidator;
    private static final FilePathHandeler instance = new FilePathHandeler();

    private FilePathHandeler() {
        filePath = new FilePath();
        filePathValidator = new FilePathValidator();
    }

    public static FilePathHandeler getObject() {
        return instance;

    }

    // This method will used to set the base path
    public void setBasePath(String basePath) {
        filePath.basePath = basePath;
    }

    // This method will use for getting the path of text file
    public String getTextFilePath(String urlAndYear) throws ArrayIndexOutOfBoundsException {
        return filePath.TextPath(urlAndYear);
    }

    public String getTextFilePath(String urlAndYear, String text) throws GaugeCrawlerException {
        return filePath.TextPath(urlAndYear, text);
    }

    public String getTextFilePath() {
        return filePath.TextPath();
    }

    // This method is used for getting the path of pdf file
    public String getPdfFilePath(String urlAndYear) {
        return filePath.PdfPath(urlAndYear);

    }

    // This method will used for getting the path of html page 
    public String getHtmlPagePath(String urlAndYear) {
        return filePath.HtmpPagePath(urlAndYear);

    }

    public boolean validatePath(String path) {
        return this.filePathValidator.isValid(path);
    }

}
