package org.bank.dao;

import org.bank.entities.Login;
import org.bank.util.SQLConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginImpl {

    public Login login(String username, String password) {

        try (Connection con = SQLConnector.createConnection()) {
            String sql = "select user_id, password_hash from users where username = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String passwordHash = resultSet.getString("password_hash");
                if (passwordHash.equals(password)) {
                    return new Login(username, passwordHash, true);
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}
