/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gaugeanalytics.gaugedatacrawler.validator;

/**
 *
 * @author Abhay
 * @param <T>
 */
public interface Validator<T> {

    boolean isValid(T field);
}