package com.pinsoft.shopapp.service.impl;

import com.pinsoft.shopapp.dto.userDTO.DeleteUser;
import com.pinsoft.shopapp.dto.userDTO.EditUser;
import com.pinsoft.shopapp.dto.userDTO.GetAllUsers;
import com.pinsoft.shopapp.entity.Role;
import com.pinsoft.shopapp.entity.User;
import com.pinsoft.shopapp.repository.RoleRepository;
import com.pinsoft.shopapp.repository.UserRepository;
import com.pinsoft.shopapp.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository, RoleRepository roleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<GetAllUsers> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> new GetAllUsers(user.getId(), user.getUsername(), user.getEmail(), user.getRole().getName()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User addUser(User user) {
        Role userRole = roleRepository.findByName(user.getRole().getName())
                .orElseThrow(() -> new RuntimeException(user.getRole().getName()+" adlı rol bulunamadı"));
        user.setRole(userRole);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEmail(user.getEmail());
        user.setUsername(user.getUsername());
        return userRepository.save(user);
    }



    @Override
    public ResponseEntity<DeleteUser> deleteUser(int id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            DeleteUser deleteUser = new DeleteUser(user.getId(), user.getUsername());
            userRepository.deleteById(id);
            return ResponseEntity.ok(deleteUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public Optional<User> updateUser(User user) {
        return userRepository.findById(user.getId())
                .map(userToUpdate -> {
                    userToUpdate.setUsername(user.getUsername());
                    userToUpdate.setEmail(user.getEmail());
                    userToUpdate.setPassword(passwordEncoder.encode(user.getPassword()));

                    Role newRole = roleRepository.findByName(user.getRole().getName())
                            .orElseThrow(() -> new EntityNotFoundException(user.getRole().getName()+" isimli rol bulunamadı" ));
                    userToUpdate.setRole(newRole);
                    return userRepository.save(userToUpdate);
                });
    }
    @Override
    public Optional<User> editUser(EditUser editUser) {
        return userRepository.findById(editUser.getId())
                .map(user -> {
                    editUser.getUsername().ifPresent(user::setUsername);
                    editUser.getEmail().ifPresent(user::setEmail);
                    editUser.getPassword().ifPresent(user::setPassword);
                    return userRepository.save(user);
                });
    }
}











    // response entityler sadece controllerda olacak burdakileri kaldır ve düzenle

