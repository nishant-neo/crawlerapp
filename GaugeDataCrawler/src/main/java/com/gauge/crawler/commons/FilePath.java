/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gauge.crawler.commons;

/**
 *
 * @author Abhay
 */
// This class will handle all getting and setting path of text file, pdf file, html file 
public class FilePath {

    private String basePath;

    // This method will used to set the base path
    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    // This method will use for getting the path of text file
    public String getTextPath() {
        return null;

    }

    // This method is used for getting the path of pdf file
    public String getPdfPath() {
        return null;

    }

    // This method will used for getting the path of html page 
    public String getHtmpPagePath() {
        return "/Program-File";

    }

}
