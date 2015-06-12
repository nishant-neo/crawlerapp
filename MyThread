/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package querybasedcrawler;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.pool.impl.SoftReferenceObjectPool;
import org.openqa.selenium.WebDriver;

/**
 *
 * @author abhay
 */
public class MyThread extends Thread {

    WebDriver driver;
    int visitedUrlCounter;
    public static final Scrapper scrapper = new Scrapper();
    private static final DriverFactory driverFactory = new DriverFactory();
    private static final SoftReferenceObjectPool pool = new SoftReferenceObjectPool((driverFactory));

    public MyThread() throws Exception {
        driver = (WebDriver) pool.borrowObject();
    }

    @Override
    public void run() { 

        String url = scrapper.getUrl();
        while (url != null) {

            while (!scrapper.openWebsite(this.driver, url)) {// This loop is used for revisiting uncompleted url
                System.out.println("Time out for this url - " + url);
                driverFactory.manageDriver.deleteDriver(this.driver);
                try {
                    this.driver = (WebDriver) pool.borrowObject();
                } catch (Exception ex) {
                    Logger.getLogger(MyThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            // driverFactory.dr.validateDriver(scrapper.openWebsite(this.driver, url));
            this.visitedUrlCounter++;
            if (this.visitedUrlCounter >= 2) {// This condition is used for scheduling driver after visitedUrl condition
                this.driver = driverFactory.manageDriver.scheduledriver(this.driver, pool);
                this.visitedUrlCounter = 0;
            }
            System.out.println("This url successfully visited " + url);
            url = scrapper.getUrl();
        }
        driverFactory.manageDriver.deleteDriver(this.driver);

    }
}
