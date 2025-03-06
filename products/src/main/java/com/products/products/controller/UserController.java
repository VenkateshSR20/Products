package com.products.products.controller;

import com.products.products.UserManagement.Users;
import com.products.products.repositories.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Tag(name = "Login", description = "Login for accessing project")
@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/login")
public class UserController {



    @Autowired
    private final UserRepository userRepository;

    public UserController(UserRepository repository) {
        this.userRepository = repository;
    }

    @GetMapping("/loginverify/{username}")
    private ResponseEntity<Users> verifyLogin(@PathVariable String username) {
        //Get the login from user and verify whether the credentials is present from DB say authenticate or not
        final Optional<Users> users = this.userRepository.findByUsername(username);

        if (users.isPresent()) {
            System.out.println("User  found");
            users.get().getPassword().equals("password");
        } else {
            System.out.println("User not found");

        }
        return ResponseEntity.ok(users.get());
    }

    @Operation(summary = "Create credentials", tags = { "login", "post"})
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {
                    @Content(schema = @Schema(implementation = Users.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @PostMapping("/Createlogin")
    private ResponseEntity<Users> createEncPassword(@RequestBody Users users) {
       // Users saveuser =
        //Get value here and encry the pwd and store.

        return null;
    }
    //Insert into DB with user name and password and store in DB encrypt of password
    //Logic security config


}
