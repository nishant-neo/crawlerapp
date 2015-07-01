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
// This class will handle SystemLevel proxy if user want to use only one Vpn for all thread then instance will called of this class
public class SystemLevelProxy implements Proxy {

    private final Vpn vpn;

    public SystemLevelProxy() {
        vpn = new Vpn();
    }

    @Override
    public void setProxy() {
        try { // This method would used to setSystemLevel Proxy

            String[] str = vpn.getVpn().split("[: ]");
            System.setProperty("http.proxyHost", str[0].trim());
            System.setProperty("http.proxyPort", str[1].trim());
        } catch (IOException ex) {
            Logger.getLogger(SystemLevelProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
