package org.bank.controller;

import org.bank.util.SQLConnector;

import java.sql.Connection;

public class Controller {
    public static void main(String[] args) {
        SQLConnector connector = new SQLConnector();

        Connection connection = connector.createConnection();
        System.out.println(connection);}

}
