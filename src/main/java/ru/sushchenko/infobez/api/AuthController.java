package ru.sushchenko.infobez.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.sushchenko.infobez.dto.AuthChangeRequest;
import ru.sushchenko.infobez.dto.AuthRequest;
import ru.sushchenko.infobez.dto.AuthResponse;
import ru.sushchenko.infobez.entity.User;
import ru.sushchenko.infobez.security.UserPrincipal;
import ru.sushchenko.infobez.service.AuthService;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Authorization", description = "Controller for user authorization and authentication using JWT")
public class AuthController {
    private final AuthService authService;
    private final ModelMapper modelMapper;
    @Operation(
            summary = "User login",
            description = "Authenticate user to the system"
    )
    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody AuthRequest authRequest) {
        return authService.attemptLogin(authRequest.getUsername(), authRequest.getPassword());
    }
    @Operation(
            summary = "User signup",
            description = "Handles user registration"
    )
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@Valid @RequestBody AuthRequest authRequest) {
        authService.signUp(modelMapper.map(authRequest, User.class));
        return ResponseEntity.ok("Successful registration");
    }
    @Operation(
            summary = "Change password",
            description = "Change user password and check for repeated characters"
    )
    @SecurityRequirement(name = "JWT")
    @PutMapping("/password")
    public ResponseEntity<?> changePassword(@Valid @RequestBody AuthChangeRequest authRequest,
                                            @AuthenticationPrincipal UserPrincipal userPrincipal) {
        authService.changeUserPassword(authRequest.getPassword(), userPrincipal.getUser());
        return ResponseEntity.ok("Password changed successfully");
    }
}
