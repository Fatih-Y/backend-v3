package com.pinsoft.shopapp.service.impl;

import com.pinsoft.shopapp.dto.DeleteUser;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User addUser(String username, String email, String roleName, String password) {
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new EntityNotFoundException(roleName + " isimli rol bulunamadı"));
        user.setRole(role);
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
    public ResponseEntity updateUser(User user) {
        Optional<User> userToUpdate = userRepository.findByUsername(user.getUsername());
        if (userToUpdate.isPresent()) {
            userToUpdate.get().setUsername(user.getUsername());
            userToUpdate.get().setPassword(user.getPassword());
            userToUpdate.get().setEmail(user.getEmail());
            Optional<Role> optionalRole = roleRepository.findByName(user.getRole().getName());
            if (optionalRole.isEmpty()) {
                throw new RuntimeException(user.getRole().getName() + " isimli rol bulunamadı");
            }
            Role role = optionalRole.get();
            userToUpdate.get().setRole(role);
            userRepository.save(userToUpdate.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @Override
    public ResponseEntity editUser(User user) {
        Optional<User> userToEdit = userRepository.findByUsername(user.getUsername());
        if(userToEdit.isPresent()){
            userToEdit.get().setUsername(user.getUsername());   // parola boş kalınca <string> atandı. çözüm bul
            userToEdit.get().setPassword(user.getPassword());
            userToEdit.get().setEmail(user.getEmail());
            userRepository.save(userToEdit.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }









    // response entityler sadece controllerda olacak burdakileri kaldır ve düzenle
}
