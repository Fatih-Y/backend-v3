package com.pinsoft.shopapp.register;

import com.pinsoft.shopapp.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String username;
    private String password;

//    private Role role;  kontrol et, kullanıcıya otomatik olarak user rolü nasıl tanımlanacak
}
