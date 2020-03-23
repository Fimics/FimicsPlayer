package com.mic.user.model;

import java.net.URL;

public class User {
    public URL avatarUrl;
    public String name;
    public String password;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
