package org.bank.entities;

public class Login {
    private int userId;
    private String username;
    private String passwordHash;
    private boolean isLogin = false;

    public Login(int userId, String username, String passwordHash, Boolean isLogin) {
        this.userId = userId;
        this.username = username;
        this.passwordHash = passwordHash;
        this.isLogin=isLogin;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {

        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    @Override
    public String toString() {
        return "Login{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", isLogin=" + isLogin +
                '}'+"\n";
    }
}
