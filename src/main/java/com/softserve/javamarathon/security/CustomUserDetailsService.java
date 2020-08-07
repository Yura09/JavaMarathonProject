package com.softserve.javamarathon.security;

import com.softserve.javamarathon.entity.User;
import com.softserve.javamarathon.exception.NoEntityException;
import com.softserve.javamarathon.repository.UserRepository;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    private static Collection<? extends GrantedAuthority> getAuthorities(User user) {
        return AuthorityUtils.createAuthorityList(user.getRole().name());
    }
    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(email)
            .orElseThrow(() -> new NoEntityException("user with email: " + email + " not found!"));
        return new org.springframework.security.core.userdetails.User(
            user.getEmail(), user.getPassword(), getAuthorities(user));
    }
}
