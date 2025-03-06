package com.products.products.Service;

import com.products.products.UserManagement.Users;
import com.products.products.repositories.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Users saveUser(Users users){
        return userRepository.save(users);
    }

}
