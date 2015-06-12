/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package querybasedcrawler;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 *
 * @author abhay
 */
public class Scrapper {

    ArrayList urlList = new ArrayList();
    int urlCounterForThread;

    public Scrapper() {
        this.urlCounterForThread = 0;
        String[] tmp = {"http://indiankanoon.org/", "https://www.caveofprogramming.com/java/java-for-beginners-static-variables-what-are-they.html", "http://indiankanoon.org/browse/delhi/", "http://www.tutorialspoint.com/tika/tika_extracting_pdf.htm", "http://www.javatpoint.com/maven-tutorial", "http://stackoverflow.com/questions/2491588/how-a-thread-should-close-itself-in-java", "http://indiankanoon.org/browse/patna/", "http://indiankanoon.org/browse/kerala/"};
        urlList.addAll(Arrays.asList(tmp));
    }

    public synchronized String getUrl() {// This method is used for passing url to thread
        if (this.urlCounterForThread == this.urlList.size()) { //This condition is used for stoping thread when urlList has not unvisited url
            System.out.println("Url List is Empty ");
            this.urlCounterForThread++;
            return null;
        } else if (this.urlCounterForThread < this.urlList.size()) {
            String tmp;
            tmp = (String) this.urlList.get(urlCounterForThread);
            System.out.println(this.urlCounterForThread + " url - " + this.urlList.get(urlCounterForThread));
            this.urlCounterForThread++;
            return tmp;
        } else {
            // System.out.println("Something wrong");
            return null;
        }

    }

    public boolean openWebsite(WebDriver driver, String url) {
        driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
        try {
            System.out.println("Visiting this url - " + url);
            driver.navigate().to(url);
            return true;
        } catch (Exception e) {
            // System.out.println(url + " Navigating to this url is not successfully completed");
            return false;
        }
    }

    public void closeBrowser(WebDriver driver) {
        driver.close();
    }

    public void getText(WebDriver driver) { // This method is used for getting text and writing it to text file

        String text;
        try {
            text = driver.findElement(By.xpath(this.getXpath())).getText();
        } catch (Exception e) {
            try {
                text = driver.findElement(By.xpath(this.getXpath())).getText();
            } catch (Exception e2) {
                text = driver.findElement(By.xpath(this.getXpath())).getText();
            }
        }
        try {
            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(""), "utf-8"));
            writer.write(text);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            //System.out.println(textFileName() + "This File not found");
        }
    }

    public String getXpath() {

        return null;

    }

}
