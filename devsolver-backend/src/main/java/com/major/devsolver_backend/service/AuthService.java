package com.major.devsolver_backend.service;

import com.major.devsolver_backend.dto.*;
import com.major.devsolver_backend.entity.User;
import com.major.devsolver_backend.exception.ConflictException;
import com.major.devsolver_backend.exception.InvalidCredentialsException;
import com.major.devsolver_backend.repository.UserRepository;
import com.major.devsolver_backend.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public RegisterResponse register(RegisterRequest req){

        String email = req.email().trim().toLowerCase();

        // 1. validate
        if (userRepository.findByEmail(email).isPresent()){
            throw new ConflictException("User already exists"); // conflict - 409 error
        }

        User user = mapToUser(req);

        // 3. save
        User savedUser = userRepository.save(user);

        return mapToRegisterResponse(savedUser);
    }

    public LoginResponse login(LoginRequest req){

        // Login should never reveal whether user exists
        // otherwise: Attacker can probe emails -> user enumeration
        // Final behavior: to be secure
            // Wrong email -> same error
            // Wrong password -> same error

        String email = req.email().trim().toLowerCase();

        User user = userRepository.findByEmail(email)
                .orElseThrow(()-> new InvalidCredentialsException("Invalid email or password!"));

        if (!passwordEncoder.matches(req.password(), user.getPassword())){
            throw new InvalidCredentialsException("Invalid email or password!"); //
        }

        String token = jwtUtil.generateToken(email);

        // map to LoginResponse
        return LoginResponse.builder()
                .token(token)
                .message("Login Successful!")
                .user(UserResponse.builder()
                        .username(user.getUsername())
                        .email(user.getEmail())
                        .id(user.getId())
                        .bio(user.getBio())
                        .build())
                .build();
    }

    private User mapToUser(RegisterRequest req){
        return User.builder()
                .username(req.username())
                .email(req.email().trim().toLowerCase())
                .password(passwordEncoder.encode(req.password()))
                .build();
    }

    private RegisterResponse mapToRegisterResponse(User user){
        return RegisterResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }
}


// why not overload register or login requests:
// because:
// signup and login should be fast,
// less friction = better ux,
// profile is optional (add bio, avatar, etc)
