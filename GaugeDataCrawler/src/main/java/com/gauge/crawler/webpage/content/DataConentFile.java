/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gauge.crawler.webpage.content;

import com.gauge.crawler.browser.BrowserAgent;
import com.gauge.crawler.browser.BrowserAgentPool;
import com.gauge.crawler.commons.Content;
import com.gauge.crawler.exception.GaugeCrawlerException;
import com.gauge.crawler.gaugefile.FilePathHandeler;
import com.jaunt.Elements;
import com.jaunt.ResponseException;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

/**
 *
 * @author Abhay
 */
// This class will responsible for extracting the Meta Data from webpage , and this class is implementing Content interface and overriding the extract method
public class DataConentFile implements Content {

    BrowserAgent browserAgent;
    BrowserAgentPool pool;
    String textData;          // Text data extracted,
    ArrayList<String> xPathList;// List of Xpath
    Elements elements;
    int xPathMethodCounter;
    FilePathHandeler filePathHandeler;
    Writer writer;
    String url;
    String fileName;
    String year;

    public DataConentFile() throws Exception {
        this.pool = BrowserAgentPool.getPoolObject();
        browserAgent = (BrowserAgent) pool.borrowObject();
        this.textData = "";
        xPathList = new ArrayList();
        elements = null;
        xPathMethodCounter = 0;
        filePathHandeler = FilePathHandeler.getObject();
        this.xPathList.add("<textarea name=\"txtqrydsp\" >");// Xpath for testing 
    }

    public void openWebSite(String url) throws ResponseException {
        this.url = url;
        String[] str = url.split("[; ]");
        System.out.println(str[0]);
        browserAgent.visit(str[0]);
    }

    @Override
    public void extractData() {
        this.textData = "";
        while (this.textData.equals("")) {// This loop will used for checking the xPath
            System.out.println("xpath called");
            try {
                this.elements = this.browserAgent.doc.findEvery(this.getXpath());
                this.textData = this.elements.innerText();
            } catch (Exception e) {
                System.out.println("There is no xPath for this page");
                break;
            }
        }
        System.out.println(this.textData);
    }

    @Override
    public void saveData() {
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.filePathHandeler.getTextFilePath(this.url, this.textData)), "utf-8"));
            writer.write(this.textData);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println("Something wrong........");
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
