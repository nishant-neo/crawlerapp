/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gauge.crawler.url.urlqueue;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Abhay
 */
//This class will handle push and pop operartion of queue of Url
public class UrlQueue {

    private final Queue<String> urlQueue;
    public HashSet<String> visitedUrl;
    private static final UrlQueue instance = new UrlQueue();

    private UrlQueue() {
        urlQueue = new LinkedList<>();
        visitedUrl = new HashSet();
    }

    public static UrlQueue getObject() {
        return instance;

    }

    //This method would responsible for adding url to queue
    public void pushUrl(String url) {
        urlQueue.add(url);
    }

    // This method would responsible for pop Url from url queue
    public String popUrl() {
        String temp = urlQueue.remove();
        return temp;

    }

    // This method add Visited url to used list
    public boolean addToVisitedList(String url) {
        boolean temp = this.visitedUrl.add(url);
        return temp;

    }

    public int length() {
        return this.urlQueue.size();
    }
}
