/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package querybasedcrawler;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.pool.impl.SoftReferenceObjectPool;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 *
 * @author Abhay
 */
public class ManageDriver {

    Vpn vpn;
    Queue driverQueue; //this Queue is used for store driver object for control vpn scheduler

    public ManageDriver() {
        vpn = new Vpn();
        driverQueue = new LinkedList();

    }

    // This method is used for creating new webdriver object using DesiredCapabilities
    public WebDriver getDriver() throws IOException {
        return new FirefoxDriver(vpn.vpn());
    }

    // This method is used for deleting bad driver object
    void deleteDriver(WebDriver driver) {
        driver.quit();
    }

    // This method is used for validating driver object
    public boolean validateDriver(boolean valid) {
        return valid;

    }

    // This method is used for sheduling driver object to scrapper
    public synchronized WebDriver scheduledriver(WebDriver driver, SoftReferenceObjectPool pool) {
        System.out.println("Scheduler called");
        WebDriver temp = driver;
        try {
            driver = (WebDriver) pool.borrowObject();
        } catch (Exception ex) {
            Logger.getLogger(ManageDriver.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.driverQueue.add(temp);
        if (this.driverQueue.size() >= 2) {
            try {
                // This condition is used for creating total number of driver object
                pool.returnObject(this.driverQueue.remove());
            } catch (Exception ex) {
                Logger.getLogger(ManageDriver.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return driver;
    }

}
