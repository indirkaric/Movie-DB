package com.indir.moviesdb.controller;

import com.indir.moviesdb.MoviesdbApplication;
import com.indir.moviesdb.constants.TestConstants;
import com.indir.moviesdb.domain.User;
import com.indir.moviesdb.repository.UserRepository;
import com.indir.moviesdb.utils.TokenAccessUtil;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@Sql(executionPhase= Sql.ExecutionPhase.BEFORE_TEST_METHOD,scripts="classpath:/script.sql")
@WebAppConfiguration
@SpringBootTest(classes = MoviesdbApplication.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class GenreControllerTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private WebApplicationContext wac;
    @Autowired
    private FilterChainProxy springSecurityFilterChain;
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        User user = new User();
        user.setFirstName("Enis");
        user.setLastName("Enisic");
        user.setEmail(TestConstants.EMAIL);
        user.setPassword(TestConstants.PASSWORD);
        user.setUsername("EnisonCabani");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).addFilter(springSecurityFilterChain).build();
    }

    @Test
    void getAllGenres() throws Exception {
        final String accessToken = TokenAccessUtil.obtainAccessToken(TestConstants.EMAIL, TestConstants.PASSWORD, mockMvc);
        mockMvc.perform(get("/api/v1/genres")
                .param("email", TestConstants.EMAIL)
                .header("Authorization", "Bearer " + accessToken)
                .accept(TestConstants.CONTENT_TYPE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(TestConstants.CONTENT_TYPE));
    }

    @Test
    void getGenreById() throws Exception {
        final String accessToken = TokenAccessUtil.obtainAccessToken(TestConstants.EMAIL, TestConstants.PASSWORD, mockMvc);
        mockMvc.perform(get("/api/v1/genres/4")
                .param("email", TestConstants.EMAIL)
                .header("Authorization", "Bearer " + accessToken)
                .accept(TestConstants.CONTENT_TYPE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(TestConstants.CONTENT_TYPE));
    }

    @Test
    void deleteGenre() throws Exception{
        final String accessToken = TokenAccessUtil.obtainAccessToken(TestConstants.EMAIL, TestConstants.PASSWORD, mockMvc);
        mockMvc.perform(delete("/api/v1/genres/{id}",3)
                .param("email", TestConstants.EMAIL)
                .header("Authorization", "Bearer " + accessToken)
                .accept(TestConstants.CONTENT_TYPE))
                .andExpect(status().isOk());
    }

    @Test
    void genreNotFound() throws Exception {
        final String accessToken = TokenAccessUtil.obtainAccessToken(TestConstants.EMAIL, TestConstants.PASSWORD, mockMvc);
        mockMvc.perform(get("/api/v1/genres/10")
                .param("email", TestConstants.EMAIL)
                .header("Authorization", "Bearer " + accessToken)
                .accept(TestConstants.CONTENT_TYPE))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(TestConstants.CONTENT_TYPE));
    }
}