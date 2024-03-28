package com.pinsoft.shopapp.controller;

import com.pinsoft.shopapp.entity.Role;
import com.pinsoft.shopapp.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/role")
public class RoleController {

    private final RoleService roleService;
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @Operation(tags = "List Roles", description = "Get All Roles", responses = {
            @ApiResponse(description = "Success", responseCode = "200"),
            @ApiResponse(description = "Data Not Found", responseCode = "404")
    })
    @GetMapping("/getAllRoles")
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    @Operation(tags = "Select Roles", description = "Get Role by Name", responses = {
            @ApiResponse(description = "Success", responseCode = "200"),
            @ApiResponse(description = "Data Not Found", responseCode = "404")
    })
    @GetMapping("/getRoleByName/{name}")
    public Optional<Role> getRoleByName(@PathVariable String name) {
        return roleService.getRoleByName(name);
    }

}
