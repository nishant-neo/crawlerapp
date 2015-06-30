/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gauge.crawler.proxy;

/**
 *
 * @author Abhay
 */
// This class will use to set proxy type , and getProxy method will return the proxy type
public class ProxyType {

    // This method will return the proxy type object
    public Proxy getProxy(int flag) {
        if (flag == 0) {
            return new ThreadLevelProxy();
        } else {
            return new SystemLevelProxy();
        }
    }
}
