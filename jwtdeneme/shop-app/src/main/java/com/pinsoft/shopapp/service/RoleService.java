package com.pinsoft.shopapp.service;

import com.pinsoft.shopapp.entity.Role;
import java.util.List;
import java.util.Optional;

public interface RoleService {
    List<Role> getAllRoles();
    Optional<Role> getRoleByName(String name);
    Optional<Role> findByName(String name);
}
