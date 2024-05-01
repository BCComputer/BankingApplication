package org.bank.dao;

import org.bank.entities.User;

import java.util.List;

public interface UserDao {
    public int createUser(User user);
    public List<User> getAllUser();
    public User getUserByUserName(String userName);
}
