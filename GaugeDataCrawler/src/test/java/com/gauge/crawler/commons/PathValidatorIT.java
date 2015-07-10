/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gauge.crawler.commons;

import com.gauge.crawler.gaugefile.FilePathValidator;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Abhay
 */
public class PathValidatorIT {

    static FilePathValidator instance;

    public PathValidatorIT() {
    }

    @BeforeClass
    public static void setUpClass() {
        instance = new FilePathValidator();
        System.out.println("SetUp completed ");
    }

    @AfterClass
    public static void tearDownClass() {
        instance = null;
        System.out.println("Resources Closed ");
    }

    /**
     * Test of isValid method, of class PathValidator.
     */
    @Test
    public void testIsValid() {
        System.out.println("isValid");
        Object folderPath = "/home/nitin/NetBeansProjects/GaugeAnalytics/gauge-data/GaugeDataCrawler/Program-File";
        boolean expResult = true;
        boolean result = instance.isValid(folderPath);
        assertEquals(expResult, result);
    }

}
