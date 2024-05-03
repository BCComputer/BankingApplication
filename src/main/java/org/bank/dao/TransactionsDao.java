package org.bank.dao;

import org.bank.entities.Transactions;

import java.util.List;

public interface TransactionsDao {
    public List<Transactions> getAllTransactions(int accountId);
}
