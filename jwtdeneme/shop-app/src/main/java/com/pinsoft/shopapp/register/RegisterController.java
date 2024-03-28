package com.pinsoft.shopapp.register;


import com.pinsoft.shopapp.entity.Role;
import com.pinsoft.shopapp.entity.User;
import com.pinsoft.shopapp.repository.RoleRepository;
import com.pinsoft.shopapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController

public class RegisterController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    @Autowired
    public RegisterController(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest){
        if(userRepository.existsByUsername(registerRequest.getUsername())){
            return new ResponseEntity<>(" Kullanıcı adı zaten mevcut", HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setEmail("örnek@örnek.com");


        Role role = roleRepository.findByName("user").get();
        user.setRole(role);

        userRepository.save(user);
        return new ResponseEntity<>(" Kayıt başarılı",HttpStatus.OK);

    }
}
