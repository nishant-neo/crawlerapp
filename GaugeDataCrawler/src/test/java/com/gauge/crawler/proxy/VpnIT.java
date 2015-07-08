/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gauge.crawler.proxy;

import java.util.ArrayList;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Abhay
 */
public class VpnIT {

    static Vpn instance;

    public VpnIT() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        System.out.println("SetUp for test is completed");
        instance = new Vpn();
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        instance = null;
        System.out.println("Resources Closed ");
    }

    /**
     * Test of getVpnList method, of class Vpn.
     */
    @Test
    public void testGetVpnList() throws Exception {
        System.out.println("Running test of getVpnList");
        try {
            ArrayList<String> myReturnedObject = instance.getVpnList();

            assertNotNull(myReturnedObject); //check if the object is != null

            assertTrue(myReturnedObject instanceof ArrayList); //checks if the returned object is of class ArrayList

        } catch (Exception e) {
            // let the test fail, if your function throws an Exception.
            fail("Test of getVpnList is failed");
        }
    }

    /**
     * Test of addVpn method, of class Vpn.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testAddVpn() throws Exception {
        System.out.println("Running test of addVpn");

        instance.addVpn();// This method only adding vpn to vpnList
        assertNotNull(instance.vpnList.size()); // We have to check this list is not null

    }

    /**
     * Test of updateVpn method, of class Vpn.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateVpn() throws Exception {
        System.out.println("Running test of updateVpn");
        instance.addVpn();// This method only updating vpn to vpnList
        assertNotNull(instance.vpnList.size()); // We have to check this list is not null
    }

    /**
     * Test of getVpn method, of class Vpn.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGetVpn() throws Exception {
        System.out.println("Running test of getVpn");
        try {
            String myReturnedObject = instance.getVpn();

            assertNotNull(myReturnedObject); //check if the object is != null

            assertTrue(myReturnedObject instanceof String); //checks if the returned object is of class String

        } catch (Exception e) {
            // let the test fail, if your function throws an Exception.
            fail("Test of getVpn is failed");
        }
    }

}
