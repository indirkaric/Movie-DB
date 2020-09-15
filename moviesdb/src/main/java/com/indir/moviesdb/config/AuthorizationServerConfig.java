package com.indir.moviesdb.config;

import com.indir.moviesdb.security.MovieTokenEnhancer;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.Arrays;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    private final AuthenticationManager authenticationManager;
    private final JwtAccessTokenConverter accessTokenConverter;
    private final TokenStore tokenStore;
    private final PasswordEncoder passwordEncoder;

    private static final String CLIENT_ID = "moviedb";
    private static final String CLIENT_SECRET = "moviedb-secret";
    private static final String GRANT_TYPE_PASSWORD = "password";
    private static final String AUTHORIZATION_CODE = "authorization_code";
    private static final String REFRESH_TOKEN = "refresh_token";
    private static final String IMPLICIT = "implicit";
    private static final String SCOPE_READ = "read";
    private static final String SCOPE_WRITE = "write";
    private static final String TRUST = "trust";
    private static final int ACCESS_TOKEN_VALIDITY_SECONDS = 3600;
    private static final int REFRESH_TOKEN_VALIDITY_SECONDS = 28800;

    public AuthorizationServerConfig(AuthenticationManager authenticationManager, JwtAccessTokenConverter accessTokenConverter, TokenStore tokenStore, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.accessTokenConverter = accessTokenConverter;
        this.tokenStore = tokenStore;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients
                .inMemory()
                .withClient(CLIENT_ID)
                .secret(passwordEncoder.encode(CLIENT_SECRET))
                .accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS)
                .refreshTokenValiditySeconds(REFRESH_TOKEN_VALIDITY_SECONDS)
                .authorizedGrantTypes(GRANT_TYPE_PASSWORD, AUTHORIZATION_CODE, REFRESH_TOKEN, IMPLICIT)
                .scopes(SCOPE_READ, SCOPE_WRITE, TRUST);
    }

    @Override
    public void configure(final AuthorizationServerEndpointsConfigurer endpoints) {
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        enhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer(), accessTokenConverter));

        endpoints
                .tokenEnhancer(enhancerChain)
                .tokenStore(this.tokenStore)
                .accessTokenConverter(this.accessTokenConverter)
                .authenticationManager(this.authenticationManager)
                .pathMapping("/oauth/token", "/api/v1/oauth/token");
    }

    @Bean
    public TokenEnhancer tokenEnhancer() {
        return new MovieTokenEnhancer();
    }
}
