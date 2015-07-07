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

    //public static ArrayList<String> vpnList;
    public VpnValidatorIT() {
        // vpnList = new ArrayList();
    }

    @BeforeClass
    public static void setUpClass() {
        // String[] list = {"183.207.229.204:9000", "120.203.148.7:8118", "103.249.91.193:8080", "61.234.249.125:8118", "42.120.81.28:80"};
        // vpnList.addAll(Arrays.asList(list));
    }

    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of isValid method, of class VpnValidator.
     */
    @Test
    public void testIsValid() {

        System.out.println("isValid");
        String vpn = "183.207.229.204:9000";
        VpnValidator instance = new VpnValidator();
        boolean expResult = true;
        boolean result = instance.isValid(vpn);
        assertEquals(expResult, result);

        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

}
