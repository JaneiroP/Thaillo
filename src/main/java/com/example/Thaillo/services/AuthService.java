package com.example.Thaillo.services;

import com.example.Thaillo.dto.AuthRequest;
import com.example.Thaillo.dto.AuthResponse;
import com.example.Thaillo.dto.RegisterRequest;
import com.example.Thaillo.dto.RegisterResponse;
import com.example.Thaillo.entities.User;
import com.example.Thaillo.exception.InvalidCredentialsException;
import com.example.Thaillo.exception.UserNotFoundException;
import com.example.Thaillo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public RegisterResponse signUp(RegisterRequest request) {

        Optional<User> userExist = userRepository.findByEmail(request.email);
        if (userExist.isPresent()) {
            throw new RuntimeException("User already exists with email: " + request.email);
        }

        User user = User.builder()
                .name(request.name)
                .email(request.email)
                .password(passwordEncoder.encode(request.password))
                .build();

        userRepository.save(user);
        String token = jwtService.generateToken(user);
        return new RegisterResponse(user.getName(), user.getEmail(), token);
    }

    public AuthResponse signIn(AuthRequest request) {
        User user = userRepository.findByEmail(request.email)
                .orElseThrow(UserNotFoundException::new);

        if (!passwordEncoder.matches(request.password, user.getPassword())) {
            throw new InvalidCredentialsException();
        }

        String token = jwtService.generateToken(user);
        return new AuthResponse(token);
    }


}
