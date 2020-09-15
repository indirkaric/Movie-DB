package com.indir.moviesdb.security;

import org.springframework.security.oauth2.common.*;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import java.util.*;

public class MovieTokenEnhancer implements TokenEnhancer {
    private static final int ADDITIONAL_INFO_CAPACITY = 2;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        MovieUserDetails userDetails = (MovieUserDetails) authentication.getPrincipal();

        Map<String,Object> additionalInfo = new HashMap<>(ADDITIONAL_INFO_CAPACITY);
        additionalInfo.put("user_id", userDetails.getUserId());
        additionalInfo.put("given_name", userDetails.getGivenName());

        ((DefaultOAuth2AccessToken)accessToken).setAdditionalInformation(additionalInfo);

        return accessToken;
    }
}
