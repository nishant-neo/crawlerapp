/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gauge.crawler.commons;

/**
 *
 * @author Abhay
 * @param <T>
 */
//This VAlidator interface has one method called isValid ,and all validator class will extend this interface
public interface Validator<T> {

    boolean isValid(T fiel);
}
