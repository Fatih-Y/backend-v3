package com.pinsoft.shopapp.dto;

import lombok.Data;

import java.util.Optional;

@Data
public class EditUser {
    private int id;
    private Optional<String> username = Optional.empty();
    private Optional<String> email = Optional.empty();
    private Optional<String> password = Optional.empty();
}
