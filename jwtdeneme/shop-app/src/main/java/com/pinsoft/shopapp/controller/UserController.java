package com.pinsoft.shopapp.controller;

import com.pinsoft.shopapp.dto.DeleteUser;

import com.pinsoft.shopapp.dto.EditUser;
import com.pinsoft.shopapp.dto.GetAllUsers;
import com.pinsoft.shopapp.entity.User;
import com.pinsoft.shopapp.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/user")
public class UserController {


    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(tags = "List Users", description = "Get All Users", responses = {
            @ApiResponse(description = "Success", responseCode = "200"),
            @ApiResponse(description = "Data Not Found", responseCode = "404")
    })// shortcut for @RequestMapping(method=RequestMethod.GET)
    @GetMapping("/getAllUsers")
    public List<GetAllUsers> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/getUserByUsername/{username}")
    public Optional<User> getUserByUsername(@PathVariable("username") String username) {
        return userService.getUserByUsername(username);
    }

    @PostMapping("/addUser")
    public ResponseEntity<User> addUser(@RequestBody User newUser) {
        User savedUser = userService.addUser(newUser);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).body(savedUser);
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<DeleteUser> deleteUser(@PathVariable int id) {
        return userService.deleteUser(id);
    }

    @PutMapping("/updateUser")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        return userService.updateUser(user)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PutMapping("/editUser")
    public ResponseEntity<User> editUser(@RequestBody EditUser editUser) {
        return userService.editUser(editUser)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}











