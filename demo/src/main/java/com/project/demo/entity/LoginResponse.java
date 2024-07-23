package com.project.demo.entity;

import com.project.demo.entity.User;

public class LoginResponse {
    private String token;

    private User authUser;

    private long expiresIn;

    private Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUser_id(Integer userId) {
        this.userId = userId;
    }
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public User getAuthUser() {
        return authUser;
    }

    public void setAuthUser(User authUser) {
        this.authUser = authUser;
    }
}
