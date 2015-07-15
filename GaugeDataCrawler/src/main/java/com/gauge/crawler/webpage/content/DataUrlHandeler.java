/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gauge.crawler.webpage.content;

import com.gauge.crawler.url.urlqueue.UrlQueue;

/**
 *
 * @author Abhay
 */
public class DataUrlHandeler {

    private final UrlQueue urlQueue;
    private static final DataUrlHandeler instance = new DataUrlHandeler();
    String urlString;

    private DataUrlHandeler() {
        this.urlQueue = UrlQueue.getObject();
        this.urlString = this.urlQueue.popUrl();
    }

    public static DataUrlHandeler getObject() {
        return instance;

    }

    public String getCurrentUrl() {
        return null;

    }
    
    public String getNextUrl(){
        return null;
        
    }
    
    public String getYear(){
        return null;
        
    }
    public String getFileName(){
        return null;
        
    }

    public String getCourtName(){
        return null;
        
    }
}
