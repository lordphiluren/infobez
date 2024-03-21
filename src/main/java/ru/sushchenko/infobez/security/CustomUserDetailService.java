package ru.sushchenko.infobez.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.sushchenko.infobez.entity.User;
import ru.sushchenko.infobez.repo.UserRepo;
import ru.sushchenko.infobez.utils.UserNotFoundException;

@Component
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User doesn't exist"));
        return UserPrincipal.builder()
                .user(user)
                .build();
    }
}
