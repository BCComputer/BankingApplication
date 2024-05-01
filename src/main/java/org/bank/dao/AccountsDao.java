package org.bank.dao;

import java.util.List;

public interface AccountsDao {
    public List<Integer> getAccountByUserName(int userName);
    public int updateUser(int id, double balance);
}
