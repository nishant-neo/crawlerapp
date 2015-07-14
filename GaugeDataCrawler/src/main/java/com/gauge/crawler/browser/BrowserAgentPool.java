/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gauge.crawler.browser;

import org.apache.commons.pool.BasePoolableObjectFactory;
import org.apache.commons.pool.impl.SoftReferenceObjectPool;

/**
 *
 * @author Abhay
 */
// This class would responsible pasing BrowserAgent Pool, and this also called singelton class
public class BrowserAgentPool extends SoftReferenceObjectPool {

    private static final BrowserAgentFactory factory = new BrowserAgentFactory();
    private static final BrowserAgentPool instance = new BrowserAgentPool(factory);

    private BrowserAgentPool(BasePoolableObjectFactory factory) {
        super(factory);
    }

    public static BrowserAgentPool getPoolObject() {
        return instance;
    }

}
