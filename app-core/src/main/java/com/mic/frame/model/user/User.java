package com.mic.frame.model.user;

public class User {
    public String id;
    public String name;
    public String password;
    public String email;
    public String avatarUrl;
    public String bigAvatarUrl;
    public String phoneNUm;
    public String address;
    public String token;
    public String sex;
    public String enjoy;
    public String income;
    public String age;
    public String school;



    public User(String id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }
}
