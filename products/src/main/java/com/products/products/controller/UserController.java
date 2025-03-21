package com.products.products.controller;

import com.products.products.Service.UserService;
import com.products.products.UserManagement.Users;
import com.products.products.repositories.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;

@Tag(name = "Login", description = "Login for accessing project")
@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/login")
public class UserController {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;


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
        //while storing the pwd in db use bcrypt object - encrypt it - & store in db

        String plainpassword = users.getPassword();
        String hashedpassword = encryptPassword(plainpassword);
        System.out.println("Plain password : "+plainpassword);
        System.out.println("Hashed password :"+hashedpassword);

        //Saving the data in DB
        //Users saveusername = userService.saveUser(users);
        Users saveuser = userService.saveUser(users);
        saveuser.setUsername(users.getUsername());
        saveuser.setPassword(hashedpassword);
        saveuser = userService.saveUser(saveuser);
        return new ResponseEntity<>(saveuser, HttpStatus.CREATED);

    }
    //Insert into DB with user name and password and store in DB encrypt of password
    //Logic security config
    public static String encryptPassword(String plainpassword){
        return BCrypt.hashpw(plainpassword, BCrypt.gensalt(12));
    }

    //To do -User login end point call, if give user name and pwd then get uname and pwd from DB
    //THen take the pwd use decrypt and bring to text form. then check if it matches with user pwd.
    //Give in Json
    @Operation(summary = "Get credentials", tags = { "login", "get"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = Users.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @PostMapping("/PasswordDecrypt")
    private ResponseEntity<Users> decryptPassword(@RequestBody Users users){

        String encryptedpassword = users.getPassword();
        String username = users.getUsername();
        String hashedPasswordFromDB = getPasswordFromDatabase(username);
        //Because BCrypt uses a hashing function, not encryption.
        //Hashing is one-way, meaning you can only verify if two values match — you cannot retrieve the original password.
        //String hashedPassword = passwordE(users.getPassword());
        //users
        return null;
    }

    private String getPasswordFromDatabase(String username) {

        String hashedPassword = passwordEncoder.encode(username.getPassword());
        users.setPassword(hashedPassword);
        //Spring boot security - Password encoder - add pom.-
        //Use that password verifier - user detail, it self decode.
        //page64(encoder) @bean, it will do password verification process
    }

//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    public void createUser(User user) {
//        String hashedPassword = passwordEncoder.encode(user.getPassword());
//        user.setPassword(hashedPassword);
//        // Save the user to the database
//    }

    //Spring data MQ, Rabbit MQ
}
