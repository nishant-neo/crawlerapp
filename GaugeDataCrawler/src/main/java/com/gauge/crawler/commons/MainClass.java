/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gauge.crawler.commons;

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

    public static void main(String[] args) throws Exception {
        System.out.println("hiii im working...");
        PathValidator p = new PathValidator();
        p.isValid("/home/nitin/NetBeansProjects/GaugeAnalytics/gauge-data/GaugeDataCrawler/nbactions.xml");
        //Thread t1 = new Thread(new DataThread(pool));
        //Thread t2 = new Thread(new DataThread(pool));
        //Thread t3 = new Thread(new MetaThread(pool));

       // t1.start();
        //t2.start();
        //t3.start();
    }
}
