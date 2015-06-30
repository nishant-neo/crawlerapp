/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gauge.crawler.commons;

/**
 *
 * @author Abhay
 */
// This is an interface that has one method extract, and this interface will implement by DataConentFile class and MetaContentFile class
public interface Content {

    public void extractData();

    public void saveData();
}
