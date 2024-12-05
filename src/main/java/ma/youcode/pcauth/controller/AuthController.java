package ma.youcode.pcauth.controller;

import ma.youcode.pcauth.dto.request.UserRequestDto;
import ma.youcode.pcauth.entities.User;
import ma.youcode.pcauth.mapper.UserMapper;
import ma.youcode.pcauth.service.Interface.UserInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationProvider authenticationProvider;
    private final UserInterface userService;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Validated UserRequestDto userRequestDto) {
        if (userService.usernameExists(userRequestDto.username())) {
            return ResponseEntity.badRequest()
                .body("Username already exists");
        }

        User user = userMapper.toEntity(userRequestDto);
        user.setUsername(userRequestDto.username());
        user.setPassword(passwordEncoder.encode(userRequestDto.password()));
        user.setActive(true);
        
        userService.saveUser(user);
        
        return ResponseEntity.status(HttpStatus.CREATED)
            .body("Registration successful");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Validated UserRequestDto loginRequest, 
                                 HttpServletRequest request) {
        try {
            Authentication authentication = authenticationProvider.authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginRequest.username(),
                    loginRequest.password()
                )
            );

            if (authentication.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
                
                var session = request.getSession(true);
                session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
                
                return ResponseEntity.ok("Login successful");
            }
            
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Authentication failed");
                
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Invalid credentials");
        }
    }
}