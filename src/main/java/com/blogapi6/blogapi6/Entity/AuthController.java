package com.blogapi6.blogapi6.Entity;

import com.blogapi6.blogapi6.Payload.SignUpDto;
import com.blogapi6.blogapi6.Repository.RolesRepository;
import com.blogapi6.blogapi6.Repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private UserRepository userRepo;
    private RolesRepository roleRepo;
    private PasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepo, RolesRepository roleRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
    }
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignUpDto signUpDto){
        if(userRepo.existsByUsername(signUpDto.getUsername())){
            return new ResponseEntity<>("Username is already taken", HttpStatus.BAD_REQUEST);
        }
        if(userRepo.existsByEmail(signUpDto.getEmail())){
            return new ResponseEntity<>("Email is already taken",HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        user.setName(signUpDto.getName());
        user.setEmail(signUpDto.getEmail());
        user.setUsername(signUpDto.getUsername());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        Roles roles = roleRepo.findByName("ROLES_ADMIN").get();
        user.setRoles(Collections.singleton(roles));
        userRepo.save(user);

        return new ResponseEntity<>("account is Createddddd",HttpStatus.CREATED);
    }
}
