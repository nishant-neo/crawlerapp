/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gauge.crawler.webpage.content;

import com.gauge.crawler.browser.BrowserAgent;
import com.gauge.crawler.browser.BrowserAgentPool;
import com.gauge.crawler.commons.Content;
import com.gauge.crawler.exception.GaugeParsingException;
import com.gauge.crawler.gaugefile.FilePathHandeler;
import com.jaunt.Elements;
import com.jaunt.ResponseException;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

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
    String fileName; // This have current file name
    String year;

    public DataConentFile() throws Exception {
        this.pool = BrowserAgentPool.getPoolObject();
        browserAgent = (BrowserAgent) pool.borrowObject();
        this.textData = "";
        xPathList = new ArrayList();
        elements = null;
        xPathMethodCounter = 0;
        filePathHandeler = FilePathHandeler.getObject();
        this.filePathHandeler.setBasePath();// We have to add this method in crawling handeler
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
        this.fileName = this.filePathHandeler.getTextFilePath(this.url, this.textData);
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.fileName), "utf-8"));
            writer.write(this.textData);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println("Something wrong........");
        }
    }

    // This method will extract text from pdf at runtime after just downloading pdf file
    public void getTextFromPdf(String pdfPath, String urlWithAllData) throws GaugeParsingException {
        this.url = urlWithAllData;
        this.textData = "";
        PDFParser parser;
        PDFTextStripper pdfStripper;
        PDDocument pdDoc = null;
        COSDocument cosDoc = null;
        try {
            parser = new PDFParser(new FileInputStream(pdfPath));
        } catch (IOException e) {
            throw new GaugeParsingException();
        }
        try {
            parser.parse();
            cosDoc = parser.getDocument();
            pdfStripper = new PDFTextStripper();
            pdDoc = new PDDocument(cosDoc);
            pdfStripper.setStartPage(1);
            pdfStripper.setEndPage(5);
            this.textData = pdfStripper.getText(pdDoc);
        } catch (Exception e) {
            throw new GaugeParsingException();
        } finally {
            try {
                if (cosDoc != null) {
                    cosDoc.close();
                }
                if (pdDoc != null) {
                    pdDoc.close();
                }
            } catch (Exception e) {
            }
        }

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
