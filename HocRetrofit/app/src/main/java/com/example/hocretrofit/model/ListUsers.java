package com.example.hocretrofit.model;

import java.util.ArrayList;

public class ListUsers {
    ArrayList<User>data;

    public ListUsers() {
    }

    public ListUsers(ArrayList<User> data) {
        this.data = data;
    }

    public ArrayList<User> getData() {
        return data;
    }

    public void setData(ArrayList<User> data) {
        this.data = data;
    }
}
