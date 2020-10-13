package com.fivesix.fivesixserver.entity;

public class User {
    private Long id;
    private String userName;
    private String password;

    public Long getId() { return this.id;}

    public void setId(Long id) { this.id = id; }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "用户名:" + this.userName;
    }
}
