package com.indir.moviesdb.controller;

import com.indir.moviesdb.MoviesdbApplication;
import com.indir.moviesdb.constants.TestConstants;
import com.indir.moviesdb.domain.User;
import com.indir.moviesdb.dto.*;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Transactional
@Sql(executionPhase= Sql.ExecutionPhase.BEFORE_TEST_METHOD,scripts="classpath:/script.sql")
@WebAppConfiguration
@SpringBootTest(classes = MoviesdbApplication.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class CityControllerTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private WebApplicationContext wac;
    @Autowired
    private FilterChainProxy springSecurityFilterChain;
    private MockMvc mockMvc;
    CityDto cityDto;
    CountryDto countryDto;
    private static final String CITY_NAME = "Madrid";

    @BeforeEach
    public void setup() {
        countryDto = new CountryDto();
        countryDto.setId(5);
        countryDto.setName("Spain");

        cityDto = new CityDto();
        cityDto.setId(39);
        cityDto.setName("Valencia");
        cityDto.setCountry(countryDto);

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
    void getAllCities() throws Exception {
        final String accessToken = TokenAccessUtil.obtainAccessToken(TestConstants.EMAIL, TestConstants.PASSWORD, mockMvc);
        mockMvc.perform(get("/api/v1/cities")
                .param("email", TestConstants.EMAIL)
                .header("Authorization", "Bearer " + accessToken)
                .accept(TestConstants.CONTENT_TYPE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(TestConstants.CONTENT_TYPE));
    }

    @Test
    void getCityById() throws Exception {
        final String accessToken =TokenAccessUtil.obtainAccessToken(TestConstants.EMAIL, TestConstants.PASSWORD, mockMvc);
        mockMvc.perform(get("/api/v1/cities/2")
                .param("email", TestConstants.EMAIL)
                .header("Authorization", "Bearer " + accessToken)
                .accept(TestConstants.CONTENT_TYPE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(TestConstants.CONTENT_TYPE));
    }

    @Test
    void getCitiesByName() throws Exception {
        final String accessToken = TokenAccessUtil.obtainAccessToken(TestConstants.EMAIL, TestConstants.PASSWORD, mockMvc);
        mockMvc.perform(get("/api/v1/cities/name/{name}", CITY_NAME)
                .param("email", TestConstants.EMAIL)
                .header("Authorization", "Bearer " + accessToken)
                .accept(TestConstants.CONTENT_TYPE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(TestConstants.CONTENT_TYPE));
    }

    @Test
    void deleteCity() throws Exception {
        final String accessToken = TokenAccessUtil.obtainAccessToken(TestConstants.EMAIL, TestConstants.PASSWORD, mockMvc);
        mockMvc.perform(delete("/api/v1/cities/{id}",12)
                .param("email", TestConstants.EMAIL)
                .header("Authorization", "Bearer " + accessToken)
                .accept(TestConstants.CONTENT_TYPE))
                .andExpect(status().isOk());
    }

    @Test
    void saveCity() throws Exception {
        final String accessToken = TokenAccessUtil.obtainAccessToken(TestConstants.EMAIL, TestConstants.PASSWORD, mockMvc);
        mockMvc.perform(post("/api/v1/cities")
                .param("email", TestConstants.EMAIL)
                .header("Authorization", "Bearer " + accessToken)
                .content(ObjectToJsonUtil.asJsonString(cityDto)).contentType(TestConstants.CONTENT_TYPE)
                .accept(TestConstants.CONTENT_TYPE))
                .andExpect(status().isCreated());

    }

    @Test
    void cityNotFoundById() throws Exception {
        final String accessToken =TokenAccessUtil.obtainAccessToken(TestConstants.EMAIL, TestConstants.PASSWORD, mockMvc);
        mockMvc.perform(get("/api/v1/cities/80")
                .param("email", TestConstants.EMAIL)
                .header("Authorization", "Bearer " + accessToken)
                .accept(TestConstants.CONTENT_TYPE))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(TestConstants.CONTENT_TYPE));
    }

}