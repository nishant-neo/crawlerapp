/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gauge.crawler.proxy;

import com.gauge.crawler.browser.BrowserAgent;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Abhay
 */
public class ProxyHandelerIT {

    public ProxyHandelerIT() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of setProxyType method, of class ProxyHandeler.
     */
    @Test
    public void testSetProxyType() {
        System.out.println("setProxyType");
        ProxyHandeler instance = new ProxyHandeler();
        instance.setProxyType();
     // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");

    }

    /**
     * Test of getBrowserAgent method, of class ProxyHandeler.
     */
    @Test
    public void testGetBrowserAgent() {
        System.out.println("getBrowserAgent");
        ProxyHandeler instance = new ProxyHandeler();
        BrowserAgent expResult = instance.getBrowserAgent();
        BrowserAgent result = instance.getBrowserAgent();
        assertEquals(expResult, result);
        System.out.println("Test CAse excuted");
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

}
