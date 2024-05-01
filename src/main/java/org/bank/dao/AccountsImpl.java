package org.bank.dao;

import org.bank.entities.Account;
import org.bank.util.SQLConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountsImpl implements AccountsDao{
    public int createAnAccount(int user_id, String account_type, double balance) {

        int status = 0;

        String sqlStatement = "INSERT INTO ACCOUNTS (user_id, account_type, balance)" +
                "VALUES(?, ?, ?)";
        try (Connection con = SQLConnector.createConnection()) {
            PreparedStatement insertStatement = con.prepareStatement(sqlStatement);
            insertStatement.setInt(1, user_id);
            insertStatement.setString(2, account_type);
            insertStatement.setDouble(3, balance);

            status = insertStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return status;
    }

    public List<Integer> getAccountByUserName(int userId) {
        List<Integer> myList = new ArrayList<>();
        try (Connection con = SQLConnector.createConnection()) {

            String sql = " select*from accounts where user_id = ?";

            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, userId);

            ResultSet resultData = preparedStatement.executeQuery();
            while (resultData.next()) {
                myList.add(resultData.getInt(1));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return myList;
    }

    @Override

    public int updateUser(int id, double balance) {
        int status = 0;
        try(Connection connection = SQLConnector.createConnection()){
            String sql = "update accounts set balance=? where account_id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1, balance);
            statement.setInt(2, id);
            status = statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return status;
    }

    public Account getAccountById(int accountId) {
        String sql = "SELECT * FROM accounts WHERE account_id = ?";
        try (Connection con = SQLConnector.createConnection()) {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, accountId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int userId = rs.getInt("user_id");
                String accountType = rs.getString("account_type");
                double balance = rs.getDouble("balance");
                return new Account(accountId, userId, accountType, balance);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateAccountBalance(Account account) {
        String sql = "UPDATE accounts SET balance = ? WHERE account_id = ?";
        try (Connection con = SQLConnector.createConnection()) {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setDouble(1, account.getBalance());
            stmt.setInt(2, account.getAccountId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
