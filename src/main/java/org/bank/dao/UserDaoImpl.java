package org.bank.dao;

import org.bank.entities.User;
import org.bank.util.SQLConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public int createUser(User user) {
        int status = 0;

        String sqlStatement = "INSERT INTO USERS (firstName, lastName, address, email, phonenumber, username, password_hash)" +
                "VALUES(?, ?, ?, ?, ?,?,?)";
        try (Connection con = SQLConnector.createConnection()) {
            PreparedStatement insertStatement = con.prepareStatement(sqlStatement);
            insertStatement.setString(1, user.getFirstName());
            insertStatement.setString(2, user.getLastName());
            insertStatement.setString(3, user.getAddress());
            insertStatement.setString(4, user.getEmail());
            insertStatement.setString(5, user.getPhoneNumber());
            insertStatement.setString(6, user.getUsername());
            insertStatement.setString(7, user.getPassword_hash());

            insertStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return status;
    }

    @Override
    public int updateUser(int user_id, User user) {

        try(Connection connection = SQLConnector.createConnection()){
            String sql = "update users set firstName=?, lastName=?, address=?, email=?, phonenumber=?, username=?, password_hash=? where user_id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getAddress());
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getPhoneNumber());
            statement.setString(6, user.getUsername());
            statement.setString(7, user.getPassword_hash());
            statement.setInt(8, user_id);

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 1;
    }


    @Override
    public int deleteUser(int user_id) {
        try(Connection connection = SQLConnector.createConnection()) {
            String sql = "delete from users where user_id=?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,user_id);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    @Override
    public List<User> getAllUser() {

            List<User> users = new ArrayList<>();
            try(Connection con = SQLConnector.createConnection()){
                String sql =  "select*from users";

                PreparedStatement preparedStatement = con.prepareStatement(sql);

                ResultSet resultData = preparedStatement.executeQuery();

                while (resultData.next()){
                    User user = new User();
                    user.setUser_id(resultData.getInt(1));
                    user.setFirstName(resultData.getString(2));
                    user.setLastName(resultData.getString(3));
                    user.setAddress(resultData.getString(4));
                    user.setEmail(resultData.getString(5));
                    user.setPhoneNumber(resultData.getString(6));
                    user.setUsername(resultData.getString(7));
                    user.setPassword_hash(resultData.getString(8));

                    users.add(user);
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
            return users;
    }

    @Override
    public User getUserByUserName(String userName) {
        User user = new User();
        try(Connection con = SQLConnector.createConnection()){
            String sql =  "select user_id, firstName, lastName, address, email, phonenumber, username, password_hash from users where username = ?";

            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1,userName);

            ResultSet resultData = preparedStatement.executeQuery();

            while (resultData.next()){
                user.setUser_id(resultData.getInt(1));
                user.setFirstName(resultData.getString(2));
                user.setLastName(resultData.getString(3));
                user.setAddress(resultData.getString(4));
                user.setEmail(resultData.getString(5));
                user.setPhoneNumber(resultData.getString(6));
                user.setUsername(resultData.getString(7));
                user.setPassword_hash(resultData.getString(8));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return user;
    }
}
