/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gauge.crawler.webpage.content;


import com.jaunt.ResponseException;
import com.jaunt.UserAgent;
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

    UserAgent agent;

    public DataThread(SoftReferenceObjectPool pool) throws Exception {
        agent = (UserAgent) pool.borrowObject();
    }

    @Override
    public void run() {
       System.out.println("Data Thread created...");
        try {
            agent.visit("http://www.tutorialspoint.com/design_pattern/factory_pattern.htm");
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
