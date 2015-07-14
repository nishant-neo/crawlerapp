/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gauge.crawler.commons;

import com.gauge.crawler.url.urlqueue.UrlQueue;
import com.gauge.crawler.webpage.content.DataThread;
import com.gauge.crawler.webpage.metadata.MetaThread;
import org.apache.commons.pool.impl.SoftReferenceObjectPool;

/**
 *
 * @author Abhay
 */
public class MainClass {

    private static final BrowserAgentFactory factory = new BrowserAgentFactory();
    public static final SoftReferenceObjectPool pool = new SoftReferenceObjectPool((factory));
    private static final UrlQueue urlQueue = new UrlQueue();

    public static void main(String[] args) throws Exception {
        System.out.println("hiii im working...");
        Thread t1 = new Thread(new DataThread(pool, urlQueue));
        //Thread t2 = new Thread(new DataThread(pool, urlQueue));
        //Thread t3 = new Thread(new MetaThread(pool));

        t1.start();
        // t2.start();
        //t3.start();
    }
}
