package com.indir.moviesdb.utils;

import com.indir.moviesdb.constants.TestConstants;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.test.web.servlet.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class TokenAccessUtil {
    public static String obtainAccessToken(String username, String password, MockMvc mockMvc) throws Exception {
        final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "password");
        params.add("client_id", TestConstants.CLIENT_ID);
        params.add("username", username);
        params.add("password", password);

        // @formatter:off
        ResultActions result = mockMvc.perform(post("/api/v1/oauth/token")
                .params(params)
                .with(httpBasic(TestConstants.CLIENT_ID, TestConstants.CLIENT_SECRET))
                .accept(TestConstants.CONTENT_TYPE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(TestConstants.CONTENT_TYPE));

        // @formatter:on
        String resultString = result.andReturn().getResponse().getContentAsString();

        JacksonJsonParser jsonParser = new JacksonJsonParser();
        return jsonParser.parseMap(resultString).get("access_token").toString();
    }
}
