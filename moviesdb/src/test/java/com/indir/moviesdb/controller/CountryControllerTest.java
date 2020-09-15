package com.indir.moviesdb.controller;

import com.indir.moviesdb.MoviesdbApplication;
import com.indir.moviesdb.constants.TestConstants;
import com.indir.moviesdb.domain.User;
import com.indir.moviesdb.dto.CountryDto;
import com.indir.moviesdb.repository.UserRepository;
import com.indir.moviesdb.utils.*;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@Sql(executionPhase= Sql.ExecutionPhase.BEFORE_TEST_METHOD,scripts="classpath:/script.sql")
@WebAppConfiguration
@SpringBootTest(classes = MoviesdbApplication.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class CountryControllerTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private WebApplicationContext wac;
    @Autowired
    private FilterChainProxy springSecurityFilterChain;
    private MockMvc mockMvc;
    CountryDto countryDto;

    @BeforeEach
    public void setup() {
       countryDto = new CountryDto();
       countryDto.setId(33);
       countryDto.setName("Germany");

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
    void getAllCountries() throws Exception {
        final String accessToken = TokenAccessUtil.obtainAccessToken(TestConstants.EMAIL, TestConstants.PASSWORD, mockMvc);
        mockMvc.perform(get("/api/v1/countries")
                .param("email", TestConstants.EMAIL)
                .header("Authorization", "Bearer " + accessToken)
                .accept(TestConstants.CONTENT_TYPE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(TestConstants.CONTENT_TYPE));
    }

    @Test
    void getCountryById() throws Exception {
        final String accessToken = TokenAccessUtil.obtainAccessToken(TestConstants.EMAIL, TestConstants.PASSWORD, mockMvc);
        mockMvc.perform(get("/api/v1/countries/66")
                .param("email", TestConstants.EMAIL)
                .header("Authorization", "Bearer " + accessToken)
                .accept(TestConstants.CONTENT_TYPE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(TestConstants.CONTENT_TYPE));

    }

    @Test
    void deleteCountry() throws Exception {
        final String accessToken = TokenAccessUtil.obtainAccessToken(TestConstants.EMAIL, TestConstants.PASSWORD, mockMvc);
        mockMvc.perform(delete("/api/v1/countries/{id}",54)
                .param("email", TestConstants.EMAIL)
                .header("Authorization", "Bearer " + accessToken)
                .accept(TestConstants.CONTENT_TYPE))
                .andExpect(status().isOk());
    }

    @Test
    void saveCountry() throws Exception {
        final String accessToken = TokenAccessUtil.obtainAccessToken(TestConstants.EMAIL, TestConstants.PASSWORD, mockMvc);
        mockMvc.perform(post("/api/v1/countries")
                .param("email", TestConstants.EMAIL)
                .header("Authorization", "Bearer " + accessToken)
                .content(ObjectToJsonUtil.asJsonString(countryDto)).contentType(TestConstants.CONTENT_TYPE)
                .accept(TestConstants.CONTENT_TYPE))
                .andExpect(status().isCreated());
    }

    @Test
    void countryNotFound() throws Exception {
        final String accessToken = TokenAccessUtil.obtainAccessToken(TestConstants.EMAIL, TestConstants.PASSWORD, mockMvc);
        mockMvc.perform(get("/api/v1/countries/18")
                .param("email", TestConstants.EMAIL)
                .header("Authorization", "Bearer " + accessToken)
                .accept(TestConstants.CONTENT_TYPE))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(TestConstants.CONTENT_TYPE));
    }

}