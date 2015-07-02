/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gauge.crawler.url;

import com.gauge.crawler.commons.Validator;

/**
 *
 * @author Abhay
 */
public class UrlValidatorByRobotTxt implements Validator {

    @Override
    public boolean isValid(Object field) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String robots = field.toString();
        String link = "http://www.flipkart.com/reviews/";
        //Some method to access the file that contains the robots.txt for a particular domain. 
        //String robots = "";// Convert the contents to String data  type
        int i ,j, sent = 0, us, next;
        //Extracting all the disallowed links and comparing to the url provided
        while( robots.indexOf("User-agent: *", sent) != -1)
        {
            j = robots.indexOf("User-agent: *", sent) ;
            next = robots.indexOf("User-agent: *", j+12); 
            if( next == -1)
                next = robots.length()-1;
            while( robots.indexOf( "Disallow: ", j)!= -1 && robots.indexOf( "Disallow: ", j) <next )
            {
                i = robots.indexOf( "Disallow: ",j) + 9;
                j = robots.indexOf( "\n", i);
                if( j == -1)
                    j = robots.length() - 1;
                String sub = robots.substring(i,j);
                //System.out.println(sub);
                if (link.indexOf(sub)==-1)
                      return false;
                sent = j;
            }
        }
        return true;
        
    }
    
}
