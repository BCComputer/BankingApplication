package org.bank.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnector {
    public Connection createConnection(){
        Connection con=null;
        final String USERNAME= "root";
        final String PASSWORD = "1234";
        final String DB_NAME = "highschool";
        final String DB_URL= "jdbc:mysql://localhost:3306/" + DB_NAME;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return con;
    }
}
