package org.bank.dao;

import org.bank.entities.Transactions;

import java.util.List;

public interface TransactionsDao {
    int deposit(Transactions transactions);
    public List<Transactions> getAllTransactions();
}
