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
// This interface has one method setProxy and it will extend by two classes one is SystemLevelProxy and second one is ThreadLevelproxy
public interface Proxy {

    public void setProxy();
}
