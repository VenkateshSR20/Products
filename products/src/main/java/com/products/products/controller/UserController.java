package com.products.products.controller;

import com.products.products.UserManagement.Users;
import com.products.products.repositories.UserRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Tag(name = "Login", description = "Login for accessing project")
@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/login")
public class UserController {

    private final UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/loginverify/{username}")
    private ResponseEntity<Users> verifyLogin(@PathVariable String username) {
        //Get the login from user and verify whether the credentials is present from DB say authenticate or not
        final Optional<Users> users = this.repository.findByUsername(username);

        if (users.isPresent()) {
            System.out.println("User  found");
            users.get().getPassword().equals("password");
        } else {
            System.out.println("User not found");

        }
        return ResponseEntity.ok(users.get());
    }

    //Insert into DB with user name and password and store in DB encrypt of password

}
