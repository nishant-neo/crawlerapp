/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gauge.crawler.gaugefile;

import com.gauge.crawler.exception.GaugeCrawlerException;
import java.io.File;
import java.util.Scanner;

/**
 *
 * @author Abhay
 */
public class FilePathHandeler {

    private final FilePath filePath;
    private final FilePathValidator filePathValidator;
    private static final FilePathHandeler instance = new FilePathHandeler();
    Scanner sc;

    private FilePathHandeler() {
        sc = new Scanner(System.in);
        filePath = new FilePath();
        filePathValidator = new FilePathValidator();
    }

    public static FilePathHandeler getObject() {
        return instance;

    }

    // This method will used to change the default base path to new BasePath
    public void setBasePath() {
//        System.out.println("Do You Want To Change Default OutPut Folder Path Enter [Y/N] ");
//        String temp = this.sc.next();
        String temp = "n"; //it is used for testing
        if (temp.equalsIgnoreCase("y")) {
            System.out.println("Now Enter the OutPut Folder Path");
            temp = this.sc.next() ;
            this.filePath.basePath = temp + "/CrawlerOutPut";
        } else {
            this.filePath.basePath = System.getProperty("user.dir") + "/CrawlerOutPut";
            // System.out.println("This is base path "+this.filePath.basePath);
        }
    }

    // This method will use for getting the path of text file
    public String getTextFilePath(String urlAndYear, String text) {
        String path;
        try {
            path = filePath.TextPath(urlAndYear);
        } catch (ArrayIndexOutOfBoundsException ex) {
            try {
                path = filePath.TextPath(urlAndYear, text);
            } catch (GaugeCrawlerException ex1) {
                path = filePath.TextPath();
            }
        }
        return path;
    }

    // This method is used for getting the path of pdf file
    public String getPdfFilePath(String urlAndYear) {
        String path;
        return filePath.PdfPath(urlAndYear);

    }

    // This method will used for getting the path of html page 
    public String getHtmlPagePath(String urlAndYear) {
        return filePath.HtmpPagePath(urlAndYear);

    }

    public boolean validatePath(String path) {
        return this.filePathValidator.isValid(path);
    }

    public boolean renameFile(String oldName, String newName) {
        File f = new File(oldName);
        File f2 = new File(newName);
        boolean bool = f.renameTo(f2);
        return bool;
    }
}
