/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gauge.crawler.proxy;

import com.gauge.crawler.browser.BrowserAgent;
import java.util.Scanner;

/**
 *
 * @author Abhay
 */
// This class would be responsible for handeling proxy
public class ProxyHandeler {

    private final ThreadLevelProxy threadLevelProxy;
    private final SystemLevelProxy systemLevelProxy;
    BrowserAgent browserAgent;
    int proxyType;
    Scanner sc;

    public ProxyHandeler() {
        sc = new Scanner(System.in);
        threadLevelProxy = new ThreadLevelProxy();
        systemLevelProxy = new SystemLevelProxy();
    }

    // This method will return the proxy type object
    public void setProxyType() {
        if (this.proxyType == 0) {
            this.getProxyType();
        }
        if (this.proxyType == 1) {
            threadLevelProxy.setProxy();
            this.browserAgent = threadLevelProxy.browserAgent;
        } else if (this.proxyType == 2) {
            systemLevelProxy.setProxy();
            //this.browserAgent = browserAgent.getBrowserAgent();
        }
    }

    // This method will used for get Browser Agent with Proxy
    public BrowserAgent getBrowserAgent() {
        this.setProxyType();
        if (this.proxyType == 2 || this.proxyType == 3) {// This will be called when user want SystemLevel Proxy or No Proxy
            return new BrowserAgent();
        } else {
            return this.browserAgent;
        }
    }

    private void getProxyType() {// This method will use to get proxy type fro user
        int tmp = 0;
        System.out.println("Enter 1 for Thread Level Proxy, 2 for System Level Proxy and 3 for No proxy");
        do {                     // This do while loop will use to take correct input from user
            if (tmp > 0) {
                System.out.println("Enter the correct option, Try again");
                tmp = 0;
            }
            // proxyType = sc.nextInt(); for testing , we have commented
            this.proxyType = 2;

            tmp++;
        } while (this.proxyType != 1 && this.proxyType != 2 && this.proxyType != 3);

    }
}
