/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gauge.crawler.proxy;

import com.gauge.crawler.browser.BrowserAgent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Abhay
 */
// This class will handle Thread Level proxy, if user want to use proxy on thread level,  then instance will called of this class
public class ThreadLevelProxy implements Proxy {

    BrowserAgent browserAgent;
    private final Vpn vpn;

    public ThreadLevelProxy() {
        //browserAgent = browserAgent.getBrowserAgent();
        vpn = new Vpn();
        browserAgent = new BrowserAgent();
    }

    @Override
    public void setProxy() {
        try {
            browserAgent = browserAgent.getBrowserAgent();
            // System.out.println(vpn.getVpn());
            String[] str = vpn.getVpn().split("[: ]");
            System.out.println(str[0] + " " + str[1]);
            browserAgent.setProxyHost(str[0].trim());
            browserAgent.setProxyPort(Integer.parseInt(str[1].trim()));
        } catch (IOException ex) {
            Logger.getLogger(ThreadLevelProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
