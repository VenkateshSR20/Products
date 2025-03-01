package com.products.products.controller;

import jakarta.persistence.Column;

public class UsersDTO {

    private String userID;
    private String username;
    private String password;

    private UsersDTO(String userID, String username, String password){
        this.userID = userID;
        this.username = username;
        this.password = password;
    }
}
