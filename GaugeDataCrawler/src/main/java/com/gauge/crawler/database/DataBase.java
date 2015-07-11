/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gauge.crawler.database;

/**
 *
 * @author Abhay
 * @param <T>
 */
public interface DataBase<T> {

    public void conectToDbServer();

    public void conectToDataBase();

    public void excuteQuery(T query);
}
