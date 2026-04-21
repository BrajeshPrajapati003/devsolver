package com.major.devsolver_backend.service;

import com.major.devsolver_backend.dto.LoginRequest;
import com.major.devsolver_backend.dto.RegisterRequest;
import com.major.devsolver_backend.entity.User;
import com.major.devsolver_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User register(RegisterRequest req){

        // 1. validate
        if (userRepository.findByEmail(req.email()).isPresent()){
            throw new RuntimeException("User already exists");
        }

        User user = mapToUser(req);

        // 3. save
        return userRepository.save(user);
    }

    public User login(LoginRequest req){

        User user = userRepository.findByEmail(req.email())
                .orElseThrow(()-> new RuntimeException("User not found!"));

        if (!passwordEncoder.matches(req.password(), user.getPassword())){
            throw new RuntimeException("Invalid password!");
        }

        return user;
    }

    private User mapToUser(RegisterRequest req){
        return User.builder()
                .username(req.username())
                .email(req.email())
                .password(passwordEncoder.encode(req.password()))
                .build();
    }
}


// why not overload register or login requests:
// because:
// signup and login should be fast,
// less friction = better ux,
// profile is optional (add bio, avatar, etc)
