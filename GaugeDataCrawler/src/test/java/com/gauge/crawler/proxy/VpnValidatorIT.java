/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gauge.crawler.proxy;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Abhay
 */
public class VpnValidatorIT {

    public static ArrayList<String> vpnPassList; // Valid list of vpn
    public static ArrayList<String> vpnFailList; // Invalid list of vpn

    public VpnValidatorIT() {

    }

    @BeforeClass
    public static void setUpClass() {
        vpnPassList = new ArrayList();
        vpnFailList = new ArrayList();
        String[] list = {"183.207.229.204:9000", "120.203.148.7:8118", "103.249.91.193:8080", "61.234.249.125:8118", "42.120.81.28:80"};
        vpnPassList.addAll(Arrays.asList(list));
        String[] list2 = {"183.207.229.2049000", "183207.229.204:9000", "103.249..193:8080", "103.249.91.193:"};
        vpnFailList.addAll(Arrays.asList(list2));
        System.out.println("SetUp completed ");
    }

    @AfterClass
    public static void tearDownClass() {
        vpnPassList = null;
        vpnFailList = null;
        System.out.println("Resources Closed ");
    }

    /**
     * Test of isValid method, of class VpnValidator.
     */
    @Test
    public void testIsValid() {

        System.out.println("Test method testIsValid is running ");

        for (String vpnT : vpnPassList) {// This loop will work for valid vpnlist (Expected result is True)
            //System.out.println("isValid");
            String vpn = vpnT;
            VpnValidator instance = new VpnValidator();
            boolean expResult = true;
            boolean result = instance.isValid(vpn);
            assertEquals(expResult, result);
        }

        for (String vpnT : vpnFailList) { //This loop will work for invalid vpnlist (Expected result is False)
            //System.out.println("isValid");
            String vpn = vpnT;
            VpnValidator instance = new VpnValidator();
            boolean expResult = false;
            boolean result = instance.isValid(vpn);
            assertEquals(expResult, result);
        }

    }

}
