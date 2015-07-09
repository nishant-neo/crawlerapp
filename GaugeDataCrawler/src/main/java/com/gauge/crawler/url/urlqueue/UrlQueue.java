/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gauge.crawler.url.urlqueue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Abhay
 */
//This class will handle push and pop operartion of queue of Url
public class UrlQueue {

    private final Queue<String> urlQueue;
    private ArrayList<String> visitedUrl;

    public UrlQueue() {
        urlQueue = new LinkedList<>();
    }

    //This method would responsible for adding url to queue
    public void pushUrl(String url) {
        urlQueue.add(url);
    }

    // This method would responsible for pop Url from url queue
    public String popUrl() {
        String temp = urlQueue.peek();
        return temp;

    }

    // This method add Visited url to used list
    public void addToVisitedList(String url) {
        this.visitedUrl.add(url);
    }
}
