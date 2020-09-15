package com.indir.moviesdb.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.*;

public class MovieSecurityUtils {
    private MovieSecurityUtils(){

    }

    public static Integer getUserId(){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        MovieUserDetails userDetails = (MovieUserDetails) authentication.getPrincipal();
        return userDetails.getUserId();
    }
}
