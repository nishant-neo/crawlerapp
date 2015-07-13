/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gauge.crawler.webpage.content;

import com.gauge.crawler.gaugefile.FilePathHandeler;
import com.gauge.crawler.url.urlqueue.UrlQueue;
import org.apache.commons.pool.impl.SoftReferenceObjectPool;

/**
 *
 * @author Abhay
 */
// This thread class will responsible for downloading text , pdf and data-html page
public class DataThread implements Runnable {

    DataConentFile dataConentFile;
    FilePathHandeler filePath;

    public DataThread(SoftReferenceObjectPool pool, UrlQueue urlQueue) throws Exception {
        dataConentFile = new DataConentFile(pool, urlQueue);
        filePath = new FilePathHandeler();
    }

    @Override
    public void run() {

    }

}
