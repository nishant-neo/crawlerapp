/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gauge.crawler.url;

import com.gauge.crawler.commons.Validator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Abhay
 */
public class UrlValidator implements Validator {

    @Override
    public boolean isValid(Object field) {
        boolean validCheck;
        String UrlS = (String)field;
        Pattern pattern;
        String regex = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
        pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(UrlS);
        validCheck = matcher.matches();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return validCheck;
    }

}
