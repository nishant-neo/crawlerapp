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

    private static ProxyHandeler instance;

    public ProxyHandelerIT() {
        instance = new ProxyHandeler();
    }

    @BeforeClass
    public static void setUpClass() {

        System.out.println("SetUp completed ");
    }

    @AfterClass
    public static void tearDownClass() {
        instance = null;
        System.out.println("Resources Closed ");
    }

    /**
     * Test of getBrowserAgent method, of class ProxyHandeler.
     */
    @Test
    public void testGetBrowserAgent() {
        System.out.println("Test method getBrowserAgent is running");
        try {
            BrowserAgent myReturnedObject = instance.getBrowserAgent();

            assertNotNull(myReturnedObject); //check if the object is != null

            assertTrue(myReturnedObject instanceof BrowserAgent); //checks if the returned object is of class String

        } catch (Exception e) {
            // let the test fail, if your function throws an Exception.
            fail("Test of getVpn is failed");
        }
    }

}
