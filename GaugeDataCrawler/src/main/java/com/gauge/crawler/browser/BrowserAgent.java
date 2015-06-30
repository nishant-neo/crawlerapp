/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gauge.crawler.browser;

import com.jaunt.UserAgent;
import java.io.IOException;

/**
 *
 * @author Abhay
 */
// This class will responsible for creating browser agent, deleting browser object
public class BrowserAgent {

    //This method will used for get BrowserAgent Insance
    public UserAgent getBrowserAgent() {
        return new UserAgent();

    }

    // This method will used for deleting given UserAgent instance
    public void deleteBrowserAgent(UserAgent browserAgent) throws IOException {
        browserAgent.close();
    }

}
