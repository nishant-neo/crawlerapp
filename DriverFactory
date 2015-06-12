/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package querybasedcrawler;

import org.apache.commons.pool.BasePoolableObjectFactory;

/**
 *
 * @author Abhay
 */
public class DriverFactory extends BasePoolableObjectFactory {

    public ManageDriver manageDriver;

    public DriverFactory() {
        manageDriver = new ManageDriver();
    }

    @Override
    public Object makeObject() throws Exception {
        return manageDriver.getDriver(); // calling createObject from Vpn class for passing webdriver object to pool
    }
}
