package org.bank.dao;

import org.bank.entities.Transactions;
import org.bank.util.SQLConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionsImpl implements TransactionsDao {


    public void addTransaction(int accountId, String transactionType, double amount, String description) {
        String sql = "INSERT INTO transactions (account_id, transaction_type, amount, description) VALUES (?, ?, ?, ?)";
        try (Connection con = SQLConnector.createConnection()) {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, accountId);
            stmt.setString(2, transactionType);
            stmt.setDouble(3, amount);
            stmt.setString(4, description);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int deposit(Transactions transactions) {
        return 0;
    }


    public List<Transactions> getAllTransactions(int account_id) {

        List<Transactions> transactions = new ArrayList<>();
        String sql = "select account_id, transaction_type, amount, description from transactions where account_id = ?";
        try (Connection con = SQLConnector.createConnection()) {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, account_id);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Transactions myTransaction = new Transactions();
                myTransaction.setAccount_id(rs.getInt(1));
                myTransaction.setTransaction_type(rs.getString(2));
                myTransaction.setAmount(rs.getDouble(3));
                myTransaction.setDescription(rs.getString(4));
                transactions.add(myTransaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }

}
