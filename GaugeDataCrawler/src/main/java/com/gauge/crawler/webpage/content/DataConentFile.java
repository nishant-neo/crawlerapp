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

    private static UrlQueue urlQueue;
    BrowserAgent browserAgent;
    SoftReferenceObjectPool pool;
    String textData;          // Text data extracted,
    ArrayList<String> xPathList;// List of Xpath
    Elements elements;
    int xPathMethodCounter;
    FilePathHandeler filePathHandeler;
    Writer writer;

    public DataConentFile(SoftReferenceObjectPool pool) throws Exception {
        this.pool = pool;
        browserAgent = (BrowserAgent) pool.borrowObject();
        urlQueue = new UrlQueue();
        this.textData = null;
        xPathList = new ArrayList();
        elements = null;
        xPathMethodCounter = 0;
        filePathHandeler = new FilePathHandeler();
    }

    public void openWebSite(String url) throws ResponseException {
        browserAgent.visit(url);
    }

    @Override
    public void extractData() {
        this.elements = this.browserAgent.doc.findEvery(this.getXpath());
        this.textData = this.elements.innerText();
    }

    @Override
    public void saveData() {
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.filePathHandeler.getTextFilePath("url")), "utf-8"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DataConentFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
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
