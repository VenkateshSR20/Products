package com.products.products.Service;

import com.products.products.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserService {

    @Autowired
    private UserRepository userRepository;

}
