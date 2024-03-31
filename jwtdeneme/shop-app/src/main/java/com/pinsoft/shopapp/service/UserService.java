package com.pinsoft.shopapp.service;

import com.pinsoft.shopapp.dto.DeleteUser;
import com.pinsoft.shopapp.dto.EditUser;
import com.pinsoft.shopapp.dto.GetAllUsers;
import com.pinsoft.shopapp.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<GetAllUsers> getAllUsers();
    Optional<User> getUserByUsername(String username);
    User addUser(User user);

    ResponseEntity<DeleteUser> deleteUser(int id);
    Optional<User> updateUser(User user);
    Optional<User> editUser(EditUser editUser);
}
