package com.example.recycleview;

public class User {
    int id;
    String name,email,phone,password;
    public User(int id, String email, String password) {
       this.id=id;
        this.email=email;

        this.password=password;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }
}
