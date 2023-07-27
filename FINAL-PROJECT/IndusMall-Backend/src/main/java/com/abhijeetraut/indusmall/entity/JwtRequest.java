package com.abhijeetraut.indusmall.entity;

/**
 * Created By Abhijeet Raut on || Date : 21-04-2023 ||  Time : 10:11 AM.
 */
public class JwtRequest {

    private String userName;
    private String userPassword;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
