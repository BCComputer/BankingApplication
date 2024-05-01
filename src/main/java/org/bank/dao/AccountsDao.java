package org.bank.dao;

import org.bank.entities.Account;

import java.util.Map;

public interface AccountsDao {
    public Map<Integer, String> getAccountByUserName(int userName);

    public int updateUser(int id, double balance);

    public int createAnAccount(int user_id, String account_type, double balance);

    public Account getAccountById(int accountId);

    public void updateAccountBalance(Account account);
}