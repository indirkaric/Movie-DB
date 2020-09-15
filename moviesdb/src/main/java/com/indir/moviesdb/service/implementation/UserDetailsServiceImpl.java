package com.indir.moviesdb.service.implementation;

import com.indir.moviesdb.domain.User;
import com.indir.moviesdb.repository.UserRepository;
import com.indir.moviesdb.security.MovieUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service("userService")
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("Invalid username or password"));
        return new MovieUserDetails(user);
    }
}
