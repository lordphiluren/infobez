package ru.sushchenko.infobez.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sushchenko.infobez.dto.AuthResponse;
import ru.sushchenko.infobez.entity.User;
import ru.sushchenko.infobez.repo.UserRepo;
import ru.sushchenko.infobez.security.JwtIssuer;
import ru.sushchenko.infobez.security.UserPrincipal;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtIssuer jwtIssuer;
    private final AuthenticationManager authenticationManager;
    private final UserRepo userRepo;
    private final PasswordEncoder bCryptPasswordEncoder;
    public AuthResponse attemptLogin(String username, String password) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );
        SecurityContextHolder.getContext().setAuthentication(auth);
        UserPrincipal principal = (UserPrincipal) auth.getPrincipal();
        String token = jwtIssuer.issue(principal);
        return AuthResponse.builder()
                .token(token)
                .build();
    }
    @Transactional
    public void signUp(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepo.save(user);
    }
    @Transactional
    public void changeUserPassword(String password, User user) {
        if(password.chars().distinct().count() == password.length()) {
            user.setPassword(bCryptPasswordEncoder.encode(password));
            userRepo.save(user);
        } else {
            throw new RuntimeException("Пароль не может содержать повторяющиеся символы");
        }
    }
}
