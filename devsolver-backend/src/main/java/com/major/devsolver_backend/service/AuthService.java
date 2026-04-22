package com.major.devsolver_backend.service;

import com.major.devsolver_backend.dto.LoginRequest;
import com.major.devsolver_backend.dto.LoginResponse;
import com.major.devsolver_backend.dto.RegisterRequest;
import com.major.devsolver_backend.dto.RegisterResponse;
import com.major.devsolver_backend.entity.User;
import com.major.devsolver_backend.exception.ConflictException;
import com.major.devsolver_backend.exception.InvalidCredentialsException;
import com.major.devsolver_backend.exception.NotFoundException;
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

        // 1. validate
        if (userRepository.findByEmail(req.email()).isPresent()){
            throw new ConflictException("User already exists"); // conflict - 409 error
        }

        User user = mapToUser(req);

        // 3. save
        User savedUser = userRepository.save(user);

        return mapToRegisterResponse(savedUser);
    }

    public LoginResponse login(LoginRequest req){

        User user = userRepository.findByEmail(req.email())
                .orElseThrow(()-> new NotFoundException("User not found!")); // Resource Not Found - 404 error

        if (!passwordEncoder.matches(req.password(), user.getPassword())){
            throw new InvalidCredentialsException("Invalid password!"); //
        }

        String token = jwtUtil.generateToken(user.getEmail());

        // map to LoginResponseDto
        return LoginResponse.builder()
                .token(token)
                .message("Login Successful!")
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }

    private User mapToUser(RegisterRequest req){
        return User.builder()
                .username(req.username())
                .email(req.email())
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
