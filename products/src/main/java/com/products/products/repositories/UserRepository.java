package com.products.products.repositories;

import com.products.products.UserManagement.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, String> {
}
