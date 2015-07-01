/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gauge.crawler.webpage.content;

import com.gauge.crawler.browser.BrowserAgent;
import com.jaunt.ResponseException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.pool.impl.SoftReferenceObjectPool;

/**
 *
 * @author Abhay
 */
// This thread class will responsible for downloading text , pdf and data-html page
public class DataThread implements Runnable {

    BrowserAgent agent;

    public DataThread(SoftReferenceObjectPool pool) throws Exception {
        agent = (BrowserAgent) pool.borrowObject();
    }

    @Override
    public void run() {
        System.out.println("Data Thread created...");
        try {
            try{
                agent.visit("http://indiankanoon.org/");
                System.out.println("url opened with vpn");
            }catch(Exception e){
                System.out.println("Url opened witout vpn");
                agent.visit("http://stackoverflow.com/");
            }
            System.out.println(agent.doc.innerHTML());
        } catch (ResponseException ex) {
            Logger.getLogger(DataThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            agent.doc.saveAs("test.html");
        } catch (IOException ex) {
            Logger.getLogger(DataThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
