/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gauge.crawler.proxy;

import com.gauge.crawler.browser.BrowserAgent;

/**
 *
 * @author Abhay
 */
// This class will handle SystemLevel proxy if user want to use only one Vpn for all thread then instance will called of this class
public class SystemLevelProxy implements Proxy {

    BrowserAgent browserAgent;
    private final Vpn vpn;

    public SystemLevelProxy() {
        vpn = new Vpn();
    }

    @Override
    public void setProxy() {// This method would used to setSystemLevel Proxy

        System.setProperty("http.proxyHost", "host");
        System.setProperty("http.proxyPort", "port_number");
    }

}
