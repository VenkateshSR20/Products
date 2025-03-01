package com.products.products.UserManagement;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "login")
@Data//Lombok instead getters and setters
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String userID;
    @Column(name="username")
    private String username;
    @Column(name="password")
    private String password;

}
