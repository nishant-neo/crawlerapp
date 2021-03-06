/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gauge.crawler.webpage.downloader;

import com.gauge.crawler.browser.BrowserAgent;
import com.gauge.crawler.browser.BrowserAgentPool;
import com.gauge.crawler.commons.Validator;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.tidy.Tidy;

/**
 *
 * @author Abhay
 */
// This class will responsible for validating page before downloading
public class PageValidator implements Validator {

    BrowserAgent browserAgent;
    BrowserAgentPool pool;

    PageValidator() {
        this.pool = BrowserAgentPool.getPoolObject();
    }

    @Override
    public boolean isValid(Object field) {
        boolean validCheck = false;
        try {
            try {
                browserAgent = (BrowserAgent) pool.borrowObject();                    //create new userAgent (headless browser).
            } catch (Exception ex) {
                Logger.getLogger(PageValidator.class.getName()).log(Level.SEVERE, null, ex);
            }
            String htmlData = (String) field;

            Tidy tidy = new Tidy();
            InputStream stream = new ByteArrayInputStream(htmlData.getBytes());
            tidy.parse(stream, System.out);
            validCheck = tidy.getParseErrors() == 0;
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            try {
                pool.returnObject(browserAgent);
            } catch (Exception ex) {
                Logger.getLogger(PageValidator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return validCheck;
    }
    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

}
