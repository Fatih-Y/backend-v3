package com.pinsoft.shopapp.controller;

import com.pinsoft.shopapp.dto.DeleteUser;

import com.pinsoft.shopapp.entity.User;
import com.pinsoft.shopapp.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class UserController {

    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(tags = "List Users", description = "Get All Users", responses = {
            @ApiResponse(description = "Success", responseCode = "200"),
            @ApiResponse(description = "Data Not Found", responseCode = "404")
    })
    @GetMapping("/getAllUsers") // shortcut for @RequestMapping(method=RequestMethod.GET)
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @Operation(tags = "Select User", description = "Get User by Username", responses = {
            @ApiResponse(description = "Success", responseCode = "200"),
            @ApiResponse(description = "Data Not Found", responseCode = "404")
    })
    @GetMapping("/getUserByUsername/{username}")
    public Optional<User> getUserByUsername(@PathVariable("username") String username) {
        return userService.getUserByUsername(username);
    }

    @PostMapping("/addUser")
    public ResponseEntity<User> addUser(@RequestParam String username,
                                        @RequestParam String email,
                                        @RequestParam String roleName,
                                        @RequestParam String password) {
        return userService.addUser(username, email, roleName, password);
    }
    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<DeleteUser> deleteUser(@PathVariable int id) {
        return userService.deleteUser(id);
    }

    @PostMapping("/updateUser")
    public ResponseEntity updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }
    @PostMapping("/editUser")
    public ResponseEntity editUser (@RequestBody User user) {
        return userService.editUser(user);
    }



}
