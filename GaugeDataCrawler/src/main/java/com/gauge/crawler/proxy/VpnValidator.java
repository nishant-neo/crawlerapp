/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gauge.crawler.proxy;

import com.gauge.crawler.commons.Validator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author nitin
 */
// This class will rfesponsible for validation of vpn server
public class VpnValidator implements Validator {

    @Override // This method will tell given vpn is valid or not
    public boolean isValid(Object vpn) {
        Pattern p = Pattern.compile("(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}):(\\d{1,5})");
        Matcher m = p.matcher((CharSequence) vpn);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }

}
