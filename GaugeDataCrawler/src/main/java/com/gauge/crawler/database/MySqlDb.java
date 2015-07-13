/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gauge.crawler.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Abhay
 */
public class MySqlDb extends DataBase {

    Connection connection;
    public Statement statement;
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/GaugeCrawler";
    private static final String USER = "root";
    private static final String PASS = "satluj123";

    public MySqlDb() {
        connection = null;
        statement = null;
    }

    @Override
    public void conectToDataBase() {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your MySQL JDBC Driver?");
            return;
        }

        System.out.println("MySQL JDBC Driver Registered!");
        //connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);

        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            return;
        }

        if (connection != null) {
            System.out.println("You made it, take control your database now!");
        } else {
            System.out.println("Failed to make connection!");
        }
        try {
            statement = connection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(MySqlDb.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void closeDbConnection() {
        try {
            this.statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySqlDb.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            this.connection.close();
            System.out.println("Database Connection closed successfully");
        } catch (SQLException ex) {
            Logger.getLogger(MySqlDb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
