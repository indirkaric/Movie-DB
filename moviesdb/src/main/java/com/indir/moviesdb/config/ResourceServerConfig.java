package com.indir.moviesdb.config;

import com.indir.moviesdb.security.MovieAccessDeniedHandler;
import com.indir.moviesdb.security.MovieAuthenticationEntryPoint;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.*;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private final MovieAuthenticationEntryPoint movieAuthenticationEntryPoint;

    public ResourceServerConfig(MovieAuthenticationEntryPoint movieAuthenticationEntryPoint) {
        this.movieAuthenticationEntryPoint = movieAuthenticationEntryPoint;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                //access without authentication
                .antMatchers(HttpMethod.GET, "/**/movies").permitAll()
                .antMatchers(HttpMethod.POST, "/**/users").permitAll()
                .antMatchers(HttpMethod.GET, "/**/users").permitAll()
                .antMatchers(HttpMethod.GET, "/**/genres").permitAll()
                .antMatchers(HttpMethod.GET, "/**/directors").permitAll()
                .antMatchers(HttpMethod.GET, "/**/cities").permitAll()
                .antMatchers(HttpMethod.GET, "/**/countries").permitAll()

                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(movieAuthenticationEntryPoint)
                .accessDeniedHandler(new MovieAccessDeniedHandler());
    }

}
