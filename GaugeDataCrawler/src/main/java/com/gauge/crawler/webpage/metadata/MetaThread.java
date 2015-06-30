/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gauge.crawler.webpage.metadata;

import org.apache.commons.pool.impl.SoftReferenceObjectPool;

/**
 *
 * @author Abhay
 */
// This thread class will responsible for downloading meta-data and meta-datahtml page
public class MetaThread implements Runnable {

    public SoftReferenceObjectPool pool;

    public MetaThread(SoftReferenceObjectPool pool) {
        this.pool = pool;
    }

    @Override
    public void run() {
        System.out.println("Meta Thread created.......");
    }

}
