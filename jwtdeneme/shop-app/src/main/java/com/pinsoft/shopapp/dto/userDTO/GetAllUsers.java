package com.pinsoft.shopapp.dto.userDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllUsers {

    private int id;
    private String username;
    private String email;
    private String roleName;
}
