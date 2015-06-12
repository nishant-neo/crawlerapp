/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package querybasedcrawler;

/**
 *
 * @author abhay
 */
public class QueryBasedCrawler {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws Exception {

        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();
        MyThread t3 = new MyThread();

        System.out.println(MyThread.scrapper.urlList);
        
        t1.start();
        t2.start();
        t3.start();

        
    }

}
