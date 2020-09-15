package com.indir.moviesdb.security;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import javax.servlet.http.*;
import java.io.IOException;

@Component
public class MovieAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private static final String ACCESS_DENIED_JSON = "{  \n" +
            "   \"error\":\"access_denied\",\n" +
            "   \"error_description\":\"You don't have privileges for requested action!\"\n" +
            "}";

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse response, AuthenticationException e) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(ACCESS_DENIED_JSON);
    }
}
