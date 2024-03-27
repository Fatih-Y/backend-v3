package com.pinsoft.shopapp.service;

import com.pinsoft.shopapp.dto.DeleteUser;
import com.pinsoft.shopapp.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();
    Optional<User> getUserByUsername(String username);
    ResponseEntity<User> addUser(String username, String email, String roleName, String password);

    ResponseEntity<DeleteUser> deleteUser(int id);
    ResponseEntity updateUser(User user);
    ResponseEntity editUser(User user);
}
