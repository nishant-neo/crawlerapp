/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gauge.crawler.webpage.content;

import com.gauge.crawler.browser.BrowserAgent;
import com.gauge.crawler.commons.Content;
import com.gauge.crawler.gaugefile.FilePathHandeler;
import com.gauge.crawler.url.urlqueue.UrlQueue;
import com.jaunt.Elements;
import com.jaunt.ResponseException;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.pool.impl.SoftReferenceObjectPool;

/**
 *
 * @author Abhay
 */
// This class will responsible for extracting the Meta Data from webpage , and this class is implementing Content interface and overriding the extract method
public class DataConentFile implements Content {

    UrlQueue urlQueue;
    BrowserAgent browserAgent;
    SoftReferenceObjectPool pool;
    String textData;          // Text data extracted,
    ArrayList<String> xPathList;// List of Xpath
    Elements elements;
    int xPathMethodCounter;
    FilePathHandeler filePathHandeler;
    Writer writer;
    String url;
    String year;

    public DataConentFile(SoftReferenceObjectPool pool, UrlQueue urlQueue) throws Exception {
        this.pool = pool;
        browserAgent = (BrowserAgent) pool.borrowObject();
        this.urlQueue = urlQueue;
        this.textData = null;
        xPathList = new ArrayList();
        elements = null;
        xPathMethodCounter = 0;
        filePathHandeler = new FilePathHandeler();
    }

    public void openWebSite() throws ResponseException {
        String[] str = urlQueue.popUrl().split("[: ]");
        this.url = str[0];
        this.year = str[1];
        browserAgent.visit(url);
    }

    @Override
    public void extractData() {
        while (this.textData.equals("")) {// This loop will used for checking the xPath
            try {
                this.elements = this.browserAgent.doc.findEvery(this.getXpath());
                this.textData = this.elements.innerText();
            } catch (Exception e) {
                System.out.println("There is no xPath for this page");
                break;
            }
        }

    }

    @Override
    public void saveData() {
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.filePathHandeler.getTextFilePath(year)), "utf-8"));
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            Logger.getLogger(DataConentFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // This method will convert text fro pdf
    public void getTextFromPdf() {

    }

    public void closeBrowser() throws Exception {
        pool.returnObject(this.browserAgent);
    }

    private String getXpath() {
        String xPath = this.xPathList.get(xPathMethodCounter);
        this.xPathMethodCounter++;
        return xPath;

    }

}
