package org.bank.dao;

import org.bank.entities.Transactions;
import org.bank.util.SQLConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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

    @Override
    public List<Transactions> getAllTransactions() {
        return null;
    }
}
