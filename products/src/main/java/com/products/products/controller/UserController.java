package com.products.products.controller;

import com.products.products.UserManagement.Users;
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public class UserController {

    private ResponseEntity<Users> verifyLogin(@RequestBody Users users){
        //Get the login from user and verify whether the credentials is present from DB say authenticate or not
    return null;
    }


}
