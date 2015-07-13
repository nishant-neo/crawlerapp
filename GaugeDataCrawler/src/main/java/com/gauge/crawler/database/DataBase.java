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
public abstract class DataBase<T> {

    public abstract void conectToDbServer();

    public abstract void conectToDataBase();

    public abstract void excuteQuery(T query);
}
