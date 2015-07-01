/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gauge.crawler.query;

import com.gauge.crawler.commons.Validator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 *
 * @author Abhay
 */

// This class will used to validate date fromat of uery
public class DateValidator implements Validator {

    @Override
    public boolean isValid(Object fiel) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    String pattern = "^(0[1-9]|[12][0-9]|3[01])[/](0[1-9]|1[012])[/](19|20)\\d\\d$"; // the date format is dd/mm/yyyy
    Pattern r = Pattern.compile(pattern);
    Matcher m = r.matcher((CharSequence) fiel);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }
}
