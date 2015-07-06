/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gauge.crawler.proxy;

import au.com.bytecode.opencsv.CSVReader;
import java.io.FileReader;
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

    public VpnIT() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of getVpnList method, of class Vpn.
     *
     * @throws java.lang.Exception
     */
    /*
     @Test
     public void testGetVpnList() throws Exception {
     String vpnCsv = "Program-File/vpn.csv";
     CSVReader br = new CSVReader(new FileReader(vpnCsv));

     System.out.println("getVpnList");
     Vpn instance = new Vpn();
     ArrayList<String> expResult = instance.getVpnList();
     ArrayList<String> result = instance.getVpnList();
     assertEquals(expResult, result);
     // TODO review the generated test code and remove the default call to fail.
     fail("The test case is a prototype.");
     }
     */
    /**
     * Test of addVpn method, of class Vpn.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testAddVpn() throws Exception {
        System.out.println("addVpn");
        Vpn instance = new Vpn();
        instance.addVpn();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateVpn method, of class Vpn.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateVpn() throws Exception {
        System.out.println("updateVpn");
        Vpn instance = new Vpn();
        instance.updateVpn();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeVpn method, of class Vpn.
     */
    @Test
    public void testRemoveVpn() {
        System.out.println("removeVpn");
        String vpn = "183.207.229.204:9000";
        Vpn instance = new Vpn();
        instance.removeVpn(vpn);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getVpn method, of class Vpn.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGetVpn() throws Exception {
        System.out.println("getVpn");
        Vpn instance = new Vpn();
        String expResult = "183.207.229.204:9000";
        String result = instance.getVpn();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
