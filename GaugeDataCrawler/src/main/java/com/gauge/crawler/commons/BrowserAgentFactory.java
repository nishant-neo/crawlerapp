/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gauge.crawler.commons;

import com.gauge.crawler.browser.BrowserAgent;
import org.apache.commons.pool.BasePoolableObjectFactory;

/**
 *
 * @author Abhay
 */
// This class is respoonsible for passing Object to Object Pool and it is extending BasePoolableObjectFactory class from Apache commons pool api
public class BrowserAgentFactory extends BasePoolableObjectFactory {

    public BrowserAgent browserAgent;

    public BrowserAgentFactory() {
        browserAgent = new BrowserAgent();
    }

    /**
     *
     * @return @throws Exception
     */
    @Override
    // This method will make the object that would pass to pool
    public Object makeObject() throws Exception {
        return browserAgent.getBrowserAgent();
    }

}
